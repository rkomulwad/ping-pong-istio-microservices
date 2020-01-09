package com.sample.ping;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

@RestController
public class PingController {
    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        String url = "http://demo-pong-service.pong.svc.cluster.local:8080/pong";

        RestTemplate pongClient = new RestTemplate();
        try {
            ResponseEntity<String> response = pongClient.getForEntity(url, String.class);
            if (response.getStatusCodeValue() == 200) {
                return new ResponseEntity<>("ping-" + response.getBody(), HttpStatus.OK);
            }else {
                return new ResponseEntity<>("Failure: "+ response.getBody(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch(HttpStatusCodeException e) {
            System.err.println("Error Accessing Pong Service -" + e.getResponseBodyAsString());
            return ResponseEntity.status(e.getRawStatusCode())
                    .headers(e.getResponseHeaders())
                    .body("Error Accessing Pong Service - " + e.getResponseBodyAsString());
        }
    }
}
