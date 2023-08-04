package com.zorgapp.data;

import com.google.gson.*;
import com.zorgapp.exceptions.PatientNotFoundException;
import com.zorgapp.models.Patient;
import com.zorgapp.adapters.LocalDateAdapter;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Data {
    private static Data instance;
    private static String source;

    private final ArrayList<Patient> patients;

    private Data(ArrayList<Patient> patients) {
        this.patients = patients;
    }

    private void write() {
        //TODO
    }

    public static void init(String source) {
        Data.source = source;
        InputStream stream = Data.class.getResourceAsStream(source);

        if (stream == null) {
            //TODO create resource...
            Data.instance = new Data(new ArrayList<>());
            return;
        }

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .create();

        Patient[] patients = gson.fromJson(new InputStreamReader(stream), Patient[].class);
        Data.instance = new Data(new ArrayList<>(Arrays.asList(patients)));
    }

    public static Data getInstance() {
        if (Data.instance == null) {
            throw new IllegalStateException("Data has not been initialized");
        }

        return Data.instance;
    }

    public ArrayList<Patient> getPatients() {
        return this.patients;
    }

    public Patient getPatient(int id) throws PatientNotFoundException {
        for (Patient patient : this.patients) {
            if (patient.getId() == id) {
                return patient;
            }
        }
        throw new PatientNotFoundException();
    }

    public void addPatient(Patient patient) {
        this.patients.add(patient);
        this.write();
    }

    public void deletePatient(Patient patient) {
        this.patients.remove(patient);
        this.write();
    }

    public void updatePatient(Patient newPatient) {
        this.write();
    }
}

