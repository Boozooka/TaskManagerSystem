package effective.mobile.TaskManagmentSytem.dto.responses;

import lombok.Data;

@Data
public class JWTResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String email;

    public JWTResponse(String token, Long id, String email){
        this.token = token;
        this.email = email;
        this.id = id;
    }
}
