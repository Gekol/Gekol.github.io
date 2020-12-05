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
INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id, subscription_id)
VALUES ('Darth', 'Vader', 'vader@example.com', '1111', 3, 1, 1);
INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id, subscription_id)
VALUES ('Luke', 'Skywalker', 'lskywalker@example.com', '1111', 2, 1, 1);
INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id, subscription_id)
VALUES ('Mace', 'Vindu', 'vindu@example.com', '1111', 1, 1, 1);
INSERT INTO users(first_name, last_name, email, user_password, user_type_id, user_status_id, subscription_id)
VALUES ('Obi-Wan', 'Kenobi', 'kenobi@example.com', '1111', 1, 1, 1);

# Author inserts
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('rowling', 'joanne rowling', 'джоан роулинг');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('lermontov', 'michael lermontov', 'михаил лермонтов');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('pushkin', 'alexander pushkin', 'александр пушкин');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('tolstoy', 'lev tolstoy', 'лев толстой');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('aligeri', 'dante alig`eri', 'данте алигьери');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('bulgakov', 'michael bulgakov', 'михаил булгаков');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('turgenev', 'ivan turgenev', 'иван тургенев');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('shakespeare', 'william shakespeare', 'уильям шекспир');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('balsak', 'onore de balsak', 'оноре де бальзак');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('stendal', 'stendal', 'стендаль');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('gofman', 'ernst gofman', 'эрнст гофман');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('cooper', 'james fenimore cooper', 'джеймс фенимор купер');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('ostin', 'jane ostin', 'джейн остин');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('nabokov', 'vladimir nabokov', 'владимир набоков');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('swift', 'jonathan swift', 'джонатан сфивт');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('orwell', 'george orwell', 'джордж оруэлл');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('bradbury', 'ray bradbury', 'рэй брэдберри');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('fitzgerald', 'francis scott fitzgerald', 'фрэнсис скотт фицджеральд');
INSERT INTO authors(id, full_name_en, full_name_ru)
VALUES ('dostoevskyi', 'fedor dostoevskyi', 'фёдор достоевский');

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
        'harry_potter_and_the_philosopher''s_stone.webp', 10, 5, 'rowling',
        1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Harry Potter and the Chamber of Secrets', 'Гарри Поттер и тайная комната',
        'harry_potter_and_the_chamber_of_secrets.jpg', 10, 5, 'rowling', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Harry Potter and the Prisoner of Azkaban', 'Гарри Поттер и узник Азкабана',
        'harry_potter_and_the_prisoner_of_azkaban.jpg', 10, 5, 'rowling',
        1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Harry Potter and the Goblet of Fire', 'Гарри Поттер и кубок огня', 'harry_potter_and_the_goblet_of_fire.webp',
        10, 5, 'rowling', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Harry Potter and the Order of Phoenix', 'Гарри Поттер и орден феникса',
        'harry_potter_and_the_order_of_phoenix.jpg', 10, 5, 'rowling', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Harry Potter and the Half-blood prince', 'Гарри Поттер и принц-полукровка',
        'harry_potter_and_the_half-blood_prince.webp', 10, 5, 'rowling', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Harry Potter and the Deathly Hallows', 'Гарри Поттер и дары смерти',
        'harry_potter_and_the_deathly_hallows.webp', 10, 5, 'rowling', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Fahrenheit 451', '451 градус по Фаренгейту', 'fahrenheit-451.jpg', 10, 5, 'bradbury', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('1984', '1984', '1984.png', 10, 5, 'orwell', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Anna Karenina', 'Анна Каренина', 'anna_karenina.webp', 10, 5, 'tolstoy', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Crime and punishment', 'Преступление и наказание', 'crime_and_punishment.webp', 10, 5, 'dostoevskyi', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Divine comedy', 'Божественная комедия', 'divine_comedy.jpg', 10, 5, 'aligeri', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Dog''s heart', 'Собачье сердце', 'dog''s_heart.jpg', 10, 5, 'bulgakov', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Evgeniy Onegin', 'Евгений Онегин', 'evgeniy_onegin.jpg', 10, 5, 'pushkin', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Fathers and children', 'Отцы и дети', 'fathers_and_children.jpg', 10, 5, 'turgenev', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Gamlet', 'Гамлет', 'gamlet.jpeg', 10, 5, 'shakespeare', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Gobsek', 'Гобсек', 'gobsek.webp', 10, 5, 'balsak', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Great Gatsby', 'Великий Гэтсби', 'great_gatsby.jpg', 10, 5, 'fitzgerald', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Gulliver''s travels', 'Путешествие Гулливера', 'gulliver''s_travels.jpg', 10, 5, 'swift', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Hero of our time', 'Герой нашего времени', 'hero_of_our_time.jpg', 10, 5, 'lermontov', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Lolita', 'Лолита', 'lolita.webp', 10, 5, 'nabokov', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Master and Margaryta', 'Мастер и маргарита', 'master_and_margaryta.jpg', 10, 5, 'bulgakov', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Pride and prejudice', 'Гордость и предубеждение', 'pride_and_prejudice.jpg', 10, 5, 'ostin', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Red and black', 'Красное и чёрное', 'red_and_black.jpg', 10, 5, 'stendal', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('Romeo and Juliet', 'Ромео и Джульетта', 'romeo_i_juliet.jpg', 10, 5, 'shakespeare', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('The last of the mohicans', 'Последний из могикан', 'the_last_of_the_mohicans.jpeg', 10, 5, 'cooper', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('The nutcracker and the mouse king', 'Щелкунчик и мышиный король', 'the_nutcracker_and_the_mouse_king.jpg', 10,
        5, 'gofman', 1);
INSERT INTO books(title_en, title_ru, book_src, price, fine, author_id, edition_id)
VALUES ('War and peace', 'Война и мир', 'war_and_peace.jpg', 10, 5, 'tolstoy', 1);

# Catalog inserts
INSERT INTO catalog(book_id, book_amount)
VALUES (1, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (2, 8);
INSERT INTO catalog(book_id, book_amount)
VALUES (3, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (4, 8);
INSERT INTO catalog(book_id, book_amount)
VALUES (5, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (6, 8);
INSERT INTO catalog(book_id, book_amount)
VALUES (7, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (8, 8);
INSERT INTO catalog(book_id, book_amount)
VALUES (9, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (10, 8);
INSERT INTO catalog(book_id, book_amount)
VALUES (11, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (12, 8);
INSERT INTO catalog(book_id, book_amount)
VALUES (13, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (14, 8);
INSERT INTO catalog(book_id, book_amount)
VALUES (15, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (16, 8);
INSERT INTO catalog(book_id, book_amount)
VALUES (17, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (18, 8);
INSERT INTO catalog(book_id, book_amount)
VALUES (19, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (20, 8);
INSERT INTO catalog(book_id, book_amount)
VALUES (21, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (22, 8);
INSERT INTO catalog(book_id, book_amount)
VALUES (23, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (24, 8);
INSERT INTO catalog(book_id, book_amount)
VALUES (25, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (26, 8);
INSERT INTO catalog(book_id, book_amount)
VALUES (27, 5);
INSERT INTO catalog(book_id, book_amount)
VALUES (28, 8);

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
INSERT INTO orders(user_id, book_id, fine, deadline, order_status_id, order_type_id)
VALUES (1, 1, 0, '2020-10-9', 1, 1);
INSERT INTO orders(user_id, book_id, fine, deadline, order_status_id, order_type_id)
VALUES (3, 2, 0, '2020-10-15', 2, 1);
