package effective.mobile.TaskManagmentSytem.controllers;

import effective.mobile.TaskManagmentSytem.dto.requests.*;
import effective.mobile.TaskManagmentSytem.dto.responses.MessageResponse;
import effective.mobile.TaskManagmentSytem.interfaces.TaskControllerInterface;
import effective.mobile.TaskManagmentSytem.interfaces.TaskServiceInterface;
import effective.mobile.TaskManagmentSytem.models.Comment;
import effective.mobile.TaskManagmentSytem.models.Task;
import effective.mobile.TaskManagmentSytem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task-manager")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TaskManageController implements TaskControllerInterface {

    @Autowired
    TaskServiceInterface taskManageService;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/get-task-for-id")
    @Override
    public Task getTaskForId(@RequestBody ReadTaskRequest request) {
        Task task = taskManageService.getTaskById(request.getTask_id());
        return task;
    }

    @GetMapping("/get-all-author-tasks")
    @Override
    public List<Task> getAllTasksOfAuthor(@RequestBody GetAllTasksUserRequest request) {
        List<Task> result = taskManageService.getAllTasksOfAuthor(request.getUser_id());
        return result;
    }

    @GetMapping("/get-all-executor-tasks")
    @Override
    public List<Task> getAllTasksOfExecutor(@RequestBody GetAllTasksUserRequest request) {
        List<Task> result = taskManageService.getAllTasksOfExecutor(request.getUser_id());
        return result;
    }

    @GetMapping("/get-all-tasks")
    @Override
    public List<Task> getAllTasks() {
        return taskManageService.getAllTasks();
    }

    @Override
    @PostMapping("/put-new-task")
    public ResponseEntity<?> putNewTask(@RequestBody PutNewTaskRequest request) {
        String authorEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        if (taskManageService.putNewTask(
                request.getTitle(),
                request.getText(),
                authorEmail,
                request.getPriority())) {
            return ResponseEntity.ok(new MessageResponse("Task is successfully add"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Something wrong"));
        }
    }


    @Override
    @PostMapping("/delete-task")
    public ResponseEntity<?> deleteTask(@RequestBody DeleteTaskRequest request) {
        if (taskManageService.deleteTaskById(request.getTask_id())) {
            return ResponseEntity.ok(new MessageResponse("Task successfully delete"));
        } else {
            return ResponseEntity.badRequest().body(new MessageResponse("Something wrong"));
        }
    }

    @Override
    @PostMapping("/update-task")
    public ResponseEntity<?> updateExistsTask(@RequestBody UpdateExistsTaskRequest request) {
        if (taskManageService.updateExistsTask(
                request.getTask_id(),
                request.getUpdating_column(),
                request.getUpdated_value()
        )) {
            return ResponseEntity.ok(new MessageResponse("Task successfully updated"));
        } else {
            return ResponseEntity.badRequest().body("Something wrong");
        }
    }

    @Override
    public ResponseEntity<?> putNewComment(@RequestBody PutNewCommentRequest request) {
        if (taskManageService.putNewComment(request.getTask_id(), request.getText())) {
            return ResponseEntity.ok(new MessageResponse("Comment successfully put"));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("Something wrong"));
    }

    @Override
    public List<Comment> getAllCommentOfTask(GetAllCommentsRequest request) {
        List<Comment> result = taskManageService.getAllCommentsOfTask(request.getTask_id());
        return result;
    }
}
