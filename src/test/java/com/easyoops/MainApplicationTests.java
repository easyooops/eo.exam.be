package com.easyoops;

import com.easyoops.biz.sample.entity.SampleEntity;
import com.easyoops.biz.sample.service.SampleService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
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

	@Test
	void contextLoads() {
		LOG.info("test start");
		SampleEntity sampleEntity = sampleService.selectSampleView(1);

		LOG.info("no 1 > " + sampleEntity.getNo());
		LOG.info("title 1 > " + sampleEntity.getTitle());
	}

}
