CREATE TABLE product(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500) NOT NULL,
    image VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    amount INT NOT NULL,

    PRIMARY KEY('id')
);