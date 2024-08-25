package effective.mobile.TaskManagmentSytem.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PutNewTaskRequest {
    String title;
    String text;
    String priority;
}
