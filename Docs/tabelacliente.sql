create schema teste;
use teste;
 
create table clientes(
	id int(6) auto_increment primary key,
    nome varchar(100) not null,
    cpf varchar(11) not null unique,
    email varchar(50) not null unique
);

CREATE USER 'usuario'@'localhost' IDENTIFIED BY 'P@$$w0rd';
GRANT ALL PRIVILEGES ON teste . * TO 'usuario'@'localhost';
FLUSH PRIVILEGES;