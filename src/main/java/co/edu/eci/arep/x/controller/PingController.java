package co.edu.eci.arep.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@RestController
@EnableWebMvc
public class PingController {
    
    @PostMapping(path = "/ping", consumes = "application/json", produces = "application/json")
    public NumberRequest ping(@RequestBody NumberRequest request) {
        NumberRequest response = new NumberRequest();
        response.setNumber(request.getNumber());
        return response;
    }

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
