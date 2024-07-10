package org.example.software_testing.entites;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@Entity
@Data
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Allocation {
    @Id
     Integer id;
     Integer admissionID;
     Integer employeeID;
     Date startTime;
     Date endTime;
}
