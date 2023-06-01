package wevioz.social_network.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter(AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Post {
    private static AtomicInteger nextId = new AtomicInteger(0);
    private int id = nextId.getAndIncrement();
    private final String content;
    private final User owner;
    private ArrayList<Comment> comments = new ArrayList<>();
    private LocalDate creationDate = LocalDate.now();
    public static int textLimit = 200;

    public void addComment(Comment comment) {
        comments.add(comment);
    }
}