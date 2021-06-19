DROP TABLE IF EXISTS courses;
DROP TABLE IF EXISTS statuses;
DROP TABLE IF EXISTS categories;

DROP SEQUENCE IF EXISTS course_seq;
DROP SEQUENCE IF EXISTS category_seq;


CREATE TABLE statuses(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(20) NOT NULL UNIQUE
);

CREATE TABLE categories(
	category_id INTEGER NOT NULL GENERATED BY DEFAULT AS identity PRIMARY KEY,
	name VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE courses(
	id INTEGER NOT NULL GENERATED BY DEFAULT AS identity PRIMARY KEY,
	title VARCHAR(50) NOT NULL UNIQUE,
	duration INTEGER NOT NULL,
	price INTEGER NOT NULL,
	start_date DATE NOT NULL,
	teacher_id INTEGER,
	status INTEGER NOT NULL REFERENCES statuses(id)  ON DELETE CASCADE ON UPDATE RESTRICT,
	category INTEGER NOT NULL REFERENCES categories(category_id) ON DELETE CASCADE ON UPDATE RESTRICT,
	enrollment INTEGER NOT NULL
);

CREATE SEQUENCE course_seq START 20;
CREATE SEQUENCE category_seq START 5;