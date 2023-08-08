BEGIN TRANSACTION;

DROP TABLE IF EXISTS restaurant_approval;
DROP TABLE IF EXISTS invite_restaurant;
DROP TABLE IF EXISTS invite_guest;
DROP TABLE IF EXISTS invite;
DROP TABLE IF EXISTS hours_of_operation;
DROP TABLE IF EXISTS day_of_week;
DROP TABLE IF EXISTS restaurant;

CREATE TABLE restaurant (
    restaurant_id SERIAL NOT NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(20),
    street VARCHAR(50),
    city VARCHAR(50),
    state VARCHAR(2),
    zip VARCHAR(10),
    phone VARCHAR(15),
    img_url VARCHAR(200),
    CONSTRAINT pk_restaurant PRIMARY KEY (restaurant_id)
);

CREATE TABLE day_of_week (
    day_id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(10) NOT NULL
);

INSERT INTO day_of_week (name) VALUES
('Sunday'), ('Monday'), ('Tuesday'), ('Wednesday'), ('Thursday'), ('Friday'), ('Saturday');

CREATE TABLE hours_of_operation (
    restaurant_id INTEGER NOT NULL,
    day_open INTEGER NOT NULL,
    open_from TIME NOT NULL,
    open_to TIME NOT NULL,
    CONSTRAINT fk_restuarant_hours_of_operation FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id),
    CONSTRAINT fk_hours_of_operation_day_of_week FOREIGN KEY (day_open) REFERENCES day_of_week(day_id)
);

CREATE TABLE invite (
    invite_id SERIAL NOT NULL,
    user_id INTEGER NOT NULL,
    decision_by TIMESTAMP NOT NULL,
    message varchar(200),
    CONSTRAINT pk_invite PRIMARY KEY (invite_id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE invite_guest (
    invite_id INTEGER NOT NULL,
    email_id INTEGER NOT NULL,
    CONSTRAINT fk_invite_guests_invite FOREIGN KEY (invite_id) REFERENCES invite(invite_id),
    CONSTRAINT fk_invite_email FOREIGN KEY (email_id) REFERENCES emails(email_id)
);

CREATE TABLE invite_restaurant (
    invite_id INTEGER NOT NULL,
    restaurant_id INTEGER NOT NULL,
    CONSTRAINT fk_invite_restaurant_invite FOREIGN KEY (invite_id) REFERENCES invite(invite_id),
    CONSTRAINT fk_invite_restaurant_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id)
);

CREATE TABLE restaurant_approval (
    invite_id INTEGER NOT NULL,
    email_id INTEGER NOT NULL,
    restaurant_id INTEGER NOT NULL,
    approve BOOLEAN,
    CONSTRAINT fk_restaurant_approval_invite FOREIGN KEY (invite_id) REFERENCES invite(invite_id),
    CONSTRAINT fk_restaurant_approval_restaurant FOREIGN KEY (restaurant_id) REFERENCES restaurant(restaurant_id),
    CONSTRAINT fk_restaurant_approval_email FOREIGN KEY (email_id) REFERENCES emails(email_id)
);

COMMIT TRANSACTION;
