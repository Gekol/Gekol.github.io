# Queries to view all books
# Books ordered by their title
SELECT b.title, a.full_name, e.title, e.date
FROM (books b JOIN authors a ON b.author_id = a.id)
         JOIN editions e ON b.edition_id = e.id
ORDER BY b.title;
# Books ordered by their author
SELECT b.title, a.full_name, e.title, e.date
FROM (books b JOIN authors a ON b.author_id = a.id)
         JOIN editions e ON b.edition_id = e.id
ORDER BY a.full_name;
# Books ordered by their edition title
SELECT b.title, a.full_name, e.title, e.date
FROM (books b JOIN authors a ON b.author_id = a.id)
         JOIN editions e ON b.edition_id = e.id
ORDER BY e.title;
# Books ordered by their edition date
SELECT b.title, a.full_name, e.title, e.date
FROM (books b JOIN authors a ON b.author_id = a.id)
         JOIN editions e ON b.edition_id = e.id
ORDER BY e.date;
# Queries to search for a book
# Get book by author's name
SELECT b.title, a.full_name, e.title, e.date
FROM (books b JOIN authors a ON b.author_id = a.id)
         JOIN editions e ON b.edition_id = e.id
WHERE a.full_name LIKE 'J%';
# Get book by book's title
SELECT b.title, a.full_name, e.title, e.date
FROM (books b JOIN authors a ON b.author_id = a.id)
         JOIN editions e ON b.edition_id = e.id
WHERE b.title LIKE 'Harry%';
# User queries
# Get books by user's subscription
SELECT u.first_name,
       u.last_name,
       b.title,
       a.full_name,
       e.title,
       e.date,
       o.deadline,
       o.fine
FROM books b
         JOIN authors a ON b.author_id = a.id
         JOIN editions e ON b.edition_id = e.id
         JOIN orders o ON o.book_id = b.id
         JOIN users u ON u.id = o.user_id
         JOIN orderTypes ot ON o.order_type_id = ot.id
WHERE ot.type = 'home'
  AND u.id = 1;
# Librarian's queries
# Get orders
SELECT u.first_name,
       u.last_name,
       u.email,
       b.title,
       a.full_name,
       e.title,
       e.date,
       o.book_amount,
       ot.type,
       o.deadline
FROM orders o
         JOIN users u on o.user_id = u.id
         JOIN books b ON b.id = o.book_id
         JOIN editions e on b.edition_id = e.id
         JOIN authors a on b.author_id = a.id
         JOIN orderTypes ot ON o.order_type_id = ot.id;
# Get users and subscriptions
SELECT u.first_name,
       u.last_name,
       u.email,
       us.status,
       s.given,
       s.expires
FROM users u
         JOIN subscriptions s on u.subscription_id = s.id
         JOIN userStatuses us ON u.user_status_id = us.id;
# Admin's query
INSERT INTO authors(full_name) VALUES ('Joanne Rowling');
INSERT INTO editions(title, date) VALUES ('edition 4', '2020-2-25');
INSERT INTO books(title, author_id, edition_id)
VALUES ('Harry Potter and the philosopher\'s stone', 1, 1);