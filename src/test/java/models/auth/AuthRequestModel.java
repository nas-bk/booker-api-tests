package models.auth;

import lombok.Data;

@Data
public class AuthRequestModel {
    String username;
    String password;
}
