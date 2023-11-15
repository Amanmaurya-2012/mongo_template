package com.example.mongoTemplate.service.Impl;

import com.example.mongoTemplate.dto.user.UserDataDto;
import com.example.mongoTemplate.entity.Users;
import com.example.mongoTemplate.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationOptions;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestServiceImpl implements TestService {

    private final MongoTemplate mongoTemplate;

    @Override
    public UserDataDto getUserDetails(String userId) {

//        UserDataDto userDataDto = getDTOObject(mongoTemplate
//                .aggregate(allowDiskUse(getUserDetailWithAddress(userId)),
//                        Users.class, UserDataDto.class).getMappedResults());

        UserDataDto userDataDto = getDTOObject(mongoTemplate
                .aggregate(getAggregationStages(userId),
                        Users.class, UserDataDto.class).getMappedResults());

        return userDataDto;
    }

    public static Aggregation getAggregationStages(String userId) {
        Aggregation aggregation = null;

        switch (Integer.parseInt(userId)) {
            case 1:
                aggregation = Aggregation.newAggregation(
                        Aggregation.lookup("kite_user_user_address", "_id", "user_id", "address"));
                break;
            case 2:
                aggregation = Aggregation.newAggregation(
                        Aggregation.match(Criteria.where("status").is("ACTIVE")),
                        Aggregation.lookup("kite_user_user_address", "_id", "user_id", "address"));
                break;
            case 3:
                aggregation = Aggregation.newAggregation(
                        Aggregation.match(Criteria.where("status").is("ACTIVE")),
                        Aggregation.lookup("kite_user_user_address", "_id", "user_id", "address"),
                        Aggregation.unwind("address"));
                break;
            case 4:
                aggregation = Aggregation.newAggregation(
                        Aggregation.match(Criteria.where("status").is("ACTIVE")),
                        Aggregation.lookup("kite_user_user_address", "_id", "user_id", "address"),
                        Aggregation.unwind("address"),
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
