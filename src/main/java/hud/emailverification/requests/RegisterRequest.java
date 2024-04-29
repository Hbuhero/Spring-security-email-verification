package hud.emailverification.requests;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
public class RegisterRequest {
    private String fullName;
    private String email;
    private String password;
}
