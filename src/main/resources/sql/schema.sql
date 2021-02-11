DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS statuses;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS users_courses;


----------------------------------------------------------------
-- ROLES
-- users roles
----------------------------------------------------------------
CREATE TABLE roles(

-- id has the INTEGER type (other name is INT), it is the primary key
	id INTEGER NOT NULL PRIMARY KEY,

-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE)
	name VARCHAR(10) NOT NULL UNIQUE
);

-- this two commands insert data into roles table
----------------------------------------------------------------
-- ATTENTION!!!
-- we use ENUM as the Role entity, so the numeration must started
-- from 0 with the step equaled to 1
----------------------------------------------------------------
INSERT INTO roles VALUES(0, 'STUDENT');
INSERT INTO roles VALUES(1, 'TEACHER');
INSERT INTO roles VALUES(2, 'ADMIN');

CREATE TABLE statuses(

-- id has the INTEGER type (other name is INT), it is the primary key
	id INTEGER NOT NULL PRIMARY KEY,

-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE)
	name VARCHAR(20) NOT NULL UNIQUE
);
INSERT INTO statuses VALUES(0, 'COMING_SOON');
INSERT INTO statuses VALUES(1, 'ONGOING');
INSERT INTO statuses VALUES(2, 'COMPLETED');

CREATE TABLE categories(

-- id has the INTEGER type (other name is INT), it is the primary key
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,

-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE)
	name VARCHAR(30) NOT NULL UNIQUE
);
INSERT INTO categories VALUES(DEFAULT, 'PROGRAMMING');
INSERT INTO categories VALUES(DEFAULT, 'FOREIGN LANGUAGE');
INSERT INTO categories VALUES(DEFAULT, 'MATH');


----------------------------------------------------------------
-- USERS
----------------------------------------------------------------
CREATE TABLE users(

-- 'generated always AS identity' means id is autoincrement field
-- (from 1 up to Integer.MAX_VALUE with the step 1)
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,

-- 'UNIQUE' means logins values should not be repeated in login column of table
	login VARCHAR(30) NOT NULL UNIQUE,

-- not null string columns
	password VARCHAR(30) NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	is_student BOOLEAN DEFAULT FALSE,
	is_blocked BOOLEAN DEFAULT FALSE,

-- this declaration contains the foreign key constraint
-- role_id in users table is associated with id in roles table
-- role_id of user = id of role
	role_id INTEGER NOT NULL REFERENCES roles(id)

-- removing a row with some ID from roles table implies removing
-- all rows from users table for which ROLES_ID=ID
-- default value is ON DELETE RESTRICT, it means you cannot remove
-- row with some ID from the roles table if there are rows in
-- users table with ROLES_ID=ID
		ON DELETE CASCADE

-- the same as previous but updating is used instead deleting
		ON UPDATE RESTRICT
);

-- id = 1
INSERT INTO users VALUES(DEFAULT, 'meadow@gmail.com','1', 'Meadow', 'Soprano', true, false, 0);
-- id = 2
INSERT INTO users VALUES(DEFAULT, 'carmela@gmail.com', '2', 'Carmela', 'Soprano', false, false, 1);
-- id = 3
INSERT INTO users VALUES(DEFAULT, 'tony@gmail.com', '3', 'Antony', 'Soprano', false, false, 2);

CREATE TABLE courses(
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	title VARCHAR(50) NOT NULL UNIQUE,
	duration INTEGER NOT NULL,
	price INTEGER NOT NULL,
	start_date DATE NOT NULL,
	teacher_id INTEGER NOT NULL REFERENCES users(id)  ON DELETE CASCADE ON UPDATE RESTRICT,
	status_id INTEGER NOT NULL REFERENCES statuses(id)  ON DELETE CASCADE ON UPDATE RESTRICT,
	category_id INTEGER NOT NULL REFERENCES categories(id) ON DELETE CASCADE ON UPDATE RESTRICT
);

CREATE TABLE users_courses (
  user_id int NOT NULL,
  course_id int NOT NULL,
  PRIMARY KEY (user_id, course_id),
  FOREIGN KEY (user_id) REFERENCES users(id) ON UPDATE CASCADE,
  FOREIGN KEY (course_id) REFERENCES courses(id) ON UPDATE CASCADE
);