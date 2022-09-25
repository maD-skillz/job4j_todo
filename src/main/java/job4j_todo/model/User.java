package job4j_todo.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Data
@Table(name = "users")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@NoArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NonNull
    @EqualsAndHashCode.Include
    private String name;

    @NonNull
    @EqualsAndHashCode.Include
    private String login;

    @NonNull
    @EqualsAndHashCode.Include
    private String password;

}
