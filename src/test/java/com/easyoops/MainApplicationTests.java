package com.easyoops;

import com.easyoops.biz.sample.entity.Sample;
import com.easyoops.biz.sample.service.SampleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MainApplicationTests {

    private static final Logger LOG = LoggerFactory.getLogger(MainApplicationTests.class);

    @Autowired
    SampleService sampleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Test
    void contextLoads() {
        LOG.info("test start");
        Sample sample = sampleService.selectSampleView(1000000001);

        LOG.info("no 1 > " + sample.getNo());
        LOG.info("title 1 > " + sample.getTitle());

        String encode = passwordEncoder.encode("1234");
        LOG.info("encode > " + encode);
    }

}
