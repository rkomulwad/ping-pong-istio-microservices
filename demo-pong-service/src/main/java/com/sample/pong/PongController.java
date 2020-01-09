package com.sample.pong;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PongController {
    @GetMapping("/pong")
    public ResponseEntity<String> pong() {
        return new ResponseEntity<>("pong!", HttpStatus.OK);
    }
}
