package effective.mobile.TaskManagmentSytem.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignupRequest {
    private String email;
    private String password;
}
