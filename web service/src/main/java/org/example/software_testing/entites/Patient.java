package org.example.software_testing.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    @Id
    private Integer id;
    private String surname;
    private String forename;
    private String nhsNumber;
}
