package dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.codehaus.jackson.map.annotate.JsonSerialize;


@Data
@Builder
@AllArgsConstructor
@JsonSerialize
public class User {
    private String email;
    private String firstName;
    @NonNull
    private Long id;
    private String lastName;
    @NonNull
    private String password;
    private String phone;
    private Long userStatus;
    private String username;
}
