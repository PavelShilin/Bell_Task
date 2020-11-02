CREATE TABLE IF NOT EXISTS Organization (
    id        INTEGER                      COMMENT 'Уникальный номер' PRIMARY KEY AUTO_INCREMENT,
    version   INTEGER NOT NULL             COMMENT 'Служебное поле hibernate',
    name      VARCHAR(20) NOT NULL         COMMENT 'Краткое название организации',
    full_name VARCHAR (50) NOT NULL UNIQUE COMMENT 'Полное название организации',
    inn       BIGINT NOT NULL UNIQUE       COMMENT 'ИНН организации',
    kpp       BIGINT NOT NULL              COMMENT 'КПП организации',
    address   VARCHAR(70) NOT NULL UNIQUE  COMMENT 'Юр. Адрес',
    phone     VARCHAR(20)                  COMMENT 'Телефонный номер',
    is_active BOOL                         COMMENT 'Состояние'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office(
    id        INTEGER                COMMENT 'Уникальный номер' PRIMARY KEY AUTO_INCREMENT,
    version   INTEGER     NOT NULL   COMMENT 'Служебное поле hibernate',
    org_id    INTEGER     NOT NULL   COMMENT 'id организации',
    name      VARCHAR(50) NOT NULL   COMMENT 'Название офиса ',
    phone     VARCHAR(20)            COMMENT 'телефон офиса',
    address   VARCHAR(50) NOT NULL   COMMENT 'адрес офиса',
    is_active BOOL                   COMMENT 'Статус'
);

CREATE INDEX IF NOT EXISTS IX_Office_org_id ON Office(org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);

CREATE TABLE IF NOT EXISTS User (
    id               INTEGER                COMMENT 'Уникальный номер' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER    NOT NULL    COMMENT 'Служебное поле hibernate',
    office_id        INTEGER NOT NULL       COMMENT 'id офиса работника',
    first_name       VARCHAR(50) NOT NULL   COMMENT 'Имя',
    second_name      VARCHAR(50)            COMMENT 'Фамилия',
    middle_name      VARCHAR(50)            COMMENT 'Отчество',
    position         VARCHAR(70) NOT NULL   COMMENT 'Должность',
    phone            VARCHAR(20)            COMMENT 'Номер телефона',
    doc_id           INTEGER                COMMENT 'Код документа',
    citizenship_code INTEGER                COMMENT 'код гражданства',
    is_identified    BOOL                   COMMENT 'Статус идентификации'
);

CREATE INDEX IF NOT EXISTS IX_User_office_id ON User(office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);

CREATE TABLE IF NOT EXISTS Document(
    id              INTEGER                COMMENT 'код документа' PRIMARY KEY AUTO_INCREMENT,
    version         INTEGER    NOT NULL    COMMENT 'Служебное поле hibernate',
    type_id         INTEGER    NOT NULL    COMMENT 'Тип документа',
    doc_number      VARCHAR(50)            COMMENT 'Номер документа',
    doc_date        DATE                   COMMENT 'дата выдачи документа'
);

CREATE  INDEX IF NOT EXISTS IX_User_doc_id  ON User(doc_id);
ALTER TABLE User ADD FOREIGN KEY (doc_id ) REFERENCES Document(id);

CREATE TABLE IF NOT EXISTS Type_document(
    id      INTEGER NOT NULL        COMMENT 'код документа' PRIMARY KEY,
    version INTEGER    NOT NULL     COMMENT 'Служебное поле hibernate',
    name    VARCHAR(60) NOT NULL    COMMENT 'Название документа'
);

CREATE  INDEX IF NOT EXISTS IX_Document_type_id  ON Document(type_id );
ALTER TABLE Document ADD FOREIGN KEY (type_id) REFERENCES Type_document(id);

CREATE TABLE IF NOT EXISTS  Citizenship(
    id      INTEGER NOT NULL        COMMENT 'Код Гражданства ' PRIMARY KEY,
    version INTEGER    NOT NULL     COMMENT 'Служебное поле hibernate',
    name    VARCHAR(60) NOT NULL    COMMENT 'Название страны'
);

CREATE INDEX IF NOT EXISTS IX_User_citizenship_code  ON User(citizenship_code);
ALTER TABLE User ADD FOREIGN KEY (citizenship_code) REFERENCES Citizenship(id);
