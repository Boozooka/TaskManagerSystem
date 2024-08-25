package effective.mobile.TaskManagmentSytem.models;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    private Long owner_id;

    @NotNull
    private Long task_id;

    @NotNull
    private String text;
}
