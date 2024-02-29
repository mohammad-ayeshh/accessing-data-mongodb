package com.example.accessingdatamongodb.Auth;

import lombok.Data;
import org.springframework.stereotype.Service;
@Data
@Service
public class AuthService {

    private String authToken;

    public boolean isTokenPresent() {
        return authToken != null && !authToken.isEmpty();
    }

}
