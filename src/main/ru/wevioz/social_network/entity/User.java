package wevioz.social_network.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter(AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class User {
    private static AtomicInteger nextId = new AtomicInteger(0);
    private int id = nextId.getAndIncrement();
    private final String email;
    private ArrayList<Post> posts = new ArrayList<>();

    public void addPost(Post post) {
        posts.add(post);
    }
}