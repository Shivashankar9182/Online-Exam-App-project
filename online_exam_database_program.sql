-- Create the database
CREATE DATABASE online_exam;
USE online_exam;
drop database online_exam;

-- Create the users table
CREATE TABLE users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL 
);
select * from users;

-- Create the course table
CREATE TABLE course (
    course_id INT AUTO_INCREMENT PRIMARY KEY,
    course_name VARCHAR(50) NOT NULL,
    duration VARCHAR(20) NOT NULL
);
INSERT INTO course ( course_name, duration) 
VALUES 
    ( 'Introduction to SQL', '5 weeks'),
    ( 'Introduction to java', '2 weeks'),
	('Database Management Systems', '1 week');
    select * from course;

create table joinCourse(
join_id int auto_increment primary key,
user_id int not null,
course_id int not null,
foreign key(user_id) references users(user_id),
foreign key(course_id) references course(course_id)
);

-- Create the exams table
CREATE TABLE exams (
    exam_id INT AUTO_INCREMENT PRIMARY KEY,
    course_id INT NOT NULL,
    exam_name VARCHAR(50),
    FOREIGN KEY(course_id) REFERENCES course(course_id)
);
INSERT INTO exams (course_id, exam_name) 
VALUES 
    (1, 'SQL EXAM'),
    (2, 'JAVA Exam'),
	(3, 'Database Management System Exam'),
	(4,'Web Development Exam');
    select * from exams;

-- Create the questions table
CREATE TABLE questions (
    question_id INT AUTO_INCREMENT PRIMARY KEY,
    exam_id INT NOT NULL,
    question_text VARCHAR(255) NOT NULL,
    correct_option ENUM('A', 'B', 'C', 'D') NOT NULL, -- Indicates the correct option
    FOREIGN KEY(exam_id) REFERENCES exams(exam_id)
);

INSERT INTO questions (exam_id, question_text, correct_option) 
VALUES 
    (1, 'Which SQL command is used to retrieve data from a database?', 'A'),
    (1, 'Which SQL clause is used to sort the result set?', 'D'),
    (1, 'Which SQL command is used to insert new data into a table?', 'A'),
    (1, 'Which SQL command is used to delete data from a table?', 'A'),
    (1, 'Which SQL function is used to calculate the average value of a column?', 'C');


INSERT INTO questions (exam_id, question_text, correct_option) 
VALUES
    (2, 'What is Java?', 'A'),
    (2, 'What is the extension of a Java source file?', 'D'),
    (2, 'Which component of Java is responsible for running the compiled Java bytecode?', 'B'),
    (2, 'Which of the following is not a primitive data type in Java?', 'C'),
    (2, 'What is the size of a float data type in Java?', 'A');
    
INSERT INTO questions (exam_id, question_text, correct_option) VALUES
(3, 'What is the main purpose of a database management system (DBMS)?', 'A'),
(3, 'Which of the following is a relational database management system?', 'B'),
(3, 'What does SQL stand for?', 'C'),
(3, 'Which of the following is not a type of database?', 'D'),
(3, 'What is the process of organizing data in a database called?', 'A');

    select * from questions;

-- Create the options table
CREATE TABLE options (
    option_id INT AUTO_INCREMENT PRIMARY KEY,
    question_id INT NOT NULL,
    option_text VARCHAR(255) NOT NULL, -- Store the text for each option
    is_correct BOOLEAN DEFAULT false, -- Indicate if the option is correct
    FOREIGN KEY(question_id) REFERENCES questions(question_id)
);

INSERT INTO options (question_id, option_text, is_correct) 
VALUES 
    (1, 'Select', TRUE), (1, 'Insert', FALSE), (1, 'Update', FALSE), (1, 'Delete', FALSE),
    (2, 'Order', FALSE), (2, 'Sort', FALSE), (2, 'Ascending', FALSE), (2, 'Order by', TRUE),      
    (3, 'Insert into', TRUE), (3, 'Add to', FALSE), (3, 'Put into', FALSE), (3, 'New record', FALSE),
    (4, 'Delete from', TRUE), (4, 'Remove', FALSE), (4, 'Erase', FALSE), (4, 'Drop', FALSE),
    (5, 'COUNT', FALSE), (5, 'MAX', FALSE), (5, 'AVG', TRUE), (5, 'SUM', FALSE);

INSERT INTO options (question_id, option_text, is_correct) 
VALUES
(6, 'Programming language', TRUE), (6, 'Web browser', FALSE), (6, 'An operating system', FALSE), (6, 'A database management system', FALSE),
(7, '.class', FALSE), (7, '.exe', FALSE), (7, '.java', TRUE), (7, '.jar', FALSE),
(8, 'JDK', FALSE), (8, 'JVM', TRUE), (8, 'JRE', FALSE), (8, 'JIT', FALSE),
(9, 'int', FALSE), (9, 'float', FALSE), (9, 'string', TRUE), (9, 'double', FALSE),
(10, '4 bytes', TRUE), (10, '8 bytes', FALSE), (10, '2 bytes', FALSE), (10, '1 byte', FALSE);

INSERT into options (question_id, option_text, is_correct) VALUES
(11, 'To manage data efficiently', true),(11, 'To write code', false),(11, 'To create graphics', false),(11, 'To perform calculations', false),
(12, 'MySQL', true),(12, 'Notepad', false),(12, 'Microsoft Word', false),(12, 'PowerPoint', false),
(13, 'Structured Query Language', true),(13, 'Sequential Query Language', false),(13, 'Simple Query Language', false),(13, 'Standard Query Language', false),
(14, 'Hierarchical Database', false),(14, 'Network Database', false),(14, 'Relational Database', false),(14, 'Distributed Database', true),          -- Assuming distributed database is the correct option
(15, 'Data Modeling', true),(15, 'Data Migration', false),(15, 'Data Duplication', false),(15, 'Data Analysis', false);

SELECT * FROM options;

-- Create the results table
CREATE TABLE results (
    result_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    exam_id INT NOT NULL,
    score INT,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (exam_id) REFERENCES exams(exam_id)
);
