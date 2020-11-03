/* mangaテーブル */
CREATE TABLE IF NOT EXISTS manga_table (
	manga_id INT PRIMARY KEY,
    theme_id INT NOT NULL,
    frame_id1 INT NOT NULL,
    frame_id2 INT,
    frame_id3 INT,
    frame_id4 INT,
    status INT NOT NULL,
);

/* コマテーブル */
CREATE TABLE IF NOT EXISTS frame_table (
    frame_id INT PRIMARY KEY,
    creater VARCHAR(50),
    path VARCHAR(254) NOT NULL,
    create_date DATE NOT NULL
);

/* テーママスタ */
CREATE TABLE IF NOT EXISTS theme_table (
    theme_id INT PRIMARY KEY,
    theme_name VARCHAR(100),
);
