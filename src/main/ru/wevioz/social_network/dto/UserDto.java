package wevioz.social_network.dto;

import lombok.*;

import java.io.Serializable;

@Data
public class UserDto implements Serializable {
    private int id;
    private String email;
    private String password;
    private String role;
    private String city;
    private String country;
}