INSERT  INTO ADMINISTRATORS  VALUES(1,'admin','admin');
INSERT into CATEGORY values(1,'カテゴリ1');
INSERT into CATEGORY values(2,'カテゴリ2');
INSERT into CATEGORY values(3,'カテゴリ3');
INSERT into CATEGORY values(4,'カテゴリ4');

INSERT into DEPARTMENT values(1,'部署1');
INSERT into DEPARTMENT values(2,'部署2');
INSERT into DEPARTMENT values(3,'部署3');
INSERT into DEPARTMENT values(4,'部署4');
INSERT into DEPARTMENT values(5,'部署5');
INSERT into DEPARTMENT values(6,'人事6');

INSERT INTO EMPLOYEES values(1, '社員1', 1 , '1', '0000', null, 'emp', 0);
INSERT INTO EMPLOYEES values(2, '社員2', 2 , '2', '0000', null, 'emp', 0);
INSERT INTO EMPLOYEES values(3, '社員3', 3 , '3', '0000', null, 'emp', 0);
INSERT INTO EMPLOYEES values(4, '社員4', 1 , '4', '0000', null, 'emp', 0);
INSERT INTO EMPLOYEES values(5, '社員5', 2 , '5', '0000', null, 'emp', 0);
INSERT INTO EMPLOYEES values(6, '社員6', 3 , '6', '0000', null, 'emp', 0);
INSERT INTO EMPLOYEES values(7, '社員7', 1 , '7', '0000', null, 'emp', 1);
INSERT INTO EMPLOYEES values(8, '人事8', 6 , '8', '0000','人事' , 'zin', 0);
INSERT INTO EMPLOYEES values(9, '経営9', 1 , '9', '0000', '経営陣', 'kei', 0);


INSERT into GRATITUDE_CARD values(1,1,2,'2014-4-12 11:00:00', 'カード1', 'カード1内容', 1);
INSERT into GRATITUDE_CARD values(2,2,1,'2016-4-12 11:00:00', 'カード2', 'カード2内容', 2);
INSERT into GRATITUDE_CARD values(3,2,3,'2016-4-13 11:00:00', 'カード3', 'カード3内容', 1);
INSERT into GRATITUDE_CARD values(4,1,2,'2016-4-14 11:00:00', 'カード4', 'カード4内容', 1);
INSERT into GRATITUDE_CARD values(5,6,1,'2015-4-12 11:00:00', 'カード5', 'カード5内容', 2);
INSERT into GRATITUDE_CARD values(6,4,3,'2016-4-18 11:00:00', 'カード6', 'カード6内容', 3);
INSERT into GRATITUDE_CARD values(7,1,2,'2014-5-12 11:00:00', 'カード7', 'カード7内容', 1);
INSERT into GRATITUDE_CARD values(8,2,1,'2016-5-12 11:00:00', 'カード8', 'カード8内容', 2);
INSERT into GRATITUDE_CARD values(9,2,3,'2016-5-13 11:00:00', 'カード9', 'カード9内容', 1);
INSERT into GRATITUDE_CARD values(10,1,2,'2016-5-14 11:00:00', 'カード10', 'カード10内容', 1);
INSERT into GRATITUDE_CARD values(11,6,1,'2015-5-12 11:00:00', 'カード11', 'カード11内容', 2);
INSERT into GRATITUDE_CARD values(12,4,3,'2016-5-18 11:00:00', 'カード12', 'カード12内容', 3);

INSERT into CASE_STUDY values(1,2016,4);

INSERT INTO GRATITUDE_CARD_CASE_STUDY  VALUES (2,1);
INSERT INTO GRATITUDE_CARD_CASE_STUDY  VALUES (5,1);
INSERT INTO GRATITUDE_CARD_CASE_STUDY  VALUES (6,1);