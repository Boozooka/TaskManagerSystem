package effective.mobile.TaskManagmentSytem.interfaces;

import effective.mobile.TaskManagmentSytem.dto.requests.*;
import effective.mobile.TaskManagmentSytem.models.Comment;
import effective.mobile.TaskManagmentSytem.models.Task;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TaskControllerInterface {

    Task getTaskForId(ReadTaskRequest request);

    List<Task> getAllTasksOfAuthor(GetAllTasksUserRequest request);

    List<Task> getAllTasksOfExecutor(GetAllTasksUserRequest request);

    List<Task> getAllTasks();

    ResponseEntity<?> putNewTask(PutNewTaskRequest request);

    ResponseEntity<?> deleteTask(DeleteTaskRequest request);

    ResponseEntity<?> updateExistsTask(UpdateExistsTaskRequest request);

    ResponseEntity<?> putNewComment(PutNewCommentRequest request);

    List<Comment> getAllCommentOfTask(GetAllCommentsRequest request);
}
