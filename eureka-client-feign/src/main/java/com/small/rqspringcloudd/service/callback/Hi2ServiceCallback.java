package com.small.rqspringcloudd.service.callback;

import com.small.rqspringcloudd.service.Hi2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Hi2ServiceCallback implements Hi2Service {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public String sayHi2(String name) {
        logger.info("短路后我是另一种方式2");
        return "短路后我是另一种方式2:" + name;
    }
}
