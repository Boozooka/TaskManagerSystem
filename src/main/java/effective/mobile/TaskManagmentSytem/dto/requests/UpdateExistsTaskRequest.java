package effective.mobile.TaskManagmentSytem.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateExistsTaskRequest {
    Long task_id;
    String updating_column;
    String updated_value;
}
