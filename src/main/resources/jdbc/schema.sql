CREATE SCHEMA IF NOT EXISTS public;

-- таблица аккаунтов
CREATE TABLE IF NOT EXISTS account
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    username    VARCHAR(30) UNIQUE,                     -- логин
    password    VARCHAR(30),                            -- пароль
    role        VARCHAR(30)                             -- тип аккаунта
);

-- таблица покупателей
CREATE TABLE IF NOT EXISTS customer
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_account  BIGINT,                                 -- связь с таблицей аккаунтов
    first_name  VARCHAR(30),                            -- имя
    last_name   VARCHAR(30),                            -- фамилия
    phone       VARCHAR(30),                            -- телефон
    address     VARCHAR(255)                            -- адрес
);

-- таблица магазинов
CREATE TABLE IF NOT EXISTS shop
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_account  BIGINT,                                 -- связь с таблицей аккаунтов
    name        VARCHAR(255)                            -- название магазина
);

-- таблица типов товаров
CREATE TABLE IF NOT EXISTS product_type
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    type_name   VARCHAR(255),                           -- тип товара
    photo       VARCHAR(255)                            -- ссылка на фото
);

-- таблица товаров
CREATE TABLE IF NOT EXISTS product
(
    id                  BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_product_type     BIGINT,                                 -- связь с таблицей типов товаров
    name                VARCHAR(255),                           -- название товара
    description         VARCHAR(255)                            -- описание товара
);

-- таблица фото товаров
CREATE TABLE IF NOT EXISTS product_photo
(
    id                  BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_product          BIGINT,                                 -- связь с таблицей товаров
    photo               VARCHAR(255)                            -- ссылка на фото товара
);

-- таблица для связи магазинов и товаров
CREATE TABLE IF NOT EXISTS shop_product
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_shop     BIGINT,                                 -- связь с таблицей магазинов
    id_product  BIGINT,                                 -- связь с таблицей товаров
    price       DECIMAL,                                -- цена товара
    delivery    VARCHAR(50)                             -- срок доставки
);

-- таблица для заказа
CREATE TABLE IF NOT EXISTS orders
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_customer BIGINT,                                 -- связь с таблицей покупателей
    total_price DECIMAL,                                -- стоимость заказа
    first_name  VARCHAR(30),                            -- имя
    last_name   VARCHAR(30),                            -- фамилия
    phone       VARCHAR(30),                            -- телефон
    address     VARCHAR(255),                           -- адрес доставки заказа
    status      VARCHAR(30)                             -- статус заказа
);

-- таблица для товаров в заказе
CREATE TABLE IF NOT EXISTS order_item
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY,    -- идентификатор таблицы
    id_orders       BIGINT,                                 -- связь с таблицей заказа
    id_shop_product BIGINT,                                 -- связь с товаром продавца
    quantity        INTEGER,                                -- количество товаров в заказе
    price           DECIMAL                                 -- стоимость товаров
);
