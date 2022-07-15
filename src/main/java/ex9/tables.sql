DROP TABLE IF EXISTS students CASCADE;
DROP TABLE IF EXISTS subjects CASCADE;
DROP TABLE IF EXISTS students_subjects CASCADE;

CREATE TABLE students (
    student_id SERIAL NOT NULL,
    student_name VARCHAR(30) NOT NULL,
    birth_date DATE,
    CONSTRAINT pk_student_id PRIMARY KEY (student_id)
);

CREATE TABLE subjects (
    subject_id SERIAL NOT NULL,
    subject_desc TEXT NOT NULL,
    CONSTRAINT pk_subject_id PRIMARY KEY (subject_id)
);

CREATE TABLE students_subjects (
    student_id INT NOT NULL,
    subject_id INT NOT NULL,
    CONSTRAINT pk_students_subjects PRIMARY KEY (student_id , subject_id),
    CONSTRAINT fk_student_id FOREIGN KEY (student_id)
        REFERENCES students (student_id)
    ON DELETE CASCADE,
    CONSTRAINT fk_subject_id FOREIGN KEY (subject_id)
        REFERENCES subjects (subject_id)
    ON DELETE CASCADE
);

INSERT INTO students (student_name, birth_date) 
VALUES ('Alesha', '1999-12-01'), 
       ('Alex', '1999-04-15'),
       ('Peter', '2000-03-12'),
       ('Samantha', '1998-01-02'),
       ('Sasha', '1998-06-02'),
       ('Simeon', '1998-09-30'),
       ('Kristina', '1998-03-20');

INSERT INTO subjects (subject_desc)
VALUES ('Math'),
       ('Geometry'),
       ('Economics'),
       ('Geography'),
       ('Astronomy'),
       ('Geography'),
       ('English');

INSERT INTO students_subjects (student_id, subject_id)
VALUES (1, 2),
       (1, 3),
       (2, 4),
       (3, 2),
       (4, 6),
       (7, 7),
       (5, 6),
       (1, 7),
       (7, 1),
       (5, 5),
       (2, 1),
       (6, 4),
       (2, 5);