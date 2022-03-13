package com.bathtub.controller;

import com.bathtub.api.FaceDetectAuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/faceDetect")
public class FaceDetectController {

    @RequestMapping("/getAuth")
    public String getAuth() {
        return FaceDetectAuthService.getAuth();
    }
}
