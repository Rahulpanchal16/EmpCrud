package com.emp.crud.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.cognitoidentityprovider.CognitoIdentityProviderClient;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserRequest;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AdminCreateUserResponse;
import software.amazon.awssdk.services.cognitoidentityprovider.model.AttributeType;

@Service
public class UserService {

    @Value("${aws.region}")
    private String region;

    @Value("${aws.cognito.userPoolId}")
    private String userPoolId;

    private CognitoIdentityProviderClient getCognitoClient() {
        
        return CognitoIdentityProviderClient.builder()
                .region(Region.of(region))
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public String createUser(String username, String email, String password) {
        CognitoIdentityProviderClient cognitoClient = getCognitoClient();

        AdminCreateUserRequest request = AdminCreateUserRequest.builder()
                .userPoolId(userPoolId)
                .username(username)
                .temporaryPassword(password)
                .userAttributes(
                        AttributeType.builder().name("email").value(email).build(),
                        AttributeType.builder().name("email_verified").value("true").build()
                )
                .messageAction("SUPPRESS") // Suppress sending welcome email
                .build();

        AdminCreateUserResponse response = cognitoClient.adminCreateUser(request);

        return response.user().username();
    }
}
