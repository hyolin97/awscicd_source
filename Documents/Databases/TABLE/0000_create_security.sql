/* spring security => */

-- for user_profile
-- DROP TABLE mecury1db1.sc_user_profile;
create table mecury1db1.sc_user_profile (
	id bigint unsigned not null auto_increment COMMENT '순차번호'
	,roletype varchar(30) not null COMMENT '접근유형'
	,primary key (id)
	,constraint uk_type unique(roletype)
	,index ix_sc_user_profile(roletype)
) ENGINE=InnoDB default CHARSET=utf8 COMMENT = '[TB] security 사용자 profile';
-- ALTER TABLE mecury1db1.user_profile COMMENT = '[TB] security 사용자 profile';
commit;

-- for user member
-- DROP TABLE mecury1db1.sc_member;
create table mecury1db1.sc_member (
	id bigint unsigned not null AUTO_INCREMENT  COMMENT '순차번호'
	,username varchar(30) not null  COMMENT '로그인 ID'
	,password varchar(100) not null COMMENT '패스워드'
	,first_name varchar(30) not null COMMENT '이름'
	,last_name varchar(30) not null COMMENT '성'
	,email varchar(30) not null COMMENT '이메일'
	,accountstate varchar(30) not null COMMENT '계정상태'
	,reg_date datetime not null COMMENT '등록일'
	,upp_date datetime not null COMMENT '수정일'
	,primary key (id, username)
	,constraint uk_sc_bm1 UNIQUE (username)
	,index ix_sc_member(username, email)
) ENGINE=InnoDB default CHARSET=utf8 COMMENT = '[TB] security 사용자 멤버';
commit;

-- for matching member and profile
-- DROP TABLE mecury1db1.sc_member_user_profile;
create table mecury1db1.sc_member_user_profile (
	user_id bigint unsigned not null COMMENT 'member id'
	,user_profile_id bigint unsigned not null COMMENT 'user_profile id'
	,primary key (user_id, user_profile_id)
	,index ix_sc_mem_user_profile(user_id, user_profile_id)
) ENGINE=InnoDB default CHARSET=utf8 COMMENT = '[TB] security 사용자 멤버/profile 매칭';
commit;

/* spring security <= */