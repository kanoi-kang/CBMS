drop table if exists BusStop;

drop table if exists Captain;

drop table if exists Car;

drop table if exists Company;

drop table if exists Fleet;

drop table if exists Route;

drop table if exists Staff;

drop table if exists StaffRoute;

drop table if exists StopRoute;

drop table if exists TrafficViolation;

drop table if exists ViolationType;

/*==============================================================*/
/* Table: BusStop                                               */
/*==============================================================*/
create table BusStop
(
   Stopname             char(10) not null,
   primary key (Stopname)
);

/*==============================================================*/
/* Table: Captain                                               */
/*==============================================================*/
create table Captain
(
   Sno                  int not null,
   Fno                  int,
   Sname                char(10),
   Gender               char(1),
   Stel                 char(12),
   Sage                 int,
   primary key (Sno)
);

alter table Captain comment '������˾�����жӳ�';

/*==============================================================*/
/* Table: Car                                                   */
/*==============================================================*/
create table Car
(
   CarId                char(8) not null,
   Rno                  int,
   Seat                 smallint,
   Ctype                varchar(3),
   Cage                 Int,
   primary key (CarId)
);

alter table Car comment '������˾������';

/*==============================================================*/
/* Table: Company                                               */
/*==============================================================*/
create table Company
(
   Cname                char(10) not null,
   Cdate                date,
   primary key (Cname)
);

alter table Company comment '��˾����
��˾��������';

/*==============================================================*/
/* Table: Fleet                                                 */
/*==============================================================*/
create table Fleet
(
   Fno                  int not null,
   Sno                  int,
   Com_Cname            char(10),
   Fname                char(10),
   Cname                char(10),
   primary key (Fno)
);

alter table Fleet comment '��˾�����ɳ���';

/*==============================================================*/
/* Table: Route                                                 */
/*==============================================================*/
create table Route
(
   Rno                  int not null,
   Fno                  int,
   Rname                char(10),
   primary key (Rno)
);

/*==============================================================*/
/* Table: Staff                                                 */
/*==============================================================*/
create table Staff
(
   Sno                  char(11) not null,
   Rno                  int,
   Sname                char(10),
   Gender               char(1),
   Stel                 char(12),
   Sage                 int,
   primary key (Sno)
);

alter table Staff comment '������˾������Ա��������·�ӳ�����ͨ˾��';

/*==============================================================*/
/* Table: StaffRoute                                            */
/*==============================================================*/
create table StaffRoute
(
   no                   int not null,
   Sno                  char(11),
   Rno                  smallint,
   primary key (no)
);

alter table StaffRoute comment '˾������·';

/*==============================================================*/
/* Table: StopRoute                                             */
/*==============================================================*/
create table StopRoute
(
   no                   int not null,
   Stopname             char(10),
   Rno                  smallint,
   primary key (no)
);

alter table StopRoute comment 'վ������·��Ӧ��';

/*==============================================================*/
/* Table: TrafficViolation                                      */
/*==============================================================*/
create table TrafficViolation
(
   Vno                  int not null,
   Car_CarId            char(8),
   Vio_Vtype            char(10) not null,
   Sta_Sno              char(11) not null,
   Stopname             char(10),
   Vdate                date,
   Vtime                time,
   Location             char(256),
   CarId                char(10),
   primary key (Vno)
);

alter table TrafficViolation comment 'Υ�����������Υ���˹��ţ�Υ��ʱ�䣬�·��أ����ƺţ�Υ�����͵�';

/*==============================================================*/
/* Table: ViolationType                                         */
/*==============================================================*/
create table ViolationType
(
   Vtype                char(10) not null,
   punish               smallint,
   primary key (Vtype)
);

alter table ViolationType comment '����Υ������';

alter table Captain add constraint FK_Reference_5 foreign key (Fno)
      references Fleet (Fno) on delete restrict on update restrict;

alter table Car add constraint FK_Relationship_10 foreign key (Rno)
      references Route (Rno) on delete restrict on update restrict;

alter table Fleet add constraint FK_Reference_5 foreign key (Sno)
      references Captain (Sno) on delete restrict on update restrict;

alter table Fleet add constraint FK_Relationship_12 foreign key (Com_Cname)
      references Company (Cname) on delete restrict on update restrict;

alter table Route add constraint FK_Reference_2 foreign key (Fno)
      references Fleet (Fno) on delete restrict on update restrict;

alter table Staff
add constraint FK_Reference_8 foreign key (Rno) references route (Rno) on delete RESTRICT on update RESTRICT,
add constraint CHK_Gender check (Gender IN ('��', 'Ů'));

alter table TrafficViolation add constraint FK_Reference_16 foreign key (Stopname)
      references BusStop (Stopname) on delete restrict on update restrict;

alter table TrafficViolation add constraint FK_Reference_6 foreign key (Sta_Sno)
      references Staff (Sno) on delete restrict on update restrict;

alter table TrafficViolation add constraint FK_Reference_7 foreign key (Vio_Vtype)
      references ViolationType (Vtype) on delete restrict on update restrict;

alter table TrafficViolation add constraint FK_Reference_9 foreign key (Car_CarId)
      references Car (CarId) on delete restrict on update restrict;
