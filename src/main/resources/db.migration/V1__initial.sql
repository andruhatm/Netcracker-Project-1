create table clients
(
    id              integer      not null
        constraint clients_pkey
            primary key,
    surname         varchar(100) not null,
    first_name      varchar(100) not null,
    patronymic      varchar(100),
    date_of_birth   date         not null,
    gender          varchar(6)   not null,
    number_passport integer      not null,
    series_passport integer      not null
);

create table contracts
(
    id              integer     not null
        constraint contracts_pkey
            primary key,
    start_date      date        not null,
    end_date        date        not null,
--     number_contract integer     not null,
    minutes         integer,
    gb_internet     float,
    sms             integer,
    maximum_speed   double precision,
    package_channel varchar(20),
    contract_type   varchar(50) not null,
    client_id       integer     not null
        constraint "FK_contract_client"
            references clients
);

