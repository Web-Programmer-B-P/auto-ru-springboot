create table users
(
    id       serial not null
        constraint users_pk
            primary key,
    username varchar(200),
    email    varchar(200),
    password varchar(200),
    phone    varchar(50),
    address  varchar(200),
    enabled  smallint
);

create unique index users_username_uindex
    on users (username);

create table engine
(
    id          serial not null
        constraint engine_pk
            primary key,
    fuel_type   varchar(50),
    horse_power varchar(50)
);

create table car
(
    id            serial not null
        constraint car_pk
            primary key,
    name          varchar(100),
    color         varchar(100),
    year_of_issue varchar(100),
    km_age        varchar(200),
    handlebar     varchar(20),
    transmission  varchar(100),
    wheel_drive   varchar(100),
    engine_id     integer
        constraint fk_engine
            references engine,
    car_body      varchar(200)
);

create table advertisement
(
    id          serial not null
        constraint advertisement_pk
            primary key,
    description text,
    sale_status boolean default false,
    price       varchar(20),
    create_date timestamp,
    car_id      integer
        constraint fk_car
            references car,
    user_id     integer
        constraint fk_user
            references users
);

create table images
(
    id               serial not null
        constraint images_pk
            primary key,
    file_name        varchar(200),
    image            bytea,
    advertisement_id integer
        constraint fk_advertisement
            references advertisement
);

create table authorities
(
    username  varchar(100) not null
        constraint fk_username
            references users (username),
    authority varchar(100) not null,
    id        serial       not null
        constraint authorities_pk
            primary key,
    constraint username_authority
        unique (username, authority)
);