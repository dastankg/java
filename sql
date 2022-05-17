# CREATE TABLE currency (
#     currency_id INT PRIMARY KEY AUTO_INCREMENT,
#     name_currency VARCHAR(50),
#     sum_currency DECIMAL(10, 2)
# );

INSERT INTO currency (name_currency, sum_currency) VALUES
                                                       ('USD', 1000000.0),
                                                       ('EUR', 1000000.0),
                                                       ('RUB', 1000000.0),
                                                       ('KZT', 1000000.0);
INSERT INTO currency (name_currency, sum_currency) VALUE ('KGZ', 1000000.0);

CREATE TABLE buy_currency (
    buy_currency_id INT PRIMARY KEY AUTO_INCREMENT,
    currency_id INT NOT NULL,
    sum_buy_currency DECIMAL(10, 2),
    date_buy DATE,
    FOREIGN KEY (currency_id) REFERENCES currency (currency_id)
);

CREATE TABLE sell_currency (
    sell_currency_id INT PRIMARY KEY AUTO_INCREMENT,
    currency_id INT NOT NULL,
    sum_sell_currency DECIMAL(10, 2),
    date_buy DATE,
    FOREIGN KEY (currency_id) REFERENCES currency (currency_id)
);

CREATE TABLE client
(
    client_id   INT PRIMARY KEY AUTO_INCREMENT,
    name_client VARCHAR(50)
);


CREATE TABLE client_data
(
    client_id       INT PRIMARY KEY AUTO_INCREMENT,
    age             INT,
    phone_number    VARCHAR(50),
    city            VARCHAR(50),
    personal_number VARCHAR(50),
    job             BOOLEAN,
    salary_client   DECIMAL(10, 2),
    FOREIGN KEY (client_id) REFERENCES client (client_id)

);


CREATE TABLE client_status
(
    client_id      INT PRIMARY KEY AUTO_INCREMENT,
    sum_credit     DECIMAL(10, 2),
    sum_pay DECIMAL(10, 2),
    sum_pay_credit DECIMAL(10, 2),
    date_start     DATE,
    date_end       DATE,
    percent        DECIMAL(10, 2),
    status_credit  BOOLEAN,
    FOREIGN KEY (client_id) REFERENCES client (client_id)
);


CREATE TABLE credit_history
(   credit_history INT PRIMARY KEY AUTO_INCREMENT,
    client_id INT,
    date_pay  DATE,
    sum_pay   DECIMAL(10, 2),
    FOREIGN KEY (client_id) REFERENCES client (client_id)
);


CREATE TABLE new_client (
    new_client_id INT PRIMARY KEY AUTO_INCREMENT,
    name_new_client VARCHAR(50)
);


CREATE TABLE new_client_data
(
    new_client_id   INT PRIMARY KEY AUTO_INCREMENT,
    age             INT,
    phone_number    VARCHAR(50),
    city            VARCHAR(50),
    personal_number VARCHAR(50),
    job             BOOLEAN,
    salary_client   DECIMAL(10, 2),
    FOREIGN KEY (new_client_id) REFERENCES new_client (new_client_id)
);

create TABLE new_client_data_credit  (
    new_client_id   INT PRIMARY KEY AUTO_INCREMENT,
    month_credit INT,
    sum_credit DECIMAL(10, 2),
    percent DECIMAL(10, 2),
    sum_after_percent DECIMAL(10, 2),
    FOREIGN KEY (new_client_id) REFERENCES new_client (new_client_id)
);

CREATE TABLE good_client  (
    good_client INT PRIMARY KEY AUTO_INCREMENT,
    name_good_client VARCHAR(50),
    personal_number VARCHAR(50)
);

CREATE TABLE bad_client  (
    good_client INT PRIMARY KEY AUTO_INCREMENT,
    name_bad_client VARCHAR(50),
    personal_number VARCHAR(50)

);

CREATE TABLE admin (
    admin_id INT PRIMARY KEY AUTO_INCREMENT,
    name_admin VARCHAR(50),
    city VARCHAR(50),
    login VARCHAR(50),
    password_admin VARCHAR(50)
);

INSERT INTO admin (name_admin, city, login, password_admin) VALUE ('Telmanov Meder', 'Karakol', '123', '123');



ALTER TABLE new_client ADD status INT;


CREATE TABLE deposits (
  client_id INT PRIMARY KEY AUTO_INCREMENT,
  name_client VARCHAR(50),
  phone VARCHAR(50),
  age VARCHAR(50),
  inn VARCHAR(50),
  sum DECIMAL(10, 2),
  sum_get DECIMAL(10, 2),
  date_start DATE,
  date_end DATE
);