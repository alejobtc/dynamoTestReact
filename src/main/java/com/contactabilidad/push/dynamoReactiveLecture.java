package com.contactabilidad.push;

//import com.amazonaws.auth.BasicAWSCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClientBuilder;

@Configuration
public class dynamoReactiveLecture {

    @Bean
    public DynamoDbAsyncClient dynamoDbAsyncClient() {
         DynamoDbAsyncClientBuilder dynamoDbAsyncClientBuilder = DynamoDbAsyncClient.builder()
                .region(Region.of(Region.US_EAST_1.id()))
                 .credentialsProvider(DefaultCredentialsProvider.builder().build());

//                 .credentialsProvider(DefaultCredentialsProvider.builder().build());

         return dynamoDbAsyncClientBuilder.build();
    }
}
