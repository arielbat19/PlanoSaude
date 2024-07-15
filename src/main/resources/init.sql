

-- Criação da tabela de usuários
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    login VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

INSERT INTO usuarios (login, password, role) VALUES
('admin', '$2a$10$bCxJyUo7wp.aAfY1RoRFReaMkhbYOduAK948f4rTTTivXG47QrhSa', 'ADMIN'); -- password: "password"



