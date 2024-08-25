package effective.mobile.TaskManagmentSytem.interfaces;

import effective.mobile.TaskManagmentSytem.models.Comment;
import effective.mobile.TaskManagmentSytem.models.Task;

import java.util.List;

public interface TaskServiceInterface {

    boolean putNewTask(String title, String text, String priority, String authorEmail);

    boolean updateExistsTask(Long task_id, String updating_column, String updated_value);

    boolean deleteTaskById(Long task_id);

    Task getTaskById(Long task_id);

    List<Task> getAllTasksOfAuthor(Long author_id);

    List<Task> getAllTasksOfExecutor(Long executor_id);

    List<Task> getAllTasks();

    boolean putNewComment(Long task_id, String text);

    List<Comment> getAllCommentsOfTask(Long task_id);
}
