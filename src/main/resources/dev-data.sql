insert into usuario(id, email, nome, senha)
values(1, 'usuario', 'teste', '$2a$10$i5fdUAe9guh7O2UeNHepUeC/XHZG8TyOpjGtEK7cTcqZKIw/yuk7y');

insert into categoria(id, descricao, tipo_movimentacao, usuario_id)
values(1, 'Teste', 1, 1);

insert into origem(id, nome, tipo_movimentacao, usuario_id)
values(1, 'Teste', 1, 1);