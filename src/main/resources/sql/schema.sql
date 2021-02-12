DROP TABLE IF EXISTS marks;
DROP TABLE IF EXISTS users_courses;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS statuses;
DROP TABLE IF EXISTS categories;

CREATE TABLE roles(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);

CREATE TABLE statuses(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE categories(
	category_id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE users(
	user_id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	login VARCHAR(30) NOT NULL UNIQUE,
	password VARCHAR(30) NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	is_student BOOLEAN DEFAULT FALSE,
	is_blocked BOOLEAN DEFAULT FALSE,
	role_id INTEGER NOT NULL REFERENCES roles(id) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE courses(
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	title VARCHAR(50) NOT NULL UNIQUE,
	duration INTEGER NOT NULL,
	price INTEGER NOT NULL,
	start_date DATE NOT NULL,
	teacher INTEGER NOT NULL REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE RESTRICT,
	status INTEGER NOT NULL REFERENCES statuses(id)  ON DELETE CASCADE ON UPDATE RESTRICT,
	category INTEGER NOT NULL REFERENCES categories(category_id) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE users_courses (
  userid int NOT NULL,
  course_id int NOT NULL,
  PRIMARY KEY (userid, course_id),
  FOREIGN KEY (userid) REFERENCES users(user_id) ON UPDATE CASCADE,
  FOREIGN KEY (course_id) REFERENCES courses(id) ON UPDATE CASCADE
);

CREATE TABLE marks (
    mark INTEGER NOT NULL CHECK(mark<=5),
	is_success BOOLEAN DEFAULT FALSE,
	evaluation_date DATE NOT NULL,
	student_id INTEGER NOT NULL REFERENCES users(user_id) ON DELETE CASCADE ON UPDATE RESTRICT,
	course_id INTEGER NOT NULL REFERENCES courses(id) ON DELETE CASCADE ON UPDATE RESTRICT,
	PRIMARY KEY (student_id, course_id)
);

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

INSERT INTO courses VALUES(DEFAULT, 'Java 8',16, 1200, '2020-10-20', 10,2, 1);
INSERT INTO courses VALUES(DEFAULT, 'Python',20, 2000, '2021-03-01', 10,0, 1);
INSERT INTO courses VALUES(DEFAULT, '.NET',20, 21000, '2021-02-01', 11,1, 1);
INSERT INTO courses VALUES(DEFAULT, 'Java 11',18, 21000, '2021-02-10', 11,1, 1);
INSERT INTO courses VALUES(DEFAULT, 'Automated Testing',18, 1000, '2021-04-10', 11,0, 1);
INSERT INTO courses VALUES(DEFAULT, 'SOFTWARE TESTING',15, 1000, '2021-05-10', 10,0, 1);
INSERT INTO courses VALUES(DEFAULT, 'English language',24, 3000, '2021-05-10', 12,0, 2);
INSERT INTO courses VALUES(DEFAULT, 'Italian language',20, 2400, '2021-01-10', 12,1, 2);
INSERT INTO courses VALUES(DEFAULT, 'Higher mathematics',20, 2200, '2020-12-08', 13,2,3);
