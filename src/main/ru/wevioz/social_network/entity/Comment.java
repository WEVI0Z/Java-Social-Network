package wevioz.social_network.entity;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq_generator")
    @SequenceGenerator(name = "comment_seq_generator", sequenceName = "groups_id_seq", allocationSize = 1)
    @Column(nullable = false, unique = true)
    private int id;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    @Column(name = "content")
    private String content;

    @Column(name = "creation_time")
    private final LocalDate creationTime = LocalDate.now();

    public Comment(
            int id,
            String content,
            User owner,
            Post post
    ) {
        this.id = id;
        this.content = content;
        this.owner = owner;
        this.post = post;
    }
}