package Workflow.example.Workflow.Entity;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class User  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String userFirstName;
    private String email;
    private String userLastName;
    private String userPassword;

}