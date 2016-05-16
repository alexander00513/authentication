CREATE TABLE PUBLIC.authentication
(
  id           INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
  email        VARCHAR(128)                   NOT NULL,
  password     VARCHAR(128)                   NOT NULL,
  token        VARCHAR(36),
  token_expire TIMESTAMP
);
CREATE UNIQUE INDEX "authentication_email_uindex" ON PUBLIC.authentication (email);