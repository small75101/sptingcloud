package com.small.rqspringcloudd.controller;

import com.small.rqspringcloudd.service.Hi2Service;
import com.small.rqspringcloudd.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HiController {

    @Autowired
    HiService hiService;

    @Autowired
    Hi2Service hi2Service;


    @RequestMapping(value = "/hi", method = RequestMethod.GET)
    public String sayHi(@RequestParam String name) {
        return hiService.sayHi(name);
    }

    @RequestMapping(value = "/hi2", method = RequestMethod.GET)
    public String sayHi2(@RequestParam String name) {
        return hi2Service.sayHi2(name);
    }
}
