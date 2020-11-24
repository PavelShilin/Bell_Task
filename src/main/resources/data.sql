INSERT INTO  Organization(version,name,full_name,inn,kpp,address,phone,is_active) VALUES(0,'ПАО Организация-1','публичное акционерное общество организация-1',7707083893,773601001,'Россия,Москва,117997,ул.Вавилова,д.19',89002248565,TRUE);
INSERT INTO  Organization(version,name,full_name,inn,kpp,address,phone,is_active) VALUES(0,'OOO Организация-2','Общество с ограниченной ответственостью',8743083954,995601112,'Россия,Москва,223997,ул.Вахрушева,д.8',89512248735,TRUE);
INSERT INTO  Organization(version,name,full_name,inn,kpp,address,phone,is_active) VALUES(0,'ПАО Организация-1','публичное акционеное ощество оргнизация-1',770083893,77601001,'Россия,Мосва,117997,ул.Вавилова,д.19',8902248565,TRUE);


INSERT INTO Office(version,org_id,name,phone,address,is_active) VALUES( 0,1, 'Главный офис',89004454323,'Москва,ул.Вавилова,д.19',TRUE);
INSERT INTO Office(version,org_id,name,phone,address,is_active) VALUES( 0,2, 'Главный офис второй организации',89634744341,'Москва,ул.Вахрушева,д.8',TRUE);

INSERT INTO Type_document VALUES(0,21,0,'Паспорт РФ');
INSERT INTO Type_document VALUES(1,10,0,'Паспорт иностранного гражданина');

INSERT INTO Citizenship VALUES(0,243,0,'Российская Федерация');

INSERT INTO User(id,version,office_id,first_name,second_name,middle_name,position,phone,citizenship_code,is_identified)
        VALUES(0,0,1,'Иван','Иванов','Алексеевич','Разработчик',89004354354,0,TRUE);

INSERT INTO User(id,version,office_id,first_name,second_name,middle_name,position,phone,citizenship_code,is_identified)
        VALUES(1,0,2,'Петр','Вавилов','Иванович','Менеджер',89116754359,0,TRUE);

INSERT INTO Document (id,version,type_id,doc_number,doc_date) VALUES (0,0,0,9342432456,'2019-02-03');
INSERT INTO Document (id,version,type_id,doc_number,doc_date) VALUES (1,0,1,7463865459,'2018-06-07');









