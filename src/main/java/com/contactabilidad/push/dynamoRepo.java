package com.contactabilidad.push;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import java.util.HashMap;
import java.util.Map;

@Repository
public class dynamoRepo {

    @Autowired
    DynamoDbAsyncClient dynamoDbAsyncClient;
    UserMapper mapper = new UserMapper();



    public Mono<User> saveUser(User user){

        Map<String, AttributeValue> map = mapper.toMap(user);
        PutItemRequest putItemRequest = PutItemRequest.builder()
                .tableName("contactabilidad")
                .item(map)
                .build();
        return Mono.fromCompletionStage(dynamoDbAsyncClient.putItem(putItemRequest))
                .flatMap((u)->{
                   return getUser(user.getUuid());
                });


    }

    public Mono<User> getUser(String id){
        Map<String,AttributeValue> map = new HashMap<String,AttributeValue>();
        map.put("uuid",AttributeValue.builder().s(id).build());
        GetItemRequest getItemRequest = GetItemRequest.builder()
                .key(map)
                .tableName("contactabilidad")
                .build();

        return Mono.fromCompletionStage(dynamoDbAsyncClient.getItem(getItemRequest))
                .map(res->mapper.fromMap(res.item())).log();
    }

}
