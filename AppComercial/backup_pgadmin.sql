create table categoria
(
id serial,
nome varchar(100),
primary key(id)
);

create table cidade
(
	id serial,
    nome varchar(100),
    estado varchar(2),
    cep int,
    primary key(id)
);

create table usuario
(
	id serial,
    email varchar(200),
    senha varchar(8),
    foto text,
    primary key(id)
);

create table cliente
(
	id serial,
    nome varchar(100),
    email varchar(200),
    telefone varchar(20),
    cidade_id int,
    primary key(id),
    constraint fk_cidade foreign key(cidade_id) references cidade(id)
);

create table fornecedor
(
	id serial,
    nome varchar(100),
    email varchar(200),
    telefone varchar(20),
    cidade_id int,
    primary key(id),
    constraint fk_cidade foreign key(cidade_id) references cidade(id)
);

create table vendedor
(
	id serial,
    nome varchar(100),
    email varchar(200),
    telefone varchar(20),
    salario decimal(10,2),
    data_adm date,
    primary key(id)
);

create table produto
(
	id serial,
    nome varchar(100),
    descricao text,
    preco decimal(10,2),
    quantidade decimal(10,2),
    fornecedor_id int,
    categoria_id int,
    primary key(id),
    constraint fk_categoria foreign key(categoria_id) references categoria(id),
    constraint fk_fornecedor foreign key(fornecedor_id) references fornecedor(id)
);



create table movimento
(
	id serial,
    data_movimento date,
    tipo_movimento varchar(1),
    tipo_pagamento varchar(1),
    cliente_id int,
    fornecedor_id int,
    vendedor_id int,
    total decimal(10,2),
    primary key(id)
);

create table caixa
(
	id serial,
    cliente_id int,
    fornecedor_id int,
    tipo varchar(1),
    data_caixa date,
    movimento_id int,
    primary key(id)
    
);

create table item
(
	id serial,
    movimento_id int,
    produto_id int,
    quuantidade decimal(10,2),
    preco decimal(10,2),
    primary key(id)
);

create table parcela
(
	id serial,
    documento varchar(10),
    fornecedor_id int,
    cliente_id int,
    movimento_id int,
    data_pagamento date,
    data_vencimento date,
    valor decimal(10,2),
    primary key(id)
);