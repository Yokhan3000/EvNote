insert into user ( id,login , password ) values ( '1','Misha','123');
insert into user ( id,login , password ) values ( '2','Petr','222');
insert into user ( id,login , password ) values ( '3','Vasya','222');

insert into notepad ( id,login , password ) values ( '2','test','222');

insert into NOTEPAD(id, title,updatetime, user_id)
values ('1', 'mishasNotepad', '2017-12-01','1');
insert into NOTEPAD(id, title,updatetime, user_id)
values ('2', 'mishasNotepad2', '2017-10-01','1');
insert into NOTEPAD(id, title,updatetime, user_id)
values ('3', 'PetrsNotepad', '2017-07-02','2');