CREATE TABLE IF NOT EXISTS user (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      email VARCHAR(255) NOT NULL UNIQUE,
                      tenant_id VARCHAR(255) NOT NULL
);