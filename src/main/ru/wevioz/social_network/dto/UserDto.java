package wevioz.social_network.dto;

import lombok.*;

@Data
public class UserDto {
    private int id;
    private String email;
    private String password;
    private String role;
}