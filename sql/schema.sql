CREATE TABLE barcode_type (
    code VARCHAR(10) NOT NULL,
    description VARCHAR(50) NOT NULL,
    PRIMARY KEY (code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE barcode (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    deleted TINYINT(1) NOT NULL DEFAULT 0,
    type_code VARCHAR(10) NOT NULL,
    value VARCHAR(20) NOT NULL,
    assigned_at DATE NULL,
    metadata VARCHAR(255) NULL,

    PRIMARY KEY (id),
    UNIQUE KEY uk_barcode_value (value),

    CONSTRAINT fk_barcode_type 
        FOREIGN KEY (type_code) REFERENCES barcode_type(code),

    CHECK (deleted IN (0, 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE product (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
    deleted TINYINT(1) NOT NULL DEFAULT 0,
    name VARCHAR(120) NOT NULL,
    brand VARCHAR(80) NULL,
    category VARCHAR(80) NULL,
    price DECIMAL(10,2) NOT NULL,
    weight DECIMAL(10,3) NULL,
    bar_code_id BIGINT UNSIGNED NULL,

    PRIMARY KEY (id),
    UNIQUE KEY uk_product_bar_code_id (bar_code_id),

    CONSTRAINT product_price_chk CHECK (price > 0),
    CONSTRAINT product_weight_chk CHECK (weight IS NULL OR weight >= 0),
    CONSTRAINT product_deleted_chk CHECK (deleted IN (0,1)),

    CONSTRAINT fk_product_barcode 
        FOREIGN KEY (bar_code_id) REFERENCES barcode(id)
) ENGINE=InnoDB DEFAULT CHARSET=u
