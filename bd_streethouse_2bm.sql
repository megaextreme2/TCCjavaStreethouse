USE master if exists (SELECT * FROM SYS.databases WHERE name = 'bd_streethouse_2bm')
DROP DATABASE bd_streethouse_2bm
GO

CREATE DATABASE bd_streethouse_2bm
GO
 
USE bd_streethouse_2bm
GO


CREATE TABLE TelefoneOrganizador(
telefone_id BIGINT NOT NULL IDENTITY (1,1) PRIMARY KEY,
ddd varchar (2),
telefone VARCHAR (9) NOT NULL,
cod_status BIT NOT NULL
)
CREATE TABLE Organizador(
organizador_id BIGINT NOT NULL IDENTITY(1,1) PRIMARY KEY,
nome_organizador VARCHAR (100) NOT NULL,
CNPJ VARCHAR (14) NOT NULL,
data_nascimento DATE ,
data_fundacao DATE,
cep VARCHAR (255) NULL,
logradouro VARCHAR (100) NOT NULL,
cod_status BIT NOT NULL,
telefone_id BIGINT NULL,
CONSTRAINT fk_telefoneorganizador_organizador FOREIGN KEY (telefone_id) REFERENCES TelefoneOrganizador(telefone_id)
)


 CREATE TABLE TelefoneArtista(
telefone_id BIGINT NOT NULL IDENTITY (1,1) PRIMARY KEY,
ddd varchar (2),
telefone VARCHAR (9) NOT NULL,
cod_status BIT NOT NULL
)

CREATE TABLE Artista(
artista_id BIGINT NOT NULL IDENTITY (1,1) PRIMARY KEY,
nome_artista varchar (100) NOT NULL,
cpf VARCHAR (20) NOT NULL,
data_nascimento DATE NOT NULL,
cep VARCHAR (255) NOT NULL,
endereco VARCHAR (100) NOT NULL,
cod_status BIT NOT NULL,
telefone_id BIGINT NOT NULL,
CONSTRAINT fk_telefone_artista_id FOREIGN KEY (telefone_id) REFERENCES TelefoneArtista(telefone_id)
)

CREATE TABLE Evento(
evento_id BIGINT NOT NULL IDENTITY (1,1) PRIMARY KEY,
lote VARCHAR (100) NOT NULL,
endereco VARCHAR (100) NOT NULL,
organizador_id BIGINT NOT NULL,
artista_id BIGINT NOT NULL,
cod_status BIT NOT NULL,
CONSTRAINT fk_evento_organizador_id FOREIGN KEY (organizador_id) REFERENCES Organizador(organizador_id),
CONSTRAINT fk_evento_artista_id FOREIGN KEY (artista_id) REFERENCES Artista(artista_id)
)

 insert into Organizador(nome_organizador,CNPJ,cep,logradouro,cod_status) 
 values
 ('Alex','11234225522333','999124832','Praça_Rua',1)

 select * from Organizador
