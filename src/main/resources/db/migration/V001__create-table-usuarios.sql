create table usuarios(
    id bigint not null auto_increment,
    login varchar(100) not null unique,
    senha varchar(100) not null,
    bloqueado boolean not null default false,

    primary key(id)
);
