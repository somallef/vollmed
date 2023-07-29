alter table medicos add ativo tinyint;
update medicos set ativo = 1;

insert into	vollmed_api.medicos
(nome,email,crm,especialidade,logradouro,bairro,cep,complemento,numero,uf,cidade,telefone,ativo)
values('Dr Auzio','drauzio@email.com','199199','CARDIOLOGIA','Rua ABC','Jd Alfabeto','07100000','','123','SP','SAO PAULO','1122222222',1);
