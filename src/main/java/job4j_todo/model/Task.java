package job4j_todo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
@Entity
@Data
@Table(name = "tasks")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@NonNull
@Getter
@Setter
@ToString
public class Task implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @EqualsAndHashCode.Include
    private String description;

    private Timestamp created;

    private boolean done;

}
