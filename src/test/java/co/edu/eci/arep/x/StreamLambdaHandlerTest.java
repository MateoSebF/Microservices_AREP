package co.edu.eci.arep;

import com.amazonaws.serverless.proxy.internal.LambdaContainerHandler;
import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.amazonaws.services.lambda.runtime.Context;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jakarta.ws.rs.HttpMethod;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;

public class StreamLambdaHandlerTest {

    private static StreamLambdaHandler handler;
    private static Context lambdaContext;

    @BeforeAll
    public static void setUp() {
        handler = new StreamLambdaHandler();
        lambdaContext = new MockLambdaContext();
    }
    /*    
    @Test
    public void ping_streamRequest_respondsWithNumberRequest() {
        String requestBody = "{\"number\": 42}";
        InputStream requestStream = new AwsProxyRequestBuilder("/ping", HttpMethod.POST)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .body(requestBody)
                .buildStream();
        
        ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
        handle(requestStream, responseStream);

        AwsProxyResponse response = readResponse(responseStream);
        assertNotNull(response);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatusCode());

        assertFalse(response.isBase64Encoded());
        assertTrue(response.getBody().contains("number"));
        assertTrue(response.getBody().contains("42"));

        assertTrue(response.getMultiValueHeaders().containsKey(HttpHeaders.CONTENT_TYPE));
        assertTrue(response.getMultiValueHeaders().getFirst(HttpHeaders.CONTENT_TYPE).startsWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void invalidResource_streamRequest_responds404() {
        InputStream requestStream = new AwsProxyRequestBuilder("/invalid", HttpMethod.POST)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .buildStream();
        ByteArrayOutputStream responseStream = new ByteArrayOutputStream();

        handle(requestStream, responseStream);

        AwsProxyResponse response = readResponse(responseStream);
        assertNotNull(response);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), response.getStatusCode());
    }

    private void handle(InputStream is, ByteArrayOutputStream os) {
        try {
            handler.handleRequest(is, os, lambdaContext);
        } catch (IOException e) {
            e.printStackTrace();
            fail(e.getMessage());
        }
    }

    private AwsProxyResponse readResponse(ByteArrayOutputStream responseStream) {
        try {
            return LambdaContainerHandler.getObjectMapper().readValue(responseStream.toByteArray(), AwsProxyResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Error while parsing response: " + e.getMessage());
        }
        return null;
    }

    public void printRequestStream(InputStream requestStream) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(requestStream, StandardCharsets.UTF_8))) {
            StringBuilder requestContent = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                requestContent.append(line).append("\n");
            }
            System.out.println("Request Stream Content:\n" + requestContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
         */

}
