package com.small.rqspringcloudd.service;

import com.small.rqspringcloudd.service.callback.HiServiceCallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import service.HiCommonService;

@FeignClient(value = "eurekaclientbase", fallback = HiServiceCallback.class)
public interface HiService extends HiCommonService {
}
