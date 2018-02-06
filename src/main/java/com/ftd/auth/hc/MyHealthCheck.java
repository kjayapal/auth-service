package com.ftd.auth.hc;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class MyHealthCheck implements HealthIndicator {

//    private MyService myService;


    @Override
    public Health health() {
        return Health.up().build();
//        ServiceResponse serviceResponse = myService.getResponse("test");
//        if (serviceResponse.getMessage().equals("Hello test")) {
//            return Health.up().build();
//        }
//        return Health
//                .down()
//                .withDetail("Error Code", 1)
//                .withException(new Exception("myService could not return result"))
//                .build();
    }
}
