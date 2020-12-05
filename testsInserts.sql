# UserTypes inserts
INSERT INTO userTypes(type_en, type_ru)
VALUES ('User', 'Пользователь');
INSERT INTO userTypes(type_en, type_ru)
VALUES ('Librarian', 'Библиотекарь');
INSERT INTO userTypes(type_en, type_ru)
VALUES ('Admin', 'Администратор');

# UserStatuses inserts
INSERT INTO userStatuses(status_en, status_ru)
VALUES ('Active', 'Активный');
INSERT INTO userStatuses(status_en, status_ru)
VALUES ('Blocked', 'Заблокированный');

# Subscriptions inserts
INSERT INTO subscriptions(given, expires)
VALUES ('2020-3-15', '2020-12-15');
INSERT INTO subscriptions(given, expires)
VALUES ('2020-3-15', '2020-11-15');
INSERT INTO subscriptions(given, expires)
VALUES ('2020-3-15', '2020-10-15');

# Users inserts
INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id)
VALUES ('Darth', 'Vader', 'vader@example.com', '1111', 3, 1);
INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id)
VALUES ('Luke', 'Skywalker', 'lskywalker@example.com', '1111', 2, 1);
INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id)
VALUES ('Mace', 'Vindu', 'vindu@example.com', '1111', 1, 1);
INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id)
VALUES ('Obi-Wan', 'Kenobi', 'kenobi@example.com', '1111', 1, 1);

# Author inserts
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('rowling', 'joanne rowling', 'джоан роулинг');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('rand', 'ayn rand', 'айн рэнд');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('greene', 'robert greene', 'роберт грин');

# Edition inserts
INSERT INTO editions(title_en, title_ru, date)
VALUES ('Edition 1', 'Издание 1', '2020-1-1');
INSERT INTO editions(title_en, title_ru, date)
VALUES ('Edition 2', 'Издание 2', '2020-2-1');
INSERT INTO editions(title_en, title_ru, date)
VALUES ('Edition 3', 'Издание 3', '2020-3-1');

# Books inserts
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Harry Potter and the Philosopher\'s stone', 'Гарри Поттер и философский камень',
        'harry_potter_and_the_philosopher''s_stone.webp', 10, 5, 'rowling', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Harry Potter and the Chamber of Secrets', 'Гарри Поттер и тайная комната',
        'harry_potter_and_the_chamber_of_secrets.jpg', 10, 5, 'rowling', 1);

# Catalog inserts
INSERT INTO catalog(book_id, book_amount)
VALUES (1, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (2, 8);

# OrderTypes inserts
INSERT INTO orderTypes(type_en, type_ru)
VALUES ('Subscription', 'Абонемент');
INSERT INTO orderTypes(type_en, type_ru)
VALUES ('Reading hall', 'Читальный зал');

# OrderStatuses inserts
INSERT INTO orderStatuses(status_en, status_ru)
VALUES ('New', 'Новый');
INSERT INTO orderStatuses(status_en, status_ru)
VALUES ('Awaiting return', 'Ожидание возвращения');
INSERT INTO orderStatuses(status_en, status_ru)
VALUES ('Done', 'Выполнено');

# Orders inserts
INSERT INTO orders(user_id, book_id, order_status_id, order_type_id)
VALUES (1, 1, 1, 1);
INSERT INTO orders(user_id, book_id, order_status_id, order_type_id)
VALUES (3, 2, 2, 1);
