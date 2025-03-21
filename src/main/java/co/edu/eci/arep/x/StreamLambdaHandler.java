package co.edu.eci.arep;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.serverless.proxy.spring.SpringBootLambdaContainerHandler;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.input.TeeInputStream;
import org.apache.commons.io.output.TeeOutputStream;
import java.io.ByteArrayOutputStream;

import jakarta.servlet.FilterRegistration;
import org.springframework.web.filter.CorsFilter;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamLambdaHandler implements RequestStreamHandler {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static SpringBootLambdaContainerHandler<AwsProxyRequest, AwsProxyResponse> handler;
    static {
        try {
            handler = SpringBootLambdaContainerHandler.getAwsProxyHandler(Application.class);

            handler.onStartup(servletContext -> {
                UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                FilterRegistration.Dynamic cors = servletContext.addFilter("CORSFilter", new CorsFilter(source));
                cors.setInitParameter("cors.allowedOrigins", "*");
                cors.setInitParameter("cors.allowedMethods", "GET,POST,PUT,DELETE,OPTIONS");
                cors.setInitParameter("cors.allowedHeaders", "Authorization,Content-Type");
                cors.setInitParameter("cors.allowCredentials", "true"); // ✅ Esto es lo importante
                cors.addMappingForUrlPatterns(null, false, "/*");
            });
        } catch (ContainerInitializationException e) {
            // if we fail here. We re-throw the exception to force another cold start
            e.printStackTrace();
            throw new RuntimeException("Could not initialize Spring Boot application", e);
        }
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream, Context context)
            throws IOException {
        ByteArrayOutputStream requestBaos = new ByteArrayOutputStream();
        ByteArrayOutputStream responseBaos = new ByteArrayOutputStream();

        try (TeeInputStream teeStream = new TeeInputStream(inputStream, requestBaos, true);
                TeeOutputStream teeOutputStream = new TeeOutputStream(outputStream, responseBaos)) {

            handler.proxyStream(teeStream, teeOutputStream, context);
        }

        // 📥 Entrada
        String requestBody = requestBaos.toString(StandardCharsets.UTF_8);
        System.out.println("\n===========================================");
        System.out.println("🚀 Incoming Request:");
        System.out.println("===========================================");
        System.out.println(requestBody);

        try {
            AwsProxyRequest awsRequest = objectMapper.readValue(requestBody, AwsProxyRequest.class);
            System.out.println("\n🔹 HTTP Method: " + awsRequest.getHttpMethod());
            System.out.println("🔹 Path: " + awsRequest.getPath());
            System.out.println("🔹 Headers: " + awsRequest.getHeaders());
            System.out.println("🔹 Query Params: " + awsRequest.getQueryStringParameters());
            System.out.println("🔹 Body: " + awsRequest.getBody());
        } catch (Exception e) {
            System.out.println("⚠️ Error parsing request: " + e.getMessage());
        }

        // 📤 Salida
        String responseBody = responseBaos.toString(StandardCharsets.UTF_8);
        System.out.println("\n===========================================");
        System.out.println("📤 Outgoing Response:");
        System.out.println("===========================================");
        System.out.println(responseBody);
    }
}