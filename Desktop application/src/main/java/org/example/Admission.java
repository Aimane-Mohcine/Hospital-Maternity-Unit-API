package org.example;

import java.util.Date;

public class Admission {

    Integer id;
    Date admissionDate;
    Date dischargeDate;
    Integer patientID;

    // Getter pour l'ID
    public Integer getId() {
        return id;
    }

    // Getter pour la date d'admission
    public Date getAdmissionDate() {
        return admissionDate;
    }

    // Getter pour la date de sortie
    public Date getDischargeDate() {
        return dischargeDate;
    }

    // Getter pour l'ID du patient
    public Integer getPatientID() {
        return patientID;
    }
}
