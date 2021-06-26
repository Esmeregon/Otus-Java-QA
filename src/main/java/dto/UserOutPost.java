package dto;

import lombok.*;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@Data
@Builder
@AllArgsConstructor
@JsonSerialize
@NoArgsConstructor
@NonNull
public class UserOutPost {
    private Long code;
    private String message;
    private String type;
}
