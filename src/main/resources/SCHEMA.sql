create table tuser
(
  id        bigint primary key,
  email     text unique,
  password  text,
  firstname text,
  lastname  text,
  created   timestamp default now(),
  modified  timestamp,
  deleted   timestamp
);

create table tcat
(
  cat_id    bigint primary key,
  owner_id    bigint references tuser (id),
  name        text,
  age         int,
  description text,
  created     timestamp default now(),
  deleted     timestamp
);

create table timage
(
  image_link text primary key,
  image_blob blob,
  cat_id bigint references tcat(cat_id)
);

create table tpair (
  pair_id bigint primary key,
  cat_id bigint references tcat(cat_id),
  cat_two_id bigint references tcat(cat_id)
);

create table tlike (
  like_id bigint primary key,
  pair_id bigint references tpair(pair_id),
  user_id bigint references tuser(id)
);

