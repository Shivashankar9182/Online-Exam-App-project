-- Create the database
create database online_exams;
use online_exams;



-- Create the users table
create table users (
    user_id int auto_increment primary key,
    username varchar(50) not null,
    password varchar(255) not null 
);

-- Create the course table
create table course (
    course_id int auto_increment primary key,
    user_id int not null,
    course_name varchar(50) not null,
    duration varchar(20) not null,
    foreign key(user_id) references users(user_id)
);

-- Create the exams table
create table exams (
    exam_id int auto_increment primary key,
    course_id int not null,
    exam_name varchar(50),
    foreign key(course_id) references course(course_id)
);

-- Create the user_exams table
create table user_exams(
	user_exam_id int auto_increment primary key,
    user_id int,
	exam_date datetime default current_timestamp,
    status enum('completed', 'in-progress', 'not attempted') default 'not attempted',
    score int default null,
    foreign key(user_id) references users(user_id)
);

-- Create the questions table
create table questions (
    question_id int auto_increment primary key,
    exam_id int not null,
    question_text varchar(255) not null,
    question_type enum('multiple_choice', 'true_false', 'open_ended'),
    correct_option int default null,
    foreign key(exam_id) references exams(exam_id)
);

-- Create the options table
create table options (
    option_id int auto_increment primary key,
    question_id int not null,
    option_text varchar(255) not null,
    is_correct boolean default false,
    foreign key(question_id) references questions(question_id)
);

-- Create the results table
create table results (
    result_id int auto_increment primary key,
    user_id int not null,
    exam_id int not null,
    score int,
    foreign key (user_id) references users(user_id),
    foreign key (exam_id) references exams(exam_id)
);