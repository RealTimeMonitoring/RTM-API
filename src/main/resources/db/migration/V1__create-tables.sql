CREATE TABLE category
(
    id                  BIGINT AUTO_INCREMENT NOT NULL,
    product_id          BIGINT NULL,
    `description`       TEXT NULL,
    type                VARCHAR(255) NULL,
    example             VARCHAR(255) NULL,
    validate_expression VARCHAR(255) NULL,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

CREATE TABLE data
(
    id            BIGINT AUTO_INCREMENT NOT NULL,
    vendor_id     VARCHAR(255) NULL,
    product_id    VARCHAR(255) NULL,
    latitude      VARCHAR(255) NULL,
    longitude     VARCHAR(255) NULL,
    value         TEXT NULL,
    `description` VARCHAR(255) NULL,
    status        VARCHAR(255) NULL,
    dt_insert     datetime NULL,
    CONSTRAINT pk_data PRIMARY KEY (id)
);

CREATE TABLE offset_entity
(
    id     BIGINT AUTO_INCREMENT NOT NULL,
    offset INT NOT NULL,
    CONSTRAINT pk_offsetentity PRIMARY KEY (id)
);