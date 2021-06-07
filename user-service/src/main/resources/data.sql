INSERT INTO roles VALUES(0, 'STUDENT');
INSERT INTO roles VALUES(1, 'TEACHER');
INSERT INTO roles VALUES(2, 'ADMIN');

INSERT INTO statuses VALUES(0, 'COMING_SOON');
INSERT INTO statuses VALUES(1, 'ONGOING');
INSERT INTO statuses VALUES(2, 'COMPLETED');

INSERT INTO categories VALUES(DEFAULT, 'PROGRAMMING');
INSERT INTO categories VALUES(DEFAULT, 'FOREIGN LANGUAGE');
INSERT INTO categories VALUES(DEFAULT, 'MATH');

INSERT INTO users VALUES(DEFAULT, 'meadow@gmail.com','11', 'Meadow', 'Soprano', true, false, 0);
INSERT INTO users VALUES(DEFAULT, 'carmela@gmail.com', '22', 'Carmela', 'Soprano', true, false, 0);
INSERT INTO users VALUES(DEFAULT, 'antony@gmail.com', '33', 'Antony', 'Soprano', true, false, 0);

INSERT INTO users VALUES(DEFAULT, 'males@gmail.com', '44', 'Males', 'Morales', true, false, 0);
INSERT INTO users VALUES(DEFAULT, 'tony@gmail.com', '55', 'Tony', 'Stark', true, false, 0);
INSERT INTO users VALUES(DEFAULT, 'bruce@gmail.com', '66', 'Bruce', 'Banner', true, false, 0);
INSERT INTO users VALUES(DEFAULT, 'stephen@gmail.com', '77', 'Stephen', 'Strange', true, false, 0);
INSERT INTO users VALUES(DEFAULT, 'betty@gmail.com', '88', 'Betty', 'Brant', true, false, 0);
INSERT INTO users VALUES(DEFAULT, 'addy@gmail.com', '99', 'Addy', 'Venom', true, false, 0);
INSERT INTO users VALUES(DEFAULT, 'super@gmail.com', '1010', 'Super', 'Man', false, false, 1);
INSERT INTO users VALUES(DEFAULT, 'wade@gmail.com', '1111', 'Wade', 'Deadpool', false, false, 1);
INSERT INTO users VALUES(DEFAULT, 'black@gmail.com', '1212', 'Black', 'Panther', false, false, 1);
INSERT INTO users VALUES(DEFAULT, 'walter@gmail.com', '1313', 'Walter', 'White', false, false, 1);
INSERT INTO users VALUES(DEFAULT, 'simon@gmail.com', '1414', 'Simon', 'Buff', false, false, 2);

INSERT INTO courses VALUES(DEFAULT, 'Java 8',16, 1200, '2020-10-20', 10,2, 1,0);
INSERT INTO courses VALUES(DEFAULT, 'Python',20, 2000, '2021-04-01', 10,0, 1,0);
INSERT INTO courses VALUES(DEFAULT, '.NET',20, 21000, '2021-02-01', 11,1, 1,1);
INSERT INTO courses VALUES(DEFAULT, 'Java 11',18, 21000, '2021-02-10', 11,1, 1,0);
INSERT INTO courses VALUES(DEFAULT, 'Automated Testing',18, 1000, '2021-04-10', 11,0, 1,0);
INSERT INTO courses VALUES(DEFAULT, 'SOFTWARE TESTING',15, 1000, '2021-05-10', 10,0, 1,0);
INSERT INTO courses VALUES(DEFAULT, 'English language',24, 3000, '2021-05-10', 12,0, 2,0);
INSERT INTO courses VALUES(DEFAULT, 'Italian language',20, 2400, '2021-01-10', 12,1, 2,0);
INSERT INTO courses VALUES(DEFAULT, 'Higher mathematics',20, 2200, '2020-12-08', 13,2,3,0);
INSERT INTO courses VALUES(DEFAULT, 'Polish language',20, 2200, '2021-04-01', 12,0,2,0);
INSERT INTO courses VALUES(DEFAULT, 'JDBC',28, 2300, '2021-02-08', 10,1,1,0);
INSERT INTO courses VALUES(DEFAULT, 'Scala',10, 2000, '2021-01-08', 11,2,1,0);
INSERT INTO courses VALUES(DEFAULT, 'C++',12, 1100, '2021-05-15', 12,0,1,0);
INSERT INTO courses VALUES(DEFAULT, 'Mathematical analysis',5, 1000, '2020-12-08', 13,1,3,0);
INSERT INTO courses VALUES(DEFAULT, 'Basics of geometry',3, 2000, '2021-12-08', 13,2,3,0);


INSERT INTO users_courses VALUES(1, 3);

--INSERT INTO users_courses VALUES(2, 15);
--INSERT INTO users_courses VALUES(2, 12);
--INSERT INTO users_courses VALUES(2, 13);

--INSERT INTO users_courses VALUES(3, 15);
--INSERT INTO users_courses VALUES(3, 12);
--INSERT INTO users_courses VALUES(3, 13);

--INSERT INTO users_courses VALUES(4, 8);
--INSERT INTO users_courses VALUES(4, 11);
--INSERT INTO users_courses VALUES(4, 1);
--INSERT INTO users_courses VALUES(4, 9);

--INSERT INTO users_courses VALUES(5, 3);
--INSERT INTO users_courses VALUES(5, 11);
--INSERT INTO users_courses VALUES(5, 12);
