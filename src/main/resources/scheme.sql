-- remove existing tables
DROP TABLE IF EXISTS users         CASCADE;
DROP TABLE IF EXISTS roles         CASCADE;
DROP TABLE IF EXISTS user_role     CASCADE;
DROP TABLE IF EXISTS groups        CASCADE;
DROP TABLE IF EXISTS user_group    CASCADE;
DROP TABLE IF EXISTS photos         CASCADE;
DROP TABLE IF EXISTS photo_share   CASCADE;
DROP TABLE IF EXISTS messages      CASCADE;
DROP TABLE IF EXISTS message_share CASCADE;

-- a user in the system.
-- In production, the password field must be either a hash of a real password
-- or an encrypted real password.
-- Use clear text for now.
CREATE TABLE users (
  id         SERIAL PRIMARY KEY,
  first_name VARCHAR(128),
  last_name  VARCHAR(128) NOT NULL,
  email      VARCHAR(256) NOT NULL,
  password   VARCHAR(256) NOT NULL
);

-- role names
CREATE TABLE roles (
  id         SERIAL PRIMARY KEY,
  name       VARCHAR(128)
);


-- one user can be assigned multiple roles and multiple users can have same roles as well.
CREATE TABLE user_role (
  id      SERIAL PRIMARY KEY,
  user_id INTEGER REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
  role_id INTEGER REFERENCES roles (id) ON UPDATE CASCADE ON DELETE CASCADE,
  UNIQUE (user_id, role_id)
);

-- a set of user is a group.
CREATE TABLE groups(
  id      SERIAL PRIMARY KEY,
  name    VARCHAR(128),
  owner_id   INTEGER REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- one user can be in multiple groups. a group can contain many users
CREATE TABLE user_group (
  id      SERIAL PRIMARY KEY,
  user_id INTEGER REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
  group_id INTEGER REFERENCES groups (id) ON UPDATE CASCADE ON DELETE CASCADE,
  UNIQUE (user_id, group_id)
);

-- table that holds uploaded photos. the uuid field will be used as file name on disk
CREATE TABLE photos (
  id       SERIAL PRIMARY KEY,
  owner_id INTEGER REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
  comments VARCHAR(256),
  uuid     VARCHAR(64)
);

-- user can share photos to a group or a set of users.
CREATE TABLE photo_share (
  id       SERIAL PRIMARY KEY,
  photo_id   INTEGER REFERENCES photos (id) ON UPDATE CASCADE ON DELETE CASCADE,
  group_id INTEGER REFERENCES groups (id) ON UPDATE CASCADE ON DELETE CASCADE,
  user_id  INTEGER REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE
);

-- message is a short piece of text for chat
CREATE TABLE messages (
  id        SERIAL PRIMARY KEY,
  message   TEXT NOT NULL,
  sender_id INTEGER REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
  ctime     TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

-- a message can be sent to some users or a group.
CREATE TABLE message_share (
  id         SERIAL PRIMARY KEY,
  message_id INTEGER REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
  to_group   INTEGER REFERENCES groups (id) ON UPDATE CASCADE ON DELETE CASCADE,
  to_user  INTEGER REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE
);

