package wevioz.social_network.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter(AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class Comment {
    private static AtomicInteger nextId = new AtomicInteger(0);
    private int id = nextId.getAndIncrement();
    private final Post post;
    private final User owner;
    private final String content;
    private LocalDate creationTime = LocalDate.now();
    public static int textLimit = 100;
}