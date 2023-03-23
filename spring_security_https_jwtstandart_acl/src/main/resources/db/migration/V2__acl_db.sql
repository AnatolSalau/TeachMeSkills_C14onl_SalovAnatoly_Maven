/*
      acl_sid перечисляются пользователи и роли (все в одной таблице),
      которым будут даваться разрешения:
      строковый столбец sid , в котором содержится либо имя пользователя
      (Authentication.principal.username), либо название роли.
      У нас тут будет имя пользователя:
      В столбце principal уточняется: пользователь это или роль. Значение 1 означает пользователя, а 0 — роль.
      Мы будем давать разрешения пользователям.
*/
CREATE SEQUENCE IF NOT EXISTS acl_sid_id_seq
      start 1
      increment 1;

CREATE TABLE IF NOT EXISTS acl_sid
(
      id        bigint         not null primary key,
      principal int            not null,
      sid       varchar unique not null
);
INSERT INTO acl_sid (id, principal, sid)
values (-1, 1, 'user'),
       (-2, 1, 'user2'),
       (-3, 1, 'admin');

/*
      Таблица с названиями классов acl_class
*/
CREATE SEQUENCE IF NOT EXISTS acl_class_id_seq
      start 1
      increment 1;

CREATE TABLE IF NOT EXISTS acl_class
(
      id    bigint         not null primary key,
      class varchar unique not null
);
INSERT INTO acl_class (id, class)
VALUES (-1, 'com.example.spring_security_https_jwtstandart_acl.entities.Document');

/*
      В acl_object_identity задается, на что (на какие объекты) будут даваться разрешения.
      Главное тут — object_id_identity — поле идентификатора объекта,
      но к нему идет уточнение :
      object_id_class  — класс.
      (Уточнение нужно, поскольку в таблице acl_object_identity хранятся объекты всех классов
      — будь то документы, сообщения, животные или любые другие объекты,
      на которые мы решили сделать ACL).
*/
CREATE SEQUENCE IF NOT EXISTS acl_object_identity_id_seq
      start 1
      increment 1;

CREATE TABLE IF NOT EXISTS acl_object_identity
(
      id                 bigint        not null primary key,
      object_id_class    bigint        NOT NULL references acl_class (id),
      object_id_identity bigint unique NOT NULL,
      parent_object      bigint DEFAULT NULL REFERENCES acl_object_identity (id),
      owner_sid          bigint DEFAULT NULL REFERENCES acl_sid (id),
      entries_inheriting int           NOT NULL
);
/**
  Пояснение:
      acl_object_identity.object_id_identity=document.id
      acl_object_identity.object_id_class=acl_class.id
 */
INSERT INTO acl_object_identity
(id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting)
VALUES (-1, -1, -1, NULL, -3, 0),
       (-2, -1, -2, NULL, -3, 0),
       (-3, -1, -3, NULL, -3, 0);

/**
      Самая главная таблица
      Таблица ACL_ENTRY — разрешения (какие, кому, на что)
      acl_sid — кому дается разрешение;

      acl_object_identity — на какой объект оно дается;
      mask — какое именно разрешение.
      Первые два — просто внешние ключи на предыдущие две таблицы.

            mask — разрешение.
      В Spring Security есть ряд предустановленных разрешений.
      Считается, что 1 —это READ, 2 — WRITE, 4 — CREATE… (см. класс BasePermission).
      Но они ничего не значат, в том смысле, что действуют только в уме.
      А использовать их можно как угодно. Но мы воспользуемся по назначению.
 */
CREATE TABLE IF NOT EXISTS acl_entry
(
      id                  bigint NOT NULL PRIMARY KEY,
      acl_object_identity bigint NOT NULL REFERENCES acl_object_identity (id),
      ace_order           int    NOT NULL REFERENCES acl_sid (id),
      sid                 bigint NOT NULL,
      mask                int    NOT NULL,
      granting            int    NOT NULL,
      audit_success       int    NOT NULL,
      audit_failure       int    NOT NULL
);

/*
      Мы хотим дать на объект ‘Document 1′ пользователю user
      разрешение READ , а пользователю admin — разрешения READ и WRITE.
      На каждое разрешение должна быть отдельная строка в таблице.
*/
INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
VALUES (-1, -1, -1, -1, 1, 1, 1, 1),
       (-2, -1, -2, -3, 1, 1, 1, 1),
       (-3, -1, -3, -3, 2, 1, 1, 1);

/*
      Дадим на объект ‘Document 2′ пользователю user2 разрешение READ,
      а пользователю admin — разрешения READ и WRITE:
*/
INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
VALUES (-4, -2, -1, -2, 1, 1, 1, 1),
       (-5, -2, -2, -3, 1, 1, 1, 1),
       (-6, -2, -3, -3, 2, 1, 1, 1);
/*
      Дадим на ‘Document 3′ пользователю admin разрешения READ и WRITE:
*/
INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure)
VALUES (-7, -3, -1, -3, 1, 1, 1, 1),
       (-8, -3, -2, -3, 2, 1, 1, 1);