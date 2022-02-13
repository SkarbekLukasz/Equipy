INSERT INTO
    user(id, first_name, last_name, pesel)
VALUES
    (1, 'Jan', 'Kowalski', '90101222457'),
    (2, 'Maciej', 'Zalewski', '87112242456'),
    (3, 'Aneta', 'Korczyńska', '76061536749');

INSERT INTO
    category(id, name, description)
VALUES
    (1, 'Laptopy', 'Przenośne komputery'),
    (2, 'Pojazdy', 'Samochody służbowe');

INSERT INTO
    asset(id, name, description, serial_no, category_id)
VALUES
    (1, 'Asus MateBook D', '15 calowy laptop, i5, 8GB DDR3, kolor czarny', 'ASMBD198723', 1),
    (2, 'Apple MacBook Pro 2015', '13 calowy laptop, i5, 16GB DDR3, SSD256GB, kolor srebrny', 'MBP15X0925336', 1),
    (3, 'Dell Inspirion 3576', '13 calowy laptop, i7, 8GB DDR4, SSD 512GB, kolor czarny', 'DI3576XO526716', 2);

