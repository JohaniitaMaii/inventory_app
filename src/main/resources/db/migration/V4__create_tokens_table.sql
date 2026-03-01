CREATE TABLE tokens (
    id BIGSERIAL PRIMARY KEY,
    token VARCHAR(500) UNIQUE NOT NULL,
    token_type VARCHAR(50) DEFAULT 'BEARER',
    revoked BOOLEAN NOT NULL DEFAULT FALSE,
    expired BOOLEAN NOT NULL DEFAULT FALSE,
    usuario_id BIGINT,
    CONSTRAINT fk_usuario_token FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);