create table users
(
    id bigint auto_increment,
    constraint users_pk
        primary key (id),
    first_name TEXT not null,
    last_name TEXT not null,
    age int null
);
create table rooms
(
    room_id bigint auto_increment,
    constraint room_pk
        primary key (room_id),
    name TEXT not null,
    capacity int not null,
    screen bool not null,
    tablet bool not null,
    board bool not null
);
create table meetup
(
    meetup_id bigint,
    room bigint,
    constraint foreign key (room) REFERENCES rooms (room_id) ON DELETE CASCADE,
    start_date date not null,
    end_date date not null,
    member bigint,
    constraint foreign key (member) REFERENCES users (id) ON DELETE CASCADE
);
