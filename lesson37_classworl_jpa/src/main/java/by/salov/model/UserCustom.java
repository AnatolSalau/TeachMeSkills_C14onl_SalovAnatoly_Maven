package by.salov.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder

@Entity
public class UserCustom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private String password;
    private Boolean isActive;

    @Temporal(TemporalType.DATE)
    private Date birthday;


    private Boolean isDelete = false;

    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date updated;
    @Version
    private Long version;

}
