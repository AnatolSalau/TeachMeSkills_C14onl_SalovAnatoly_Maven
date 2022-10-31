INSERT INTO doctors VALUES
(nextval('doctors_sequence'),'doctor1','status1'),
(nextval('doctors_sequence'),'doctor2','status2'),
(nextval('doctors_sequence'),'doctor3','status3'),
(nextval('doctors_sequence'),'doctor4','status4');

INSERT INTO patients VALUES
(nextval('patients_sequence'),'title_1','isbn_1'),
(nextval('patients_sequence'),'title_2','isbn_2'),
(nextval('patients_sequence'),'title_3','isbn_3'),
(nextval('patients_sequence'),'title_4','isbn_4');

INSERT INTO doctors_patients VALUES
                         (1,1),
                         (1,2),
                         (1,3),
                         (1,4);
INSERT INTO doctors_patients VALUES
                                 (1,1);