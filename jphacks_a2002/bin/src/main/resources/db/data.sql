/* 開発用にデータ削除を追加 : リリース時は消す */
DELETE FROM manga_table;
DELETE FROM frame_table;
DELETE FROM theme_table;


INSERT INTO manga_table (manga_ID,theme_id,status)
VALUES(1,1,4);

INSERT INTO manga_table (manga_ID,theme_id,status)
VALUES(2,3,1);

INSERT INTO manga_table (manga_ID,theme_id,status)
VALUES(3,2,2);

INSERT INTO manga_table (manga_ID,theme_id,status)
VALUES(4,10,4);

INSERT INTO manga_table (manga_ID,theme_id,status)
VALUES(5,9,4);

INSERT INTO manga_table (manga_ID,theme_id,status)
VALUES(6,5,4);

INSERT INTO manga_table (manga_ID,theme_id,status)
VALUES(7,7,4);

INSERT INTO manga_table (manga_ID,theme_id,status)
VALUES(8,3,4);

INSERT INTO manga_table (manga_ID,theme_id,status)
VALUES(9,5,4);

INSERT INTO manga_table (manga_ID,theme_id,status)
VALUES(10,8,4);

INSERT INTO manga_table (manga_ID,theme_id,status)
VALUES(11,4,4);

INSERT INTO frame_table
VALUES(1,'hayakawa','img/frame/1.png','2020-11-03',1,1);

INSERT INTO frame_table
VALUES(2,'nishida','img/frame/2.png','2020-11-03',1,2);

INSERT INTO frame_table
VALUES(3,'tiba','img/frame/3.png','2020-11-03',1,3);

INSERT INTO frame_table
VALUES(4,'uemura','img/frame/4.png','2020-11-03',1,4);

INSERT INTO frame_table
VALUES(5,'nakata','img/frame/5.png','2020-11-03',2,1);

INSERT INTO frame_table
VALUES(6,'nakata','img/frame/6.png','2020-11-03',3,1);

INSERT INTO frame_table
VALUES(7,'nakata','img/frame/7.png','2020-11-03',3,2);

INSERT INTO frame_table
VALUES(8,'nakata','img/frame/8.png','2020-11-03',4,1);

INSERT INTO frame_table
VALUES(9,'nakata','img/frame/9.png','2020-11-03',4,2);

INSERT INTO frame_table
VALUES(10,'nakata','img/frame/10.png','2020-11-03',4,3);

INSERT INTO frame_table
VALUES(11,'nakata','img/frame/11.png','2020-11-03',4,4);

INSERT INTO frame_table
VALUES(12,'nakata','img/frame/12.png','2020-11-03',5,1);

INSERT INTO frame_table
VALUES(13,'nakata','img/frame/13.png','2020-11-03',5,2);

INSERT INTO frame_table
VALUES(14,'nakata','img/frame/14.png','2020-11-03',5,3);

INSERT INTO frame_table
VALUES(15,'nakata','img/frame/15.png','2020-11-03',5,4);

INSERT INTO frame_table
VALUES(16,'nakata','img/frame/16.png','2020-11-03',6,1);
INSERT INTO frame_table
VALUES(17,'nakata','img/frame/17.png','2020-11-03',6,2);

INSERT INTO frame_table
VALUES(18,'nakata','img/frame/18.png','2020-11-03',6,3);

INSERT INTO frame_table
VALUES(19,'nakata','img/frame/19.png','2020-11-03',6,4);

INSERT INTO frame_table
VALUES(20,'nakata','img/frame/20.png','2020-11-03',7,1);

INSERT INTO frame_table
VALUES(21,'nakata','img/frame/21.png','2020-11-03',7,2);
INSERT INTO frame_table
VALUES(22,'nakata','img/frame/22.png','2020-11-03',7,3);

INSERT INTO frame_table
VALUES(23,'nakata','img/frame/23.png','2020-11-03',7,4);

INSERT INTO frame_table
VALUES(24,'nakata','img/frame/24.png','2020-11-03',8,1);

INSERT INTO frame_table
VALUES(25,'nakata','img/frame/25.png','2020-11-03',8,2);

INSERT INTO frame_table
VALUES(26,'nakata','img/frame/26.png','2020-11-03',8,3);

INSERT INTO frame_table
VALUES(27,'nakata','img/frame/27.png','2020-11-03',8,4);

INSERT INTO frame_table
VALUES(28,'nakata','img/frame/28.png','2020-11-03',9,1);

INSERT INTO frame_table
VALUES(29,'nakata','img/frame/29.png','2020-11-03',9,2);

INSERT INTO frame_table
VALUES(30,'nakata','img/frame/30.png','2020-11-03',9,3);

INSERT INTO frame_table
VALUES(31,'nakata','img/frame/31.png','2020-11-03',9,4);

INSERT INTO frame_table
VALUES(32,'nakata','img/frame/32.png','2020-11-03',10,1);

INSERT INTO frame_table
VALUES(33,'nakata','img/frame/33.png','2020-11-03',10,2);

INSERT INTO frame_table
VALUES(34,'nakata','img/frame/34.png','2020-11-03',10,3);

INSERT INTO frame_table
VALUES(35,'nakata','img/frame/35.png','2020-11-03',10,4);

INSERT INTO frame_table
VALUES(36,'nakata','img/frame/36.png','2020-11-03',11,1);

INSERT INTO frame_table
VALUES(37,'nakata','img/frame/37.png','2020-11-03',11,2);

INSERT INTO frame_table
VALUES(38,'nakata','img/frame/38.png','2020-11-03',11,3);

INSERT INTO frame_table
VALUES(39,'nakata','img/frame/39.png','2020-11-03',11,4);

INSERT INTO theme_table
VALUES(1,'爆発');
INSERT INTO theme_table
VALUES(2,'猫');
INSERT INTO theme_table
VALUES(3,'パフォーマンス');
INSERT INTO theme_table
VALUES(4,'かつおぶし');
INSERT INTO theme_table
VALUES(5,'JPHACKS');
INSERT INTO theme_table
VALUES(6,'体操');
INSERT INTO theme_table
VALUES(7,'股間');
INSERT INTO theme_table
VALUES(8,'怪人');
INSERT INTO theme_table
VALUES(9,'神');
INSERT INTO theme_table
VALUES(10,'ユダヤ教');
