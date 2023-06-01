package wevioz.social_network.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Getter
@Setter
@RequiredArgsConstructor
public class User {
    private final int id;
    private final String email;
    private ArrayList<Post> posts = new ArrayList<>();
}