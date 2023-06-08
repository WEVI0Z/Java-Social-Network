package wevioz.social_network.entity;

import lombok.*;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "groups")
public class Group {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_group",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "group_id") }
    )
    private ArrayList<User> participants = new ArrayList<>();

    public Group(
            int id,
            String name
    ) {
        this.id = id;
        this.name = name;
    }
}