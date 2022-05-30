package com.easyoops.ext.aws.config;

import com.amazonaws.auth.InstanceProfileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.secretsmanager.caching.SecretCache;
import com.amazonaws.secretsmanager.caching.SecretCacheConfiguration;
import com.amazonaws.services.secretsmanager.AWSSecretsManager;
import com.amazonaws.services.secretsmanager.AWSSecretsManagerClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AwsClientConfiguration {


    static final Regions defaultRegion = Regions.EU_CENTRAL_1; // Default Regions

    @Bean
    public AWSSecretsManager awsSecretsManager() {
        return AWSSecretsManagerClientBuilder.standard()
                .withRegion(defaultRegion)
                .withCredentials(InstanceProfileCredentialsProvider.getInstance())
                .build();
    }

    @Bean
    public SecretCacheConfiguration secretCacheConfiguration(AWSSecretsManager awsSecretsManager) {
        return new SecretCacheConfiguration().withClient(awsSecretsManager).withCacheItemTTL(600000);
    }

    @Bean
    public SecretCache secretCache(SecretCacheConfiguration secretCacheConfiguration) {
        return new SecretCache(secretCacheConfiguration);
    }

}
