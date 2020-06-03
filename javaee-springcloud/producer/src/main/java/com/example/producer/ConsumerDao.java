package com.example.producer;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient("consumer")
public interface ConsumerDao {
    @RequestMapping(value = "/toZuul")
    public String toConsumer();
}
