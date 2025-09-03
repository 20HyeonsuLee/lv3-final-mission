CREATE TABLE reservation (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    schedule_id BIGINT NOT NULL UNIQUE,
    customer_id BIGINT NOT NULL,
    FOREIGN KEY (schedule_id) REFERENCES schedule(id),
    FOREIGN KEY (customer_id) REFERENCES customer(id)
);