CREATE TABLE class (
                       id int PRIMARY KEY AUTO_INCREMENT,
                       name varchar(50)
);

CREATE TABLE student(
                        id int PRIMARY KEY AUTO_INCREMENT,
                        fullname varchar(25),
                        date_of_birth date,
                        sex bit,
                        phone varchar(10),
                        note varchar(100),
                        class_id int,
                        FOREIGN KEY (class_id) REFERENCES class(id)
);