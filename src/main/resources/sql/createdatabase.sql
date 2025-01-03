/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2024/12/6 2:28:33                            */
/*==============================================================*/


drop table if exists BusStop;

drop table if exists Captain;

drop table if exists Car;

drop table if exists CarRoute;

drop table if exists Company;

drop table if exists Fleet;

drop table if exists Relationship_11;

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
   Stopname             varchar(10) not null,
   primary key (Stopname)
);

/*==============================================================*/
/* Table: Captain                                               */
/*==============================================================*/
create table Captain
(
   Sno                  int not null,
   Fno                  int,
   Sname                varchar(10),
   Gender               varchar(1),
   Stel                 varchar(12),
   Sage                 int,
   primary key (Sno)
);

alter table Captain comment '������˾�����жӳ�';

/*==============================================================*/
/* Table: Car                                                   */
/*==============================================================*/
create table Car
(
   CarId                varchar(8) not null,
   Rno                  int,
   Seat                 smallint,
   primary key (CarId)
);

alter table Car comment '������˾������';

/*==============================================================*/
/* Table: CarRoute                                              */
/*==============================================================*/
create table CarRoute
(
   no                   int not null,
   CarId                varchar(8),
   Rno                  smallint,
   primary key (no)
);

alter table CarRoute comment '��������·';

/*==============================================================*/
/* Table: Company                                               */
/*==============================================================*/
create table Company
(
   Cname                varchar(10) not null,
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
   Com_Cname            varchar(10),
   Fname                varchar(10),
   Cname                varchar(10),
   primary key (Fno)
);

alter table Fleet comment '��˾�����ɳ���';

/*==============================================================*/
/* Table: Relationship_11                                       */
/*==============================================================*/
create table Relationship_11
(
   Stopname             varchar(10) not null,
   Rno                  int not null,
   primary key (Stopname, Rno)
);

/*==============================================================*/
/* Table: Route                                                 */
/*==============================================================*/
create table Route
(
   Rno                  int not null,
   Fno                  int,
   Rname                varchar(10),
   primary key (Rno)
);

/*==============================================================*/
/* Table: Staff                                                 */
/*==============================================================*/
create table Staff
(
   Sno                  varchar(11) not null,
   Rno                  int,
   Sname                varchar(10),
   Gender               varchar(1),
   Stel                 varchar(12),
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
   Sno                  varchar(11),
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
   Stopname             varchar(10),
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
   Car_CarId            varchar(8),
   Vio_Vtype            varchar(10) not null,
   Sta_Sno              varchar(11) not null,
   Stopname             varchar(10),
   Sno                  int,
   Vdate                date,
   Vtime                time,
   Location             varchar(10),
   CarId                varchar(10),
   Vtype                varchar(10),
   primary key (Vno)
);

alter table TrafficViolation comment 'Υ�����������Υ���˹��ţ�Υ��ʱ�䣬�·��أ����ƺţ�Υ�����͵�';

/*==============================================================*/
/* Table: ViolationType                                         */
/*==============================================================*/
create table ViolationType
(
   Vtype                varchar(10) not null,
   punish               smallint,
   primary key (Vtype)
);

alter table ViolationType comment '����Υ������';

alter table Captain add constraint FK_Reference_5 foreign key (Fno)
      references Fleet (Fno) on delete restrict on update restrict;

alter table Car add constraint FK_Relationship_10 foreign key (Rno)
      references Route (Rno) on delete restrict on update restrict;

alter table Fleet add constraint FK_Reference_1 foreign key (Sno)
      references Captain (Sno) on delete restrict on update restrict;

alter table Fleet add constraint FK_Relationship_12 foreign key (Com_Cname)
      references Company (Cname) on delete restrict on update restrict;

alter table Relationship_11 add constraint FK_Relationship_11 foreign key (Stopname)
      references BusStop (Stopname) on delete restrict on update restrict;

alter table Relationship_11 add constraint FK_Relationship_11 foreign key (Rno)
      references Route (Rno) on delete restrict on update restrict;

alter table Route add constraint FK_Reference_2 foreign key (Fno)
      references Fleet (Fno) on delete restrict on update restrict;

alter table Staff add constraint FK_Reference_8 foreign key (Rno)
      references Route (Rno) on delete restrict on update restrict;

alter table TrafficViolation add constraint FK_Reference_16 foreign key (Stopname)
      references BusStop (Stopname) on delete restrict on update restrict;

alter table TrafficViolation add constraint FK_Reference_6 foreign key (Sta_Sno)
      references Staff (Sno) on delete restrict on update restrict;

alter table TrafficViolation add constraint FK_Reference_7 foreign key (Vio_Vtype)
      references ViolationType (Vtype) on delete restrict on update restrict;

alter table TrafficViolation add constraint FK_Reference_9 foreign key (Car_CarId)
      references Car (CarId) on delete restrict on update restrict;

