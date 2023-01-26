package com.easyoops;

import com.easyoops.biz.sample.dto.SampleDTO;
import com.easyoops.biz.sample.entity.SampleEntity;
import com.easyoops.biz.sample.mapper.SampleMapper;
import com.easyoops.biz.sample.repository.SampleRepository;
import com.easyoops.biz.sample.service.SampleService;
import com.easyoops.common.config.DataSourceConfig;
import com.mysql.cj.log.Log;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Optional;

@WebAppConfiguration
@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class EasyoopsApplicationTests {

	private static final Logger LOG = LoggerFactory.getLogger(EasyoopsApplicationTests.class);

	@Autowired
	SampleService sampleService;

	@Test
	void contextLoads() {
		LOG.info("test start");
		SampleDTO sampleDTO = sampleService.selectSampleView("1");

		LOG.info("no 1 > " + sampleDTO.getNo());
		LOG.info("title 1 > " + sampleDTO.getTitle());
//		SampleService sampleService = new SampleService();
		SampleEntity sampleEntity = sampleService.findByNo("2");
//		LOG.info("test end");
		LOG.info("no 2 > " + sampleEntity.getNo());
		LOG.info("title 2 > " + sampleEntity.getTitle());

	}

}
