package com.zorgapp.data;

import com.google.gson.*;
import com.zorgapp.exceptions.PatientNotFoundException;
import com.zorgapp.models.Patient;
import com.zorgapp.adapters.LocalDateAdapter;

import java.io.*;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    private static String source;
    private static ArrayList<Patient> patients;

    private static void write() {
        URL resource = Data.class.getResource(Data.source);

        if (resource == null) {
            return;
        }

        try {
            File file = new File(resource.getPath());
            file.createNewFile();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                    .create();

            FileWriter writer = new FileWriter(file);
            writer.write(gson.toJson(Data.patients));
            writer.close();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public static void init(String source) {
        Data.source = source;
        InputStream stream = Data.class.getResourceAsStream(source);

        if (stream == null) {
            Data.patients = new ArrayList<>();
            return;
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        Patient[] patients = gson.fromJson(new InputStreamReader(stream), Patient[].class);
        Data.patients = new ArrayList<>(Arrays.asList(patients));
    }

    public static ArrayList<Patient> getPatients() {
        return Data.patients;
    }

    public static Patient getPatient(int id) throws PatientNotFoundException {
        for (Patient patient : Data.patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        throw new PatientNotFoundException();
    }

    public static void addPatient(Patient patient) {
        Data.patients.add(patient);
        Data.write();
    }

    public static void deletePatient(Patient patient) {
        Data.patients.remove(patient);
        Data.write();
    }

    public static void updatePatient() {
        Data.write();
    }
}

