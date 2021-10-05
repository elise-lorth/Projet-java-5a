create table users
(
    id bigint auto_increment,
    constraint users_pk
        primary key (id),
    first_name TEXT not null,
    last_name TEXT not null,
    age int null,
    email TEXT null
);
create table rooms
(
    room_id bigint auto_increment,
    constraint room_pk
        primary key (room_id),
    name TEXT not null,
    capacity int not null,
    screen TEXT not null,
    tablet TEXT not null,
    board TEXT not null,
    icon TEXT null
);
create table reservations
(
    reservation_id bigint auto_increment,
    constraint reservation_pk
        primary key (reservation_id),
    room bigint,
    constraint foreign key (room) REFERENCES rooms (room_id) ON DELETE CASCADE,
    start_date datetime not null,
    end_date datetime not null
);

create table jointures
(
  jointure_id bigint auto_increment,
  constraint jointure_pk
    primary key (jointure_id),
  user bigint,
  constraint foreign key (user) REFERENCES users (id) ON DELETE CASCADE,
  reservation bigint,
  constraint foreign key (reservation) REFERENCES reservations (reservation_id) ON DELETE CASCADE
);
