package com.example.mongoTemplate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@NoArgsConstructor
@Data
@Document(collection = "kite_user_user_address")
@CompoundIndexes({
        @CompoundIndex(def = "{'user_id':1}", name = "userId_idx")
})
public class UserAddress {

    @Id
    private String idField;

    @Field("address_id")
    private String addressId;

    @Field("user_id")
    private String userId;

    @Field("address_type")
    private String addressType;

    @Field("status")
    private String status;

    @Field("enterprise_id")
    private String enterpriseId;

    @Field("address_line1")
    private String addressLine1;

    @Field("address_line2")
    private String addressLine2;

    @Field("address_line3")
    private String addressLine3;

    @Field("city")
    private String city;

    @Field("state")
    private String state;

    @Field("pin_code")
    private String pinCode;

    @Field("created_on")
    private Date createdOn;

    @Field("modified_on")
    private Date modifiedOn;
    public UserAddress(String idField) {
        this.idField = idField;
    }
}
