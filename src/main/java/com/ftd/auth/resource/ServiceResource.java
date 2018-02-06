package com.ftd.auth.resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ftd.auth.api.request.ServiceRequest;
import com.ftd.auth.api.response.ServiceResponse;
import com.ftd.auth.bl.MyService;
import com.ftd.auth.data.User;
import com.ftd.auth.data.UserRepository;

@RestController
@RefreshScope
@CrossOrigin
@RequestMapping("/apis")
public class ServiceResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServiceResource.class);

    @Autowired
    private MyService myService;

    @Autowired
    private UserRepository aclsRepository;

//    private RestTemplate restTemplate;
//
//    @Autowired
//    public void setRestTemplate(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    @PostMapping(value = "/getrole", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @Cacheable(cacheNames = "acls")
    public ResponseEntity<String> getRole(@RequestBody ServiceRequest request) {
        ServiceResponse serviceResponse = null;
        LOGGER.info("Called get acls method with all values.... ");
        System.out.println(request.toString());
        if (request.getUserName() != null
                && request.getPassword() != null) {
            serviceResponse = myService.getResponse(request.getUserName(), request.getPassword());
        } else {
            serviceResponse = new ServiceResponse();
            serviceResponse.setStatus("Invlid input data");
        }

        ObjectMapper mapper = new ObjectMapper();
        String jsonResponse;
        try {
            jsonResponse = mapper.writeValueAsString(serviceResponse);
        } catch (JsonProcessingException e) {
            jsonResponse = "{status : \"Unexpected error\"}";
        }

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }

    @PostMapping(value = "/addrole", consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    @Cacheable(cacheNames = "addacls")
    public ResponseEntity<String> addRole(@RequestBody User request) {
        String jsonResponse;
        LOGGER.info("Called add acls method with all values.... ");
        if (request.getUserName() != null
                && request.getPassword() != null) {

            User user = new User(null, request.getUserName(), request.getPassword(), request.getRole());
            aclsRepository.save(user);
            jsonResponse = "{status: success}";
        } else {
            jsonResponse = "{status: error, error: Invlid input data}";
        }

        return new ResponseEntity<>(jsonResponse, HttpStatus.OK);
    }
}
