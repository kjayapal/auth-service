package com.ftd.auth.api.response;

import java.io.Serializable;

import com.ftd.auth.data.User;

public class ServiceResponse implements Serializable {
    private static final long serialVersionUID = -3228807230740133965L;
    private User user;
    private String status;

    public ServiceResponse() {
        this.user = null;
        this.status = "error";
    }

    public ServiceResponse(User user) {
        this.user = user;
        this.status = "success";
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ServiceResponse [user=" + user + ", status=" + status + "]";
    }
}
