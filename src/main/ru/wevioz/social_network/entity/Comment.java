package wevioz.social_network.entity;

import lombok.*;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
public class Comment {
    private final int id;
    private Post post;
    private User owner;
    private String content;
    private final LocalDate creationTime = LocalDate.now();
}