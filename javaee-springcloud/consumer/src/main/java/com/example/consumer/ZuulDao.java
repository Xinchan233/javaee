package com.example.consumer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("zuul")
public interface ZuulDao {
    @RequestMapping(value = "/hello")
    public String toZuul();
}
