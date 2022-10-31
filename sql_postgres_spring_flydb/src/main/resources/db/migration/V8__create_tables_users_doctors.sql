CREATE TABLE IF NOT EXISTS doctors (
                                     id bigint PRIMARY KEY NOT NULL,
                                     name text NOT NULL,
                                     status varchar(256) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS doctors_sequence
    START 1
    INCREMENT 1;

CREATE TABLE IF NOT EXISTS patients (
                                       id bigint PRIMARY KEY NOT NULL,
                                       name text NOT NULL,
                                       status varchar(256) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS patients_sequence
    START 1
    INCREMENT 1;

CREATE TABLE IF NOT EXISTS doctors_patients (
    doctor_id bigint NOT NULL REFERENCES doctors(id),
    patient_id bigint NOT NULL REFERENCES patients(id),
    CONSTRAINT pkey_com_doctor_patients PRIMARY KEY (doctor_id, patient_id) -- composite key
)