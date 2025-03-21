package co.edu.eci.arep.controller;


import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Map;


@RestController
@EnableWebMvc
public class PingController {
    @RequestMapping(path = "/ping", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public Map<String, Object> ping(@RequestBody NumberRequest request) {
        Map<String, Object> response = new HashMap<>();
        response.put("pong", "Received number: " + request.getNumber());
        return response;
    }

    // Clase interna para representar el objeto JSON de entrada
    public static class NumberRequest {
        private int number;

        public int getNumber() {
            return number;
        }

        public void setNumber(int number) {
            this.number = number;
        }
    }
}
