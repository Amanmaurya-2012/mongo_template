package com.example.mongoTemplate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Document(collection = "kite_user_user_address")
@CompoundIndexes({
        @CompoundIndex(def = "{'user_id':1}", name = "userId_idx")
})
public class UserAddress {

    @Id
    private String idField;
    private String addressId;
    private String userId;
    private String addressType;
    private String status;
    private String enterpriseId;
    private String addressLine1;
    private String addressLine2;
    private String addressLine3;
    private String city;
    private String state;
    private String pinCode;
    private Timestamp createdOn;
    private Timestamp modifiedOn;
    public UserAddress(String idField) {
        this.idField = idField;
    }
}
