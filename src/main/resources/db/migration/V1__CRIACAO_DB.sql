CREATE TABLE "tb_centros_coleta" (
  "id" integer PRIMARY KEY,
  "endereco" varchar2,
  "volume_itens_total" float,
  "volume_itens_atual" float
);

CREATE TABLE "tb_catador" (
  "id" integer PRIMARY KEY,
  "capacidade_volume_total" float
);

CREATE TABLE "tb_usuario" (
  "id" long PRIMARY KEY,
  "nome" varchar2,
  "email" varchar2,
  "senha" varchar2,
  "funcao" varchar2
);

CREATE TABLE "tb_descartador" (
  "id" integer PRIMARY KEY,
  "endereco" varchar2
);

CREATE TABLE "tb_itens" (
  "id" integer PRIMARY KEY,
  "nome" varchar2,
  "volume" float
);

CREATE TABLE "tb_coletas" (
  "id_coleta" integer PRIMARY KEY,
  "data" datetime,
  "id_catador" integer,
  "id_descartador" integer,
  "id_centro" integer,
  "foi_finalizada" bool
);

CREATE TABLE "tb_coleta_itens" (
  "id_coleta" integer,
  "id_item" integer
);

CREATE TABLE "tb_catador_item" (
  "id_catador" integer,
  "id_item" integer,
  "foi_entregue" bool
);

ALTER TABLE "tb_catador" ADD FOREIGN KEY ("id") REFERENCES "tb_usuario" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "tb_descartador" ADD FOREIGN KEY ("id") REFERENCES "tb_usuario" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "tb_coletas" ADD FOREIGN KEY ("id_catador") REFERENCES "tb_usuario" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "tb_coletas" ADD FOREIGN KEY ("id_descartador") REFERENCES "tb_usuario" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "tb_coletas" ADD FOREIGN KEY ("id_centro") REFERENCES "tb_centros_coleta" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "tb_coleta_itens" ADD FOREIGN KEY ("id_coleta") REFERENCES "tb_coletas" ("id_coleta") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "tb_coleta_itens" ADD FOREIGN KEY ("id_item") REFERENCES "tb_itens" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "tb_catador_item" ADD FOREIGN KEY ("id_catador") REFERENCES "tb_usuario" ("id") DEFERRABLE INITIALLY IMMEDIATE;

ALTER TABLE "tb_catador_item" ADD FOREIGN KEY ("id_item") REFERENCES "tb_itens" ("id") DEFERRABLE INITIALLY IMMEDIATE;
