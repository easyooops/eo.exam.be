package com.easyoops.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GlobalAspect {

    private static final Logger LOG = LoggerFactory.getLogger("BIZ_LOGGER");

    @Before("execution(* com.easyoops.biz.sample.service.SampleService.select*(..)) "
            + "|| execution(* com.easyoops.biz.sample.service.SampleService.create*(..))"
            + "|| execution(* com.easyoops.biz.sample.service.SampleService.update*(..))"
    )
    public void sampleAspect(JoinPoint joinPoint) {
        // TODO: 2022-05-30  
//        RequestChunk reqChunk = new RequestChunk();
//        Map<String, Object> reqMap = new LinkedHashMap<>();
//
//        for (Object obj : joinPoint.getArgs()) {
//            if (obj instanceof RequestChunk) {
//                reqChunk = (RequestChunk) obj;
//            } else {
//                reqMap = (Map<String, Object>) obj;
//            }
//        }
    }

}
