package bj.delivery.api.domain.rabbitmqtest.controller;

import bj.delivery.api.common.rabbitmq.Producer;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/open-api/heatlh")
@RequiredArgsConstructor
@Tag(name = "RabbitMqTest", description = "RabbitMq Test Open API Document")
public class HealthOpenApiController {

//    private final Producer producer;

    @GetMapping("/test")
    public void test(){
        log.info("test call");
//        producer.producer("delivery.exchange", "delivery.key", "test");
    }
}
