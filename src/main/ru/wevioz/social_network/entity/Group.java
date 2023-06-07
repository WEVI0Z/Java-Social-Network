package wevioz.social_network.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Setter
@Getter
@RequiredArgsConstructor
public class Group {
    private final int id;
    private final String name;
    private ArrayList<User> participants = new ArrayList<>();
}