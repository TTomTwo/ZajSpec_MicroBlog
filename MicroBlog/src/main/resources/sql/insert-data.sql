-- Użytkownicy (hasła to 'password123' zahashowane przez bcrypt)
INSERT INTO uzytkownik(login, haslo, email) VALUES ('jankowalski', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'jan@example.com');
INSERT INTO uzytkownik(login, haslo, email) VALUES ('annanowak', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'anna@example.com');
INSERT INTO uzytkownik(login, haslo, email) VALUES ('piotrzmuda', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'piotr@example.com');
INSERT INTO uzytkownik(login, haslo, email) VALUES ('mariakowal', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'maria@example.com');
INSERT INTO uzytkownik(login, haslo, email) VALUES ('admin', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'admin@example.com');

-- Relacje follower (kto kogo obserwuje)
-- Jan obserwuje Annę i Piotra
INSERT INTO follower(uzytkownik_id, follower_id) VALUES (2, 1);
INSERT INTO follower(uzytkownik_id, follower_id) VALUES (3, 1);

-- Anna obserwuje Jana i Marię
INSERT INTO follower(uzytkownik_id, follower_id) VALUES (1, 2);
INSERT INTO follower(uzytkownik_id, follower_id) VALUES (4, 2);

-- Piotr obserwuje wszystkich
INSERT INTO follower(uzytkownik_id, follower_id) VALUES (1, 3);
INSERT INTO follower(uzytkownik_id, follower_id) VALUES (2, 3);
INSERT INTO follower(uzytkownik_id, follower_id) VALUES (4, 3);

-- Maria obserwuje Annę
INSERT INTO follower(uzytkownik_id, follower_id) VALUES (2, 4);

-- Admin obserwuje wszystkich
INSERT INTO follower(uzytkownik_id, follower_id) VALUES (1, 5);
INSERT INTO follower(uzytkownik_id, follower_id) VALUES (2, 5);
INSERT INTO follower(uzytkownik_id, follower_id) VALUES (3, 5);
INSERT INTO follower(uzytkownik_id, follower_id) VALUES (4, 5);

-- Wpisy użytkowników
INSERT INTO wpis(uzytkownik_id, tresc) VALUES (1, 'Witam wszystkich na moim mikroblogu!');
INSERT INTO wpis(uzytkownik_id, tresc) VALUES (1, 'Dzisiaj piękna pogoda, idę na spacer.');
INSERT INTO wpis(uzytkownik_id, tresc) VALUES (2, 'Właśnie skończyłam czytać świetną książkę!');
INSERT INTO wpis(uzytkownik_id, tresc) VALUES (2, 'Ktoś poleci dobry film na wieczór?');
INSERT INTO wpis(uzytkownik_id, tresc) VALUES (3, 'Programowanie w Javie jest fajne, ale czasem frustrujące...');
INSERT INTO wpis(uzytkownik_id, tresc) VALUES (3, 'Maven znowu mi nie działa. Typowy dzień dewelopera.');
INSERT INTO wpis(uzytkownik_id, tresc) VALUES (4, 'Herbata czy kawa? Oto jest pytanie!');
INSERT INTO wpis(uzytkownik_id, tresc) VALUES (4, 'Weekend w górach był wspaniały!');
INSERT INTO wpis(uzytkownik_id, tresc) VALUES (5, 'Testowy wpis admina - wszystko działa poprawnie.');
INSERT INTO wpis(uzytkownik_id, tresc) VALUES (1, 'Uczę się Spring Framework, bardzo ciekawa technologia.');
INSERT INTO wpis(uzytkownik_id, tresc) VALUES (2, 'Właśnie ugotowałam pyszny obiad :)');
INSERT INTO wpis(uzytkownik_id, tresc) VALUES (3, 'Polecam wszystkim naukę SQL, bardzo przydatne!');