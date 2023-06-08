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

    @JsonIgnore
    @OneToMany(mappedBy = "owner")
    private List<Post> posts;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private List<Comment> comments;

    @JsonIgnore
    @ManyToMany(mappedBy = "participants")
    private List<Group> groups;

    public User(String email) {
        this.email = email;
    }
}