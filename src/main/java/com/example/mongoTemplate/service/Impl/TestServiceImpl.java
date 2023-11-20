package com.example.mongoTemplate.service.Impl;

import com.example.mongoTemplate.dto.user.UserDataDto;
import com.example.mongoTemplate.entity.Users;
import com.example.mongoTemplate.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final MongoTemplate mongoTemplate;

    public static final Fields userAddressFields = Fields
            .fields("id", "user_id", "status", "first_name", "email", "gender", "mobile_no", "address");


    @Override
    public List<UserDataDto> getUserDetails(String type) {

//        UserDataDto userData = getDTOObject(mongoTemplate
//                .aggregate(allowDiskUse(getUserDetailWithAddress(userId)),
//                        Users.class, UserDataDto.class).getMappedResults());

        List<UserDataDto> userDataDtoList = mongoTemplate
                .aggregate(getAggregationStages(type),
                        Users.class, UserDataDto.class).getMappedResults();

        return userDataDtoList;
    }

    public static Aggregation getAggregationStages(String type) {
        Aggregation aggregation = null;

        switch (Integer.parseInt(type)) {
            case 1:
                LookupOperation lookupOperation1 = Aggregation.lookup("kite_user_user_address", "_id", "user_id", "address");
                ProjectionOperation projectionOperation1 = Aggregation.project(userAddressFields)
                        .andInclude("address.address_id", "address.address_line1", "address.address_line2",
                                "address.address_line3", "address.address_type", "address.city", "address.state");
                aggregation = Aggregation.newAggregation(lookupOperation1, projectionOperation1);
                break;
            case 2:
                MatchOperation matchOperation2 = Aggregation.match(Criteria.where("status").is("ACTIVE"));
                LookupOperation lookupOperation2 = Aggregation.lookup("kite_user_user_address", "_id", "user_id", "address");
                ProjectionOperation projectionOperation2 = Aggregation.project(userAddressFields)
                        .andInclude("address.address_id", "address.address_line1", "address.address_line2",
                                "address.address_line3", "address.address_type", "address.city", "address.state");
                aggregation = Aggregation.newAggregation(matchOperation2, lookupOperation2, projectionOperation2);
                break;
            case 3:
                MatchOperation matchOperation3 = Aggregation.match(Criteria.where("status").is("ACTIVE"));
                LookupOperation lookupOperation3 = Aggregation.lookup("kite_user_user_address", "_id", "user_id", "address");
                UnwindOperation unwindOperation3 = Aggregation.unwind("address");
                ProjectionOperation projectionOperation3 = Aggregation.project(userAddressFields)
                        .andInclude("address.address_id", "address.address_line1", "address.address_line2",
                                "address.address_line3", "address.address_type", "address.city", "address.state");
                aggregation = Aggregation.newAggregation(matchOperation3, lookupOperation3, unwindOperation3, projectionOperation3);
                break;
            case 4:
                MatchOperation matchOperation4 = Aggregation.match(Criteria.where("status").is("ACTIVE"));
                LookupOperation lookupOperation4 = Aggregation.lookup("kite_user_user_address", "_id", "user_id", "address");
                UnwindOperation unwindOperation4 = Aggregation.unwind("address");
                ProjectionOperation projectionOperation4 = Aggregation.project(userAddressFields)
                        .andInclude("address.address_id", "address.address_line1", "address.address_line2",
                                "address.address_line3", "address.address_type", "address.city", "address.state");
                aggregation = Aggregation.newAggregation( matchOperation4, lookupOperation4, unwindOperation4,
                        Aggregation.project().andExpression("address.city").as("particular_address_type"));
                break;
        }

        return aggregation;
    }

    // @Deprecated
//    private static List<AggregationOperation> getActiveEmployeesAndUpdatedAggregationOperationsKycAndCard(String userId) {
//        Criteria matchCriteria;
        //Get all users having status as ACTIVE, PENDING_VERIFICATION or SUSPENDED

//            matchCriteria = new Criteria("status").is(1)
//                    .and("status").in(1);
//        MatchOperation initialMatch = match(matchCriteria);
//
        //Remove all users not matching the program
//        LookupOperation lookupRegisteredPrograms = applyLookUp(USER_SCHEMA + SCHEMA_TABLE_SEPRATOR + REGISTERED_PROGRAMS_TABLE
//                , Fields.UNDERSCORE_ID, "user_id", "program");
//        MatchOperation registeredProgramCriteria = match(new Criteria("program.enterprise_id").is(enterpriseId)
//                .and("program.kite_program").is(program));
//
//        //get all KYC data
//        LookupOperation lookupKycData = applyLookUp(USER_SCHEMA + SCHEMA_TABLE_SEPRATOR + KYC_TABLE
//                , Fields.UNDERSCORE_ID, "user_id", "kyc");
//
//        //get all card data
//        LookupOperation lookupCardData = applyLookUp(CARD_SCHEMA + SCHEMA_TABLE_SEPRATOR + CARD_TABLE
//                , Fields.UNDERSCORE_ID, "user_id", "card");
//        ProjectionOperation projectionUsersDataAndKYCAndCardFinal = camelCase_To_snake_case(userFieldsForActiveEmployeesCamelCase)
//                .andInclude("kyc").andInclude("card");
//
//        List<AggregationOperation> list = new ArrayList<>();
//        list.add(initialMatch);
//        return list;
//        return newArrayList(initialMatch, lookupRegisteredPrograms, registeredProgramCriteria,
//                lookupKycData, lookupCardData, projectionUsersDataAndKYCAndCardFinal,
//                sort(Sort.Direction.DESC, "modified_on", "created_on"));
//    }

    private static <T> T getDTOObject(List<T> list) {
        if(CollectionUtils.isEmpty(list))
            return null;
        else
            return list.get(0);
    }

    private Aggregation allowDiskUse(Aggregation aggregation) {
        return aggregation.withOptions(new AggregationOptions.Builder().allowDiskUse(true).build());
    }
}
