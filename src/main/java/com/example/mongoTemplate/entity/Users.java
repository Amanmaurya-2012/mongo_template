package com.example.mongoTemplate.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@NoArgsConstructor
@Data
@Document(collection = "kite_user_users")
@CompoundIndexes({
        @CompoundIndex(def = "{'enterprise_id':1, manager_id:1}", name = "enterpriseId_manager_id_idx"),
        @CompoundIndex(def = "{'user_id':1}", name = "userId_idx"),
        @CompoundIndex(def = "{'business_unit':1}", name = "business_unit_idx")
})
public class Users implements Serializable {

    @Id
    private String idField;

    private String userId;
    private String enterpriseId;
    private Date dateOfBirth;
    /*
     * @see {@link Gender}
     */
    private String gender;
    private String mobileNo;
    /*
     * @see {@link UserStatus}
     */
    private String status;
    private String firstName;
    private String lastName;
    private String defaultLanguage;
    private String nationality;
    private String alternatePhoneNumber;
    private String approvedBy;
    private String createdBy;
    private Timestamp createdOn;
    private String modifiedBy;
    private Timestamp modifiedOn;
    private String email;
    private Timestamp passwordExpiryDateTime;
    /*
     * @see {@link PasswordType}
     */
    private String passwordType;
    private String designation;
    private Date dateOfJoining;
    private String employeeId;
    private String managerId;
    /*
     * @see {@link UserRegistrationStatus}
     */
    private String userRegistrationStatus;
    private String grade;
    private String displayName;
    private String costCenterId;
    /*
     * @see {@link MobileVerificationStatus}
     */
    private String mobileVerificationStatus;
    private String departmentId;
    private String username;
    /*
     * @see {@link UserProfile}
     */
    private String userProfile;
    private String locationId;

    private String progressBarStatus;
    private String guidedTourStatus;

//    private List<ProgramRoles> programRoles;

    private String businessUnit;

    public Users(String idField) {
        this.idField = idField;
    }
}
