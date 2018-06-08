package com.example.c4q.dogsapp.data;

import java.util.List;

/**
 * Created by c4q on 6/6/18.
 */

public class DogApiResponse {
    private String status;
    private List<String> message;

    public DogApiResponse(String status, List<String> message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getMessage() {
        return message;
    }

    public void setMessage(List<String> message) {
        this.message = message;
    }
}
