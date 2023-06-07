package wevioz.social_network.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private ArrayList<Post> posts = new ArrayList<>();
}