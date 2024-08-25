package effective.mobile.TaskManagmentSytem.services;

import effective.mobile.TaskManagmentSytem.exceptions.IllegalRequestArgumentException;
import effective.mobile.TaskManagmentSytem.interfaces.TaskServiceInterface;
import effective.mobile.TaskManagmentSytem.models.Comment;
import effective.mobile.TaskManagmentSytem.models.Task;
import effective.mobile.TaskManagmentSytem.models.User;
import effective.mobile.TaskManagmentSytem.repositories.CommentRepository;
import effective.mobile.TaskManagmentSytem.repositories.TaskRepository;
import effective.mobile.TaskManagmentSytem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskManageService implements TaskServiceInterface {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Override
    public boolean putNewTask(String title, String text, String authorEmail, String priority) {
        if (userRepository.findByEmail(authorEmail).isEmpty()) {
            return false;
        }
        Long authorId = userRepository.findByEmail(authorEmail).get().getId();

        Task task = new Task(title, text, authorId, priority);
        taskRepository.save(task);
        return true;
    }

    @Override
    public boolean updateExistsTask(Long task_id, String updating_column, String updated_value) {
        if (!taskRepository.findById(task_id).isPresent()) {
            return false;
        }
        Task task = taskRepository.findById(task_id).get();

        Long currentUserId = getCurrentUser().getId();

        if (currentUserId.equals(task.getExecutor_id())) {
            task.setTitle(updated_value);
            taskRepository.save(task);
            return true;
        }

        if (!currentUserId.equals(task.getAuthor_id())) {
            throw new IllegalRequestArgumentException("It is not your task!");
        }

        switch (updating_column) {
            case "title":
                task.setTitle(updated_value);
                taskRepository.save(task);
                return true;
            case "text":
                task.setText(updated_value);
                taskRepository.save(task);
                return true;
            case "priority":
                task.setPriority(updated_value);
                taskRepository.save(task);
                return true;
            case "executor_id":
                task.setExecutor_id(Long.parseLong(updated_value));
                taskRepository.save(task);
                return true;
            case "status":
                task.setStatus(updated_value);
                taskRepository.save(task);
                return true;
            default:
                throw new IllegalRequestArgumentException("Non-existent updating value");
        }
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public boolean putNewComment(Long task_id, String text) {
        if (taskRepository.existsById(task_id)) {
            Comment comment = new Comment(getCurrentUser().getId(), task_id, text);
            commentRepository.save(comment);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Comment> getAllCommentsOfTask(Long task_id) {
        if (!taskRepository.existsById(task_id)) {
            throw new IllegalRequestArgumentException("Id non-existent task");
        }

        List<Comment> allComments = commentRepository.findAll();
        List<Comment> result = new ArrayList<>();
        allComments.forEach(x -> {
            if (x.getTask_id().equals(task_id)) {
                result.add(x);
            }
        });

        return result;
    }

    @Override
    public boolean deleteTaskById(Long task_id) {
        if (!taskRepository.findById(task_id).isPresent()) {
            throw new IllegalRequestArgumentException("Id non-existent task");
        }

        Task task = taskRepository.findById(task_id).get();

        if (getCurrentUser().getId().equals(task.getAuthor_id())) {
            throw new IllegalRequestArgumentException("It is not your task!");
        }

        taskRepository.deleteById(task_id);

        return true;
    }

    @Override
    public Task getTaskById(Long task_id) {
        if (!taskRepository.findById(task_id).isPresent()) {
            throw new IllegalRequestArgumentException("Id non-existent task");
        }
        Task result = taskRepository.findById(task_id).get();
        return result;
    }

    @Override
    public List<Task> getAllTasksOfAuthor(Long author_id) {
        List<Task> allTasks = taskRepository.findAll();
        List<Task> result = new ArrayList<>();

        allTasks.forEach(x -> {
            if (x.getAuthor_id().equals(author_id)) {
                result.add(x);
            }
        });

        return result;
    }

    @Override
    public List<Task> getAllTasksOfExecutor(Long executor_id) {
        List<Task> allTasks = taskRepository.findAll();
        List<Task> result = new ArrayList<>();

        allTasks.forEach(x -> {
            if (x.getAuthor_id().equals(executor_id)) {
                result.add(x);
            }
        });

        return result;
    }

    private User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email).get();
    }
}
