//скрипты DDL для PostgreSQL создающие структуру базы данных нашего проекта.

DROP TABLE IF EXISTS jc_student_child;
DROP TABLE IF EXISTS jc_student_order;

DROP TABLE IF EXISTS jc_passport_office;
DROP TABLE IF EXISTS jc_register_office;
DROP TABLE IF EXISTS jc_country_struct;
DROP TABLE IF EXISTS jc_street;

CREATE TABLE jc_street(
	street_code integer not null,
	street_name varchar(300),
	PRIMARY KEY (street_code)
);

CREATE TABLE jc_country_struct(
	area_id char(12) not null,
	area_name varchar(200),
	PRIMARY KEY (area_id)
);

CREATE TABLE jc_passport_office(
	p_office_id integer not null,
	p_office_area_id char(12) not null,
	p_office_name varchar(200),
	PRIMARY KEY (p_office_id),
	FOREIGN KEY (p_office_area_id) REFERENCES jc_country_struct(area_id) ON DELETE RESTRICT
);

CREATE TABLE jc_register_office(
	r_office_id integer not null,
	r_office_area_id char(12) not null,
	r_office_name varchar(200),
	PRIMARY KEY (r_office_id),
	FOREIGN KEY (r_office_area_id) REFERENCES jc_country_struct(area_id) ON DELETE RESTRICT
);

CREATE TABLE jc_student_order(
    student_order_id SERIAL,
    h_sur_name varchar(100) not null,
    h_given_name varchar(100) not null,
    h_patronymic varchar(100) not null,
    h_date_of_birth date not null,
    h_passport_seria varchar(10) not null,
    h_passport_number varchar(10) not null,
    h_passport_date date not null,
    h_passport_office_id integer not null,

    h_post_index varchar(10) not null,
    h_street_code integer not null,
    h_building varchar(10) not null,
    h_extension varchar(10) not null,
    h_apartment varchar(10) not null,

    w_sur_name varchar(100) not null,
    w_given_name varchar(100) not null,
    w_patronymic varchar(100) not null,
    w_date_of_birth date not null,
    w_passport_seria varchar(10) not null,
    w_passport_number varchar(10) not null,
    w_passport_date date not null,
    w_passport_office_id integer not null,

    w_post_index varchar(10) not null,
    w_street_code integer not null,
    w_building varchar(10) not null,
    w_extension varchar(10) not null,
    w_apartment varchar(10) not null,

    certificate_id varchar(20) not null,
    register_office_id integer not null,
    marriage_date date not null,
    PRIMARY KEY (student_order_id),
    FOREIGN KEY (h_street_code) REFERENCES jc_street(street_code) ON DELETE RESTRICT,
    FOREIGN KEY (w_street_code) REFERENCES jc_street(street_code) ON DELETE RESTRICT,
    FOREIGN KEY (register_office_id) REFERENCES jc_register_office(r_office_id) ON DELETE RESTRICT

);

CREATE TABLE jc_student_child(
    student_child_id SERIAL,
    student_order_id integer not null,

    c_sur_name varchar(100) not null,
    c_given_name varchar(100) not null,
    c_patronymic varchar(100) not null,
    c_date_of_birth date not null,

    c_certificate_number varchar(10) not null,
    c_certificate_date date not null,
    c_register_office_id integer not null,

    c_post_index varchar(10) not null,
    c_street_code integer not null,
    c_building varchar(10) not null,
    c_extension varchar(10) not null,
    c_apartment varchar(10) not null,
    PRIMARY KEY (student_child_id),
    FOREIGN KEY (c_street_code) REFERENCES jc_street(street_code) ON DELETE RESTRICT,
    FOREIGN KEY (c_register_office_id) REFERENCES jc_register_office(r_office_id) ON DELETE RESTRICT
);


//Заполнение таблиц данными
DELETE FROM jc_street;
INSERT INTO jc_street (street_code,street_name) VALUES
(1, 'Sadovaya str'),
(2, 'Nevskiy prosp'),
(3, 'Stahanovcev str'),
(4, 'Gorohovaya str'),
(5, 'Veteranov prosp');

DELETE FROM jc_country_struct;
INSERT INTO jc_country_struct (area_id, area_name) VALUES
('010000000000', 'Town'),
('010010000000', 'Town area 1'),
('010020000000', 'Town area 2'),
('010030000000', 'Town area 3'),
('010040000000', 'Town area 4'),
('020000000000', 'Kray'),
('020010000000', 'Kray Oblast 1'),
('020010010000', 'Kray Oblast 1  1'),
('020010010001', 'Kray Oblast 1 Rayon 1 Poselenie 1'),
('020010010002', 'Kray Oblast 1 Rayon 1 Poselenie 2'),
('020010020000', 'Kray Oblast 1 Rayon 2'),
('020010020001', 'Kray Oblast 1 Rayon 2 Poselenie 1'),
('020010020002', 'Kray Oblast 1 Rayon 2 Poselenie 2'),
('020010020003', 'Kray Oblast 1 Rayon 2 Poselenie 3'),
('020020000000', 'Kray Oblast 2'),
('020020010000', 'Kray Oblast 2 Rayon 1'),
('020020010001', 'Kray Oblast 2 Rayon 1 Poselenie 1'),
('020020010002', 'Kray Oblast 2 Rayon 1 Poselenie 2'),
('020020010003', 'Kray Oblast 2 Rayon 1 Poselenie 3'),
('020020020000', 'Kray Oblast 2 Rayon 2'),
('020020020001', 'Kray Oblast 2 Rayon 2 Poselenie 1'),
('020020020002', 'Kray Oblast 2 Rayon 2 Poselenie 2'),
('020020020003', 'Kray Oblast 2 Rayon 2 Poselenie 3');

DELETE FROM jc_passport_office;
INSERT INTO jc_passport_office (p_office_id, p_office_area_id, p_office_name) VALUES
(1, '010010000000', 'Passportniy stol Rayon 1 gorod'),
(2, '010020000000', 'Passportniy stol 1 Rayon 2 gorod'),
(3, '010020000000', 'Passportniy stol 2 Rayon 2 gorod'),
(4, '010010000000', 'Passportniy stol Rayon 3 gorod'),
(5, '020010010001', 'Passportniy stol Oblast 1 Poselenie 1'),
(6, '020010020002', 'Passportniy stol Oblast 1 Poselenie 2'),
(7, '020020010000', 'Passportniy stol Oblast 2 Rayon 1'),
(8, '020020020000', 'Passportniy stol Oblast 2 Rayon 2');

DELETE FROM jc_register_office;
INSERT INTO jc_register_office (r_office_id, r_office_area_id, r_office_name) VALUES
(1, '010010000000', 'ZAGS 1 Rayon 1 gorod'),
(2, '010010000000', 'ZAGS 2 Rayon 1 gorod'),
(3, '010020000000', 'ZAGS Rayon 2 gorod'),
(4, '020010010001', 'ZAGS Oblast 1 поселения 1'),
(5, '020010020002', 'ZAGS Oblast 1 поселения 2'),
(6, '020020010000', 'ZAGS Oblast 2 район 1'),
(7, '020020020000', 'ZAGS Oblast 2 район 2');

