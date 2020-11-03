
/* 受験報告テーブル */
CREATE TABLE IF NOT EXISTS report (
	entrance_exam_report_id INT PRIMARY KEY,
    stclass VARCHAR(6) NOT NULL,
    stno VARCHAR(2) NOT NULL,
    gakuseki_no INT NOT NULL,
    user_name VARCHAR(50) NOT NULL,
    department_code VARCHAR(1) NOT NULL,
    kana VARCHAR(6) NOT NULL,
    entrance_exam_report_no INT,
    company_name VARCHAR(50) NOT NULL,
    kubun INT NOT NULL,
    kubun_other VARCHAR(50),
    requestday DATE NOT NULL,
    reportday DATE,
    entrance_exam_day DATE NOT NULL,
    entrance_exam_hour INT NOT NULL,
    entrance_exam_minute INT NOT NULL,
    entrance_exam_location VARCHAR(50) NOT NULL,
    entrance_exam_location_kubun INT NOT NULL,
    entrance_exam_location_kubun_other VARCHAR(50),
    entrance_exam_detail_kubun INT NOT NULL,
    entrance_exam_detail_kubun_other VARCHAR(50),
    entrance_exam_category VARCHAR(30) NOT NULL,
    entrance_exam_category_other VARCHAR(50),
    result_cnt INT,
    pass_only BOOLEAN,
    result_notification VARCHAR(20),
    appropriate VARCHAR(30),
    appropriate_other VARCHAR(50),
    appropriate_sakubun VARCHAR(50),
    interview_overview_style INT NOT NULL,
    group_cnt INT,
    interview_overview_style_other VARCHAR(50),
    interview_overview_count INT,
    interview_overview_position VARCHAR(50),
    interview_overview_time INT,
    gd_gw VARCHAR(50) ,
    question_detail VARCHAR(500) NOT NULL,
    status INT NOT NULL,
    entry_date DATE NOT NULL,
    entry_user_id VARCHAR(50) NOT NULL,
    update_day DATE,
    update_id VARCHAR(50),
    comment VARCHAR(500)
);

/* 証明書発行 */
CREATE TABLE IF NOT EXISTS syomeisyo (
    syomeisyo_id INT PRIMARY KEY,
    department VARCHAR(50),
    schoolyear INT,
    stclass VARCHAR(6),
    stname VARCHAR(50),
    stno INT,
    birthdate DATE,
    fullset INT,
    enrolled_cnt INT,
    record_cnt INT,
    graduation_cnt INT,
    health INT,
    other_name VARCHAR(50),
    other_cnt INT,
    zipcode VARCHAR(20),
    address_name VARCHAR(50),
    address VARCHAR(50),
    total_money INT,
    money_status INT,
    receipt_date DATE,
    receipt_name VARCHAR(50),
    issue_date DATE,
    issue_name VARCHAR(50)
);



/* ユーザマスタ */
CREATE TABLE IF NOT EXISTS m_user (
    user_id VARCHAR(50) PRIMARY KEY,
    encrypted_password VARCHAR(100),
    user_name VARCHAR(50),
    stclass VARCHAR(6),
    stno VARCHAR(2),
    role VARCHAR(50),
    darkmode boolean
);

/* 就職活動申請 */
CREATE TABLE IF NOT EXISTS job_search_request (
    job_search_id INT PRIMARY KEY,
    stclass VARCHAR(4) NOT NULL,
    stno VARCHAR(2) NOT NULL,
    stname VARCHAR(60)NOT NULL,
    date_to DATETIME NOT NULL,
    date_do DATETIME,
    location VARCHAR(254) NOT NULL,
    detail VARCHAR(1) NOT NULL,
    company_name VARCHAR(254) NOT NULL,
    forward boolean,
    category VARCHAR(1) NOT NULL,
    absence_date_to DATETIME,
    absence_date_do DATETIME,
    leave_early DATETIME,
    tardy_date DATETIME,
    schedule VARCHAR(254) NOT NULL,
    message VARCHAR(254),
    contents_report VARCHAR(254)
);
