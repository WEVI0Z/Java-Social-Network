package wevioz.social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Table;
import lombok.*;

import jakarta.persistence.*;

import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq_generator")
    @SequenceGenerator(name = "user_seq_generator", sequenceName = "users_id_seq", allocationSize = 1)
    @Column(nullable = false, unique = true)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Post> posts;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Comment> comments;

    @ManyToMany(mappedBy = "participants")
    @JsonIgnore
    private List<Group> groups;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }
}