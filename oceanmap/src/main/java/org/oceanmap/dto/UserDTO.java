package org.oceanmap.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.oceanmap.model.User;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String name;
    private String email;
    private String password;
    private Boolean enabled;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.enabled = user.getActive();
    }

}
