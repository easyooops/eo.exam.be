package com.easyoops.ext.aws.sm;

import com.amazonaws.secretsmanager.caching.SecretCache;
import com.easyoops.common.enums.SecretKey;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
public class SecretManager {

    private final SecretCache secretCache;
    private final ObjectMapper objectMapper;

    SecretManager(SecretCache secretCache, ObjectMapper objectMapper) {
        this.secretCache = secretCache;
        this.objectMapper = objectMapper;
    }

    public Object getSecretValue(SecretKey secretKey, String key) throws Exception {
        Map<String, Object> secretMap = getSecretMap(secretKey);
        return secretMap.get(key);
    }

    private Map<String, Object> getSecretMap(SecretKey secretKey) throws Exception {
        return objectMapper.readValue(getSecret(secretKey.getName()), new TypeReference<HashMap<String, Object>>() {
        });
    }

    private String getSecret(String key) throws Exception {

        String secret = "";
        try {
            // Decrypts secret using the associated KMS key.
            // Depending on whether the secret is a string or binary, one of these fields will be populated.
            if (null != secretCache.getSecretString(key)) {
                secret = secretCache.getSecretString(key);
            } else {
                secret = new String(Base64.getDecoder().decode(secretCache.getSecretBinary(key)).array());
            }

        } catch (Exception e) {
            log.error("secret key : {} , Error : {}", key, e.getMessage());
            secretCache.refreshNow(key);
        }
        return secret;
    }

}
