package wevioz.social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@AllArgsConstructor
public class Post {
    private final int id;
    private final String content;
    private final User owner;
    private final ArrayList<Comment> comments = new ArrayList<>();
    private final LocalDate creationDate = LocalDate.now();
}