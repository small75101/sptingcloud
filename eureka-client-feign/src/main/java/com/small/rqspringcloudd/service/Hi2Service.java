package com.small.rqspringcloudd.service;

import com.small.rqspringcloudd.service.callback.Hi2ServiceCallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "eurekaclientribbon", fallback = Hi2ServiceCallback.class)
public interface Hi2Service {
    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    String sayHi2(@RequestParam(value = "name") String name);
}
