package wevioz.social_network.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class StatDto implements Serializable {
    private String city;
    private String country;
    private String name;
    private LocalDate creationDate;
}
