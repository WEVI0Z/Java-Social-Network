package wevioz.social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany
    private ArrayList<Comment> comments = new ArrayList<>();

    @Column(name = "creation_date")
    private final LocalDate creationDate = LocalDate.now();

    public Post(
            int id,
            String content,
            User owner
    ) {
        this.id = id;
        this.content = content;
        this.owner = owner;
    }
}