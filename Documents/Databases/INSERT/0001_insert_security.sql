
-- mecury1db1.sc_user_profile
insert into mecury1db1.sc_user_profile values (
        1, 'SUPERADMIN'
);
insert into mecury1db1.sc_user_profile values (
        2, 'ADMIN'
);
insert into mecury1db1.sc_user_profile values (
        3, 'USER'
);
commit;

select * from mecury1db1.sc_user_profile;


-- mecury1db1.sc_member
insert into mecury1db1.sc_member values (
        null, 'superadmin', '$2a$10$WfaHU6TZ36w5qX32XnwVn.costzGbni5xjZAn/gNUQXw/1NeZNPnG'
        ,'SUPERADMIN', 'Administrator', 'superadmin@xxxx.co.kr', 'Active', NOW(), NOW()
);
insert into mecury1db1.sc_member values (
        null, 'admin', '$2a$10$uE69EFGue7WV4rqYijEKs..xHDHuB2F1bNBvR.HOV6v2uoc9EMe0m'
        ,'ADMIN', 'Administrator', 'admin@xxxx.co.kr', 'Active', NOW(), NOW()
);
insert into mecury1db1.sc_member values (
        null, 'user', '$2a$10$voEFBcKwKO75Wn/E724y3eaR4WtHWzshsNouAiOqgkuNYDORMO3Oq'
        ,'GENERAL', 'USER1', 'user1@xxxx.co.kr', 'Active', NOW(), NOW()
);
commit;

select * from mecury1db1.sc_member;


-- mecury1db1.sc_member_user_profile
insert into mecury1db1.sc_member_user_profile values (
        1, 1
);
insert into mecury1db1.sc_member_user_profile values (
        2, 2
);
insert into mecury1db1.sc_member_user_profile values (
        3, 3
);

--insert into mecury1db1.sc_member_user_profile values (
--        1, 2
--);
--insert into mecury1db1.sc_member_user_profile values (
--        1, 3
--);
--insert into mecury1db1.sc_member_user_profile values (
--        4, 3
--);
commit;

select * from mecury1db1.sc_member_user_profile



