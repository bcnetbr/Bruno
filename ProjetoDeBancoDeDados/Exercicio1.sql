CREATE DATABASE Empresa;

INSERT INTO departamento(Dnome,Dnumero) VALUES ('Pesquisa',5);
INSERT INTO departamento(Dnome,Dnumero) VALUES ('Administração',4);
INSERT INTO departamento(Dnome,Dnumero) VALUES ('Matriz',1);

INSERT INTO funcionario(pnome,minicial,unome,cpf,datanasc,endereco,sexo,salario,cpf_supervisor,Dnr) 
VALUES ('John','B','Smith','12345678966','1965-01-09','731 Fondren, Houston,TX','M',3000,null,5);

INSERT INTO funcionario(pnome,minicial,unome,cpf,datanasc,endereco,sexo,salario,cpf_supervisor,Dnr) 
VALUES ('Franklin','T','Wong','33344555587','1955-12-08','638 Voss,Houston,TX','M',4000,null,5);

INSERT INTO funcionario(pnome,minicial,unome,cpf,datanasc,endereco,sexo,salario,cpf_supervisor,Dnr) 
VALUES ('Alicia','J','Zelaya','99988777767','1968-01-19','3221 Castle,Spring,TX','F',2500,null,4);

INSERT INTO funcionario(pnome,minicial,unome,cpf,datanasc,endereco,sexo,salario,cpf_supervisor,Dnr) 
VALUES ('Jennifer','S','Walace','98765432168','1941-06-20','291 Berry,Bellaire,TX','F',4300,null,4);

INSERT INTO funcionario(pnome,minicial,unome,cpf,datanasc,endereco,sexo,salario,cpf_supervisor,Dnr) 
VALUES ('Ramesh','K','Narayan','66688444476','1962-09-15','975 Fire Oak,Humble,TX','M',3800,null,5);

INSERT INTO funcionario(pnome,minicial,unome,cpf,datanasc,endereco,sexo,salario,cpf_supervisor,Dnr) 
VALUES ('Joyce','A','English','45345345376','1972-07-31','5631 Rice Houston,TX','F',2500,null,5);

INSERT INTO funcionario(pnome,minicial,unome,cpf,datanasc,endereco,sexo,salario,cpf_supervisor,Dnr) 
VALUES ('Ahmad','V','Jabbar','98798798733','1969-03-29','980,Dallas,Houston,TX','M',2500,null,4);

INSERT INTO funcionario(pnome,minicial,unome,cpf,datanasc,endereco,sexo,salario,cpf_supervisor,Dnr) 
VALUES ('James','E','Borg','88866555576','1937-11-10','450 Stone,Houston,TX','M',5500,null,1);

UPDATE funcionario SET cpf_supervisor = '33344555587' WHERE cpf = '12345678966'; 

UPDATE funcionario SET cpf_supervisor = '88866555576' WHERE cpf = '33344555587'; 

UPDATE funcionario SET cpf_supervisor = '98765432168' WHERE cpf = '99988777767'; 

UPDATE funcionario SET cpf_supervisor = '88866555576' WHERE cpf = '98765432168'; 

UPDATE funcionario SET cpf_supervisor = '33344555587' WHERE cpf = '66688444476'; 

UPDATE funcionario SET cpf_supervisor = '33344555587' WHERE cpf = '45345345376';

UPDATE funcionario SET cpf_supervisor = '98765432168' WHERE cpf = '98798798733';

INSERT INTO localizacao_dep(Dnumero,Dlocal)VALUES(1,'Houston');

INSERT INTO localizacao_dep(Dnumero,Dlocal)VALUES(4,'Stafford');

INSERT INTO localizacao_dep(Dnumero,Dlocal)VALUES(5,'Bellaire'); 

INSERT INTO localizacao_dep(Dnumero,Dlocal)VALUES(5,'Sugarland'); 

INSERT INTO projeto (Projnome,Projnumero,Projlocal,Dnum) VALUES('ProdutoX',1,'Bellaire',5);

INSERT INTO projeto (Projnome,Projnumero,Projlocal,Dnum) VALUES('ProdutoY',2,'Sugarland',5);

INSERT INTO projeto (Projnome,Projnumero,Projlocal,Dnum) VALUES('ProdutoZ',3,'Houston',5);

INSERT INTO projeto (Projnome,Projnumero,Projlocal,Dnum) VALUES('Automatizacao',10,'Stafford',4);

INSERT INTO projeto (Projnome,Projnumero,Projlocal,Dnum) VALUES('Reorganização',20,'Houston',1);

INSERT INTO projeto (Projnome,Projnumero,Projlocal,Dnum) VALUES('Qualidade',30,'Stafford',4);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('12345678966',1,32.5);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('12345678966',2,7.5);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('66688444476',3,40.0);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('45345345376',1,20.0);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('45345345376',2,20.0);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('33344555587',2,10.0);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('33344555587',3,10.0);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('33344555587',10,10.0);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('33344555587',20,10.0);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('99988777767',30,30.0);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('99988777767',10,10.0);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('98798798733',10,35.0);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('98798798733',30,5.0);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('98765432168',30,20.0);

INSERT INTO trabalha_em (Fcpf,Projnumero,Horas) VALUES('98765432168',20,15.0);

INSERT INTO dependente (Fcpf,Nome_dependente,Sexo,Datanasc,Parentesco) VALUES('33344555587','Alice','F','19860405','FILHA');

INSERT INTO dependente (Fcpf,Nome_dependente,Sexo,Datanasc,Parentesco) VALUES('33344555587','Theodore','M','19831025','FILHO');

INSERT INTO dependente (Fcpf,Nome_dependente,Sexo,Datanasc,Parentesco) VALUES('33344555587','Joy','F','19580503','CONJUGE');

INSERT INTO dependente (Fcpf,Nome_dependente,Sexo,Datanasc,Parentesco) VALUES('98765432168','Abner','M','19420228','CONJUGE');

INSERT INTO dependente (Fcpf,Nome_dependente,Sexo,Datanasc,Parentesco) VALUES('12345678966','Michael','M','19880104','FILHO');

INSERT INTO dependente (Fcpf,Nome_dependente,Sexo,Datanasc,Parentesco) VALUES('12345678966','Alice','F','19881230','FILHA');

INSERT INTO dependente (Fcpf,Nome_dependente,Sexo,Datanasc,Parentesco) VALUES('12345678966','Elizabeth','F','19670505','CONJUGE');







