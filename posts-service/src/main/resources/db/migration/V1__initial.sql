CREATE TABLE public.posts(
    id SERIAL PRIMARY KEY,
    content VARCHAR(244) NOT NULL
);

CREATE TABLE public.media(
    id SERIAL PRIMARY KEY,
    post_id INT NOT NULL,
    media_type VARCHAR(30) NOT NULL,
    media_path VARCHAR(255) NOT NULL,
    CONSTRAINT fk_posts FOREIGN KEY(post_id) REFERENCES posts(id)
);