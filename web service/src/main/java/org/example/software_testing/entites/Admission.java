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
public class Admission {
    @Id
      Integer id;
      Date admissionDate;
      Date dischargeDate;
      Integer patientID;
}
