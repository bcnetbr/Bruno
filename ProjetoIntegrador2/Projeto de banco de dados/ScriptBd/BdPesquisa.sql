CREATE DATABASE Pesquisa;

CREATE TABLE jogos(

	id_jogo serial,
	nome varchar(50),
	PRIMARY KEY(id_jogo)
	
)

CREATE TABLE Perguntas(

	pergunta1 int,
	pergunta2 int,
	pergunta3 int,
	pergunta4 int,
	pergunta5 int,
	id_jogo int not null,
	
	FOREIGN KEY(id_jogo) REFERENCES jogos(id_jogo)
	
)

CREATE TABLE Dados_jogos(

	ponto_jogo int,
	tempo_jogo double precision,
	id_jogo int not null,
	
	FOREIGN KEY(id_jogo) REFERENCES jogos(id_jogo)
	
)

