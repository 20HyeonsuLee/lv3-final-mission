CREATE TABLE schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    date DATE NOT NULL,
    time TIME NOT NULL,
    trainer_id BIGINT NOT NULL,
    FOREIGN KEY (trainer_id) REFERENCES trainer(id)
);

CREATE TABLE daily_schedule (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    time TIME NOT NULL,
    day_of_week VARCHAR(10) NOT NULL,
    trainer_id BIGINT NOT NULL,
    FOREIGN KEY (trainer_id) REFERENCES trainer(id)
);