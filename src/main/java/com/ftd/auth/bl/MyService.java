package com.ftd.auth.bl;

import com.ftd.auth.api.response.ServiceResponse;

public interface MyService {
    ServiceResponse getResponse();
    ServiceResponse getResponse(String id);
    ServiceResponse getResponse(String userName, String password);
}
