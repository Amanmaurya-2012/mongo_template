package com.example.mongoTemplate.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDataDto {
    private String id;
    private String userId;
    private String status;
    private String firstName;
    private String email;
    private String gender;
    private String mobileNo;

    private String addressId;
    private String state;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String addressType;

//    private List<UserAddress> address;

}
