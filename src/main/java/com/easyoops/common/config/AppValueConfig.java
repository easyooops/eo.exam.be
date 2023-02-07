package com.easyoops.common.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.validation.Valid;

@Data
@Valid
@Configuration
public class AppValueConfig {

    @Value("${app.db.value.writer}")
    private String appDbWriter;
}
