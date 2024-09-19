CREATE TABLE IF NOT EXISTS postgres.email_token (
    id serial PRIMARY KEY,
    token varchar(256) NOT NULL,
    expire timestamp(6) NOT NULL,
    consumed boolean DEFAULT false,
    register timestamp WITH TIME ZONE DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS postgres.users (
    id serial PRIMARY KEY,
    username varchar(32) NOT NULL,
    password varchar(256) NOT NULL,
    email varchar(56) NOT NULL,
    register timestamp WITH TIME ZONE DEFAULT NOW(),
    email_token_id int NOT NULL UNIQUE,
    CONSTRAINT fk_email_token FOREIGN KEY(email_token_id) REFERENCES postgres.email_token
);

CREATE TABLE IF NOT EXISTS postgres.auditory (
    id serial PRIMARY KEY,
    message text NOT NULL,
    register date NULL DEFAULT NOW()
);