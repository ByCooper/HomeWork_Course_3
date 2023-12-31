CREATE TABLE car (
    id INTEGER PRIMARY KEY,
    mark VARCHAR(50),
    model VARCHAR(50),
    price NUMERIC CHECK (price > 0)
);

CREATE TABLE driver (
    id INTEGER PRIMARY KEY,
    name TEXT NOT NULL,
    age INTEGER CHECK (age > 0),
    card BOOLEAN NOT NULL,
    id_car INTEGER REFERENCES car(id)
);


SELECT d.name, d.age, d.card, c.mark, c.model, c.price FROM driver AS d
LEFT JOIN car AS c ON d.id_car = c.id;

---------------------------------------------------------------------------
create table price (
    id INTEGER primary key,
    model VARCHAR(50),
    price NUMERIC CHECK (price > 0)
);

create table auto (
    id INTEGER primary key,
    mark VARCHAR(50),
    model VARCHAR(50),
    id_price INTEGER REFERENCES price(id)
);

create table card (
    id INTEGER primary key,
    age INTEGER CHECK (age > 0),
    card BOOLEAN NOT NULL
);

create table driver (
    id INTEGER primary key,
    name TEXT NOT NULL,
    age INTEGER CHECK (age > 0),
    id_card INTEGER REFERENCES card(id),
    id_car INTEGER REFERENCES auto(id)
);

SELECT d.name, d.age, c.card, a.mark, a.model, p.price FROM driver AS d
LEFT JOIN card AS c ON d.id_card = c.id
LEFT JOIN auto AS a ON d.id_car = a.id
RIGHT JOIN price AS p ON a.id_price = p.id;