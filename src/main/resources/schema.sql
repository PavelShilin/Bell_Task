
CREATE TABLE IF NOT EXISTS Organization (
    id        INTEGER                      COMMENT 'Уникальный номер' PRIMARY KEY AUTO_INCREMENT,
    version   INTEGER NOT NULL             COMMENT 'Служебное поле hibernate',
    name      VARCHAR(60) NOT NULL         COMMENT 'Краткое название организации',
    full_name VARCHAR (80)                 COMMENT 'Полное название организации',
    inn       BIGINT NOT NULL              COMMENT 'ИНН организации',
    kpp       BIGINT NOT NULL              COMMENT 'КПП организации',
    address   VARCHAR(70) NOT NULL         COMMENT 'Юр. Адрес',
    phone     VARCHAR(20)                  COMMENT 'Телефонный номер',
    is_active BOOL                         COMMENT 'Состояние'
);
COMMENT ON TABLE Organization IS 'Организация';

CREATE TABLE IF NOT EXISTS Office(
    id        INTEGER                COMMENT 'Уникальный номер' PRIMARY KEY AUTO_INCREMENT,
    version   INTEGER     NOT NULL   COMMENT 'Служебное поле hibernate',
    org_id    INTEGER                COMMENT 'id организации',
    name      VARCHAR(50)            COMMENT 'Название офиса ',
    phone     VARCHAR(20)            COMMENT 'телефон офиса',
    address   VARCHAR(50)            COMMENT 'адрес офиса',
    is_active BOOL                   COMMENT 'Статус'
);

CREATE INDEX IF NOT EXISTS IX_Office_org_id ON Office(org_id);
ALTER TABLE Office ADD FOREIGN KEY (org_id) REFERENCES Organization(id);

CREATE TABLE IF NOT EXISTS User (
    id               INTEGER                COMMENT 'Уникальный номер' PRIMARY KEY AUTO_INCREMENT,
    version          INTEGER    NOT NULL    COMMENT 'Служебное поле hibernate',
    office_id        INTEGER                COMMENT 'id офиса работника',
    first_name       VARCHAR(50) NOT NULL   COMMENT 'Имя',
    second_name      VARCHAR(50)            COMMENT 'Фамилия',
    middle_name      VARCHAR(50)            COMMENT 'Отчество',
    position         VARCHAR(70) NOT NULL   COMMENT 'Должность',
    phone            VARCHAR(20)            COMMENT 'Номер телефона',
    citizenship_code INTEGER                COMMENT 'код гражданства',
    is_identified    BOOL                   COMMENT 'Статус идентификации'
);

CREATE INDEX IF NOT EXISTS IX_User_office_id ON User(office_id);
ALTER TABLE User ADD FOREIGN KEY (office_id) REFERENCES Office(id);

CREATE TABLE IF NOT EXISTS Document(
    id              INTEGER                COMMENT 'id документа',
    version         INTEGER    NOT NULL    COMMENT 'Служебное поле hibernate',
    type_id         INTEGER    NOT NULL    COMMENT 'Тип документа',
    doc_number      VARCHAR(50)            COMMENT 'Номер документа',
    doc_date        DATE                   COMMENT 'дата выдачи документа'
);

CREATE UNIQUE INDEX IF NOT EXISTS UX_Document_id  ON Document(id);
ALTER TABLE Document ADD FOREIGN KEY (id) REFERENCES User(id);

CREATE TABLE IF NOT EXISTS Type_document(
    id      INTEGER NOT NULL        COMMENT 'id документа' PRIMARY KEY AUTO_INCREMENT,
    code    INTEGER                 COMMENT 'Код документа',
    version INTEGER    NOT NULL     COMMENT 'Служебное поле hibernate',
    name    VARCHAR(60)             COMMENT 'Название документа'
);

CREATE  INDEX IF NOT EXISTS IX_Document_type_id  ON Document(type_id );
ALTER TABLE Document ADD FOREIGN KEY (type_id) REFERENCES Type_document(id);

CREATE TABLE IF NOT EXISTS  Citizenship(
    id      INTEGER NOT NULL        COMMENT 'id Гражданства' PRIMARY KEY AUTO_INCREMENT,
    code    INTEGER                 COMMENT 'код гражданства',
    version INTEGER    NOT NULL     COMMENT 'Служебное поле hibernate',
    name    VARCHAR(60)             COMMENT 'Название страны'
);

CREATE INDEX IF NOT EXISTS IX_User_citizenship_code  ON User(citizenship_code);
ALTER TABLE User ADD FOREIGN KEY (citizenship_code) REFERENCES Citizenship(id);
