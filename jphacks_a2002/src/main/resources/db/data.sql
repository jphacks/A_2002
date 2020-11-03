/* 開発用にデータ削除を追加 : リリース時は消す */
DELETE FROM manga_table;
DELETE FROM frame_table;
DELETE FROM theme_table;


INSERT INTO manga_table (manga_ID,theme_id,frame_id1,status)
VALUES(1,1,1,1);

INSERT INTO manga_table (manga_ID,theme_id,frame_id1,status)
VALUES(1,1,1,1);

INSERT INTO manga_table (manga_ID,theme_id,frame_id1,frame_id2,frame_id3,frame_id4,status)
VALUES(1,1,2,3,4,5,1);

INSERT INTO frame_table
VALUES(1,'hayakawa','/frame/1.png','2020-11-03');

INSERT INTO frame_table
VALUES(2,'nishida','/frame/2.png','2020-11-03');

INSERT INTO frame_table
VALUES(3,'tiba','/frame/3.png','2020-11-03');

INSERT INTO frame_table
VALUES(4,'uemura','/frame/4.png','2020-11-03');

INSERT INTO frame_table
VALUES(5,'nakata','/frame/5.png','2020-11-03');

INSERT INTO frame_table
VALUES(6,'nakata','/frame/6.png','2020-11-03');

INSERT INTO frame_table
VALUES(7,'nakata','/frame/7.png','2020-11-03');

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