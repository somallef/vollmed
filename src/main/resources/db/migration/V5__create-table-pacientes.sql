create table pacientes(

    id bigint not null auto_increment,
    nome varchar(100) not null,
    email varchar(100) not null unique,
    cpf varchar(14) not null unique,
    logradouro varchar(100) not null,
    bairro varchar(100) not null,
    cep varchar(9) not null,
    complemento varchar(100),
    numero varchar(20),
    uf char(2) not null,
    cidade varchar(100) not null,
    telefone varchar(20) not null,
    ativo tinyint not null,

    primary key(id)

);

INSERT INTO vollmed_api.pacientes
(nome, email, cpf, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone, ativo)
values('Paciente Ingles','paciente_ingles@email.com','78079343012','Rua 123','Jd Numeros','07299999','','999','SP','SAO PAULO','1133333333',1);