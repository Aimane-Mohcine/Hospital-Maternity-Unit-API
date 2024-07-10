package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class ApiClient {

    public static List<Patient> getPatients() throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url = "https://web.socem.plymouth.ac.uk/COMP2005/api/Patients";

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            // Use of TypeToken to indicate the expected data type (List<Patient>)
            Type listOfPatientsType = new TypeToken<List<Patient>>() {
            }.getType();
            Gson gson = new Gson();
            List<Patient> patients = gson.fromJson(response.body().string(), listOfPatientsType);

            return patients;
        }
    }

    public static List<Admission> getAdmissionsFromAPI(int id) {
        OkHttpClient client = new OkHttpClient();

        String url = "http://localhost:8888/api/all_admissions_for_a_specific_patient/" + id;

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            Gson gson = new Gson();
            java.lang.reflect.Type listOfAdmissionsType = new TypeToken<List<Admission>>() {
            }.getType();
            return gson.fromJson(response.body().string(), listOfAdmissionsType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}