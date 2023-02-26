DROP DATABASE IF EXISTS fulfillment;

CREATE DATABASE fulfillment;

ALTER DATABASE fulfillment OWNER TO "y-watanabe";

GRANT ALL PRIVILEGES ON inventory TO logical;

\connect fulfillment;

CREATE TABLE inventory (id SERIAL PRIMARY KEY, str VARCHAR(256));

ALTER TABLE public.inventory OWNER TO "y-watanabe";
