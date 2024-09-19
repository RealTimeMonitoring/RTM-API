CREATE TABLE wmcategory
(
    id                  BIGINT       NOT NULL,
    product_id          BIGINT       NULL,
    `description`       TEXT         NULL,
    type                VARCHAR(255) NULL,
    example             VARCHAR(255) NULL,
    validate_expression VARCHAR(255) NULL,
    CONSTRAINT pk_wmcategory PRIMARY KEY (id)
);

CREATE TABLE wmdata
(
    id         BIGINT       NOT NULL,
    vendor_id  VARCHAR(255) NULL,
    product_id VARCHAR(255) NULL,
    latitude   VARCHAR(255) NULL,
    longitude  VARCHAR(255) NULL,
    value      TEXT         NULL,
    dt_insert  datetime     NULL,
    CONSTRAINT pk_wmdata PRIMARY KEY (id)
);