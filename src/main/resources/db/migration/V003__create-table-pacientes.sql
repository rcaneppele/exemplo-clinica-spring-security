create table pacientes(
    id bigint not null,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(11) not null unique,

    primary key(id),
    foreign key(id) references usuarios(id)
);
