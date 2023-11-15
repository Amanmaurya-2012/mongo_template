package com.example.mongoTemplate.service;

import com.example.mongoTemplate.dto.user.UserDataDto;

import java.sql.Timestamp;
import java.util.List;

public interface TestService {

    UserDataDto getUserDetails(String userId);

}
