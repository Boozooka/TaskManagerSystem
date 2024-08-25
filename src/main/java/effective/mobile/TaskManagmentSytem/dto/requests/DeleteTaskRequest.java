package effective.mobile.TaskManagmentSytem.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteTaskRequest {
    Long task_id;
}
