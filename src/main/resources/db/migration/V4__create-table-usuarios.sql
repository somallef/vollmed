create table usuarios(

    id bigint not null auto_increment,
    login varchar(100) not null,
    senha varchar(255) not null,


    primary key(id)

);

INSERT INTO vollmed_api.usuarios
(login, senha)
VALUES(
'admin',
'$2a$10$Y50UaMFOxteibQEYLrwuHeehHYfcoafCopUazP12.rqB41bsolF5.' --senha = 123456
);