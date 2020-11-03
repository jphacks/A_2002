/* 開発用にデータ削除を追加 : リリース時は消す */
DELETE FROM m_user;
DELETE FROM report;

/* ユーザマスタのデータ（ADMIN権限） PASS:aaaaaaaa */

INSERT INTO m_user (user_id, encrypted_password, user_name, stclass,stno, role, darkmode)
VALUES('s-ukeire@hcs.ac.jp', '$2a$10$YD3zTLahj/1mXk4WB/JS4OMvXP1nXymkGzTVMsJZtC9NT/4yIvoq.', '受入太郎','S3A1','01', 'STUDENT','FALSE');

INSERT INTO m_user (user_id, encrypted_password, user_name, stclass,stno, role, darkmode)
VALUES('satou@hcs.ac.jp', '$2a$10$YD3zTLahj/1mXk4WB/JS4OMvXP1nXymkGzTVMsJZtC9NT/4yIvoq.', '佐藤拓幸','S3A1','33', 'STUDENT','FALSE');

INSERT INTO m_user (user_id, encrypted_password, user_name, stclass,stno, role, darkmode)
VALUES('t-ukeire@hcs.ac.jp', '$2a$10$YD3zTLahj/1mXk4WB/JS4OMvXP1nXymkGzTVMsJZtC9NT/4yIvoq.', '受入先生','S3A1','', 'TEACHER','TRUE');

INSERT INTO m_user (user_id, encrypted_password, user_name, stclass,stno, role, darkmode)
VALUES('j-ukeire@hcs.ac.jp', '$2a$10$YD3zTLahj/1mXk4WB/JS4OMvXP1nXymkGzTVMsJZtC9NT/4yIvoq.', '受入事務','','', 'STUFF','TRUE');

/* 受験報告データ */
INSERT INTO report
VALUES(1,'S3A1', '34', 20183041,'佐藤拓幸','S','エスシ',111,'株式会社エスシーシー',1, '区分その他','2020-03-23','2020-03-28',
'2020-03-21',13,30,1,1,'ロケ区分アザー',1,'内容区分アザー','1','カテゴリーアザー',1,'true','1','1','approriateother','appropriate_sakubun',1,
1,'overviewother',1,'position',60,'GWは難しい',
'たくさん質問された',1,'2020-03-28','yamada@gmail.com','2020-03-28','yamada@gmail.com','内容が薄すぎます。');

/* 証明書データ */
INSERT INTO syomeisyo
VALUES(1,'S',3,'S3A1','藤井昌汰',20183041,'1999-04-21',
1,0,0,0,0,'',0,'0600053','藤井昌汰','札幌市中央区南3条東2丁目15-1',0,1,'2020-07-20','佐藤拓幸',
'2020-07-24','情報太郎');

/* 就職活動申請 */
INSERT INTO job_search_request
VALUES(1, 'S3A1','33', '佐藤拓幸','2020-10-28 15:25:07', '2020-10-29 15:25:07', '北海道', '1','SCC', '1', '2', null, null,
'2020-10-29 15:25:07', null, 'スケジュール', '内容',null);