package com.example.mongoTemplate.service;

import com.example.mongoTemplate.dto.user.UserDataDto;

import java.util.List;

public interface TestService {

    List<UserDataDto> getUserDetails(String type);

}
