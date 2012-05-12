DROP SEQUENCE IF EXISTS division_id, employee_id CASCADE;
DROP TABLE IF EXISTS    division, employee CASCADE;

CREATE SEQUENCE division_id INCREMENT BY 1
    MINVALUE 0 NO MAXVALUE START WITH 1 NO CYCLE;

CREATE TABLE division(
    id              BIGINT PRIMARY KEY DEFAULT NEXTVAL('division_id'),
    title           VARCHAR
);


CREATE SEQUENCE employee_id INCREMENT BY 1
    MINVALUE 0 NO MAXVALUE START WITH 1 NO CYCLE;

CREATE TABLE employee(
    id              BIGINT PRIMARY KEY DEFAULT NEXTVAL('employee_id'),
    first_name      VARCHAR,
    last_name       VARCHAR,
    salary          DOUBLE PRECISION,
    birthdate       DATE,
    active          BOOLEAN,
    division_id     BIGINT REFERENCES division(id) ON DELETE CASCADE ON UPDATE RESTRICT
);