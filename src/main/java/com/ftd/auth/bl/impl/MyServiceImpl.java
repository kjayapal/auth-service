package com.ftd.auth.bl.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.codahale.metrics.annotation.ExceptionMetered;
import com.codahale.metrics.annotation.Timed;
import com.ftd.auth.api.response.ServiceResponse;
import com.ftd.auth.bl.MyService;
import com.ftd.auth.config.MyConfigurationProperties;
import com.ftd.auth.data.User;
import com.ftd.auth.data.UserRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.services.micro.commons.logging.annotation.LogExecutionTime;

@Service(value = "MyService")
@EnableConfigurationProperties(MyConfigurationProperties.class)
public class MyServiceImpl implements MyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyServiceImpl.class);

    @Autowired
    private UserRepository userRepo;

    @Override
    @Timed
    @ExceptionMetered
    @HystrixCommand(groupKey = "hystrixGroup",
            commandKey = "helloCommandKey",
            threadPoolKey = "helloThreadPoolKey",
            fallbackMethod = "fallbackHello")
    @Cacheable(cacheNames = "default")
    @LogExecutionTime
    public ServiceResponse getResponse() {
        LOGGER.info("getResponse called ");
        ServiceResponse serviceResponse = new ServiceResponse();
//        List<User> user = userRepo.findAll();
        return serviceResponse;
    }


    @Override
    @Timed
    @ExceptionMetered
    @HystrixCommand(groupKey = "hystrixGroup",
            commandKey = "helloCommandKey",
            threadPoolKey = "helloThreadPoolKey",
            fallbackMethod = "fallbackHello")
    @Cacheable(cacheNames = "default")
    @LogExecutionTime
    public ServiceResponse getResponse(String id) {
        LOGGER.info("getResponse called ");
        ServiceResponse serviceResponse = new ServiceResponse();
//        User user = userRepo.findById(id);
//        serviceResponse.setMessage(acls.toJSON());
        return serviceResponse;
    }

    @Override
    @Timed
    @ExceptionMetered
    @HystrixCommand(groupKey = "hystrixGroup",
            commandKey = "helloCommandKey",
            threadPoolKey = "helloThreadPoolKey",
            fallbackMethod = "fallbackHello")
    @Cacheable(cacheNames = "default")
    @LogExecutionTime
    public ServiceResponse getResponse(String userName, String password) {
        LOGGER.info("Getting results for all given input...");
        ServiceResponse serviceResponse;

        User user = userRepo.findByUserNameIgnoreCaseAndPassword(userName, password);

        if (user == null) {
            serviceResponse = new ServiceResponse();
        } else {
            serviceResponse = new ServiceResponse(user);
        }
        return serviceResponse;
    }

    public ServiceResponse fallbackHello() {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus("error (This is Hello from fallback)");
        return serviceResponse;
    }

    public ServiceResponse fallbackHello(String userName, String password) {
        ServiceResponse serviceResponse = new ServiceResponse();
        serviceResponse.setStatus("error (This is Hello from fallback)");
        return serviceResponse;
    }
}
