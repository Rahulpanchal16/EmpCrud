package com.emp.crud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;

@RestController
@RequestMapping(path = "api/")
public class AwsTestEndpoint {
    @GetMapping(path = "/test-aws")
    public String testAws() {
        AwsCredentials credentials = DefaultCredentialsProvider.create().resolveCredentials();
        return "Access Key: " + credentials.accessKeyId();
    }
}
