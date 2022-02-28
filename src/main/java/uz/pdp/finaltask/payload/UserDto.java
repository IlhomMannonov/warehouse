package uz.pdp.finaltask.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {

    private Long id;
    private String name;
    private String lastName;
    private String phoneNumber;
    private String password;
    private boolean active;


}
