package effective.mobile.TaskManagmentSytem.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PutNewCommentRequest {
    Long task_id;
    String text;
}
