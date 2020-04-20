package com.small.rqspringcloudd.service.callback;

import com.small.rqspringcloudd.service.HiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class HiServiceCallback implements HiService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String sayHi(String name) {
        logger.info("短路后我是另一种方式");
        return "短路后我是另一种方式:" + name;
    }
}
