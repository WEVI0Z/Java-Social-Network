package wevioz.social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "post_seq_generator")
    @SequenceGenerator(name = "post_seq_generator", sequenceName = "posts_id_seq", allocationSize = 1)
    @Column(nullable = false, unique = true)
    private int id;

    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private List<Comment> comments;

    @Column(name = "creation_date")
    private LocalDate creationDate = LocalDate.now();

    public Post(String content, User owner) {
        this.content = content;
        this.owner = owner;
    }
}