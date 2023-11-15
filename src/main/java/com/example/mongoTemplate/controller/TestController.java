package com.example.mongoTemplate.controller;

import com.example.mongoTemplate.dto.user.UserDataDto;
import com.example.mongoTemplate.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    @GetMapping("/v1/web/user-data")
    public UserDataDto getUserDetails(@RequestParam(value = "userId")String userId) {
        return testService.getUserDetails(userId);
    }
}
