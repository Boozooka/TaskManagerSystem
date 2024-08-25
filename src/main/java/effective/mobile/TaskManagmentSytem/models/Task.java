package effective.mobile.TaskManagmentSytem.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private String title;

    @NotNull
    private String text;

    @NotNull
    private String status;

    @Column(length = 20)
    @NotNull
    private String priority;

    @NotNull
    Long author_id;

    Long executor_id;

    public Task(String title, String text, Long author_id, String priority) {
        this.title = title;
        this.text = text;
        this.author_id = author_id;
        this.executor_id = null;
        this.priority = priority;
        this.status = "В ожидании";
    }
}
