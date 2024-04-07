create table medicos(
    id bigint not null,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    crm varchar(10) not null unique,

    primary key(id),
    foreign key(id) references usuarios(id)
);
