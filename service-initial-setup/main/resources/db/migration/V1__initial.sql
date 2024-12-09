CREATE TABLE public.users(
  id SERIAL PRIMARY KEY,
  identifier VARCHAR(50) UNIQUE,
  name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  creation DATE NOT NULL
);