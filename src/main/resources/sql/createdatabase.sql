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

alter table Captain comment '公交公司的所有队长';

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

alter table Car comment '公交公司的汽车';

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

alter table CarRoute comment '汽车与线路';

/*==============================================================*/
/* Table: Company                                               */
/*==============================================================*/
create table Company
(
   Cname                varchar(10) not null,
   Cdate                date,
   primary key (Cname)
);

alter table Company comment '公司名称
公司创建日期';

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

alter table Fleet comment '公司有若干车队';

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

alter table Staff comment '公交公司的所有员工，包括路队长和普通司机';

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

alter table StaffRoute comment '司机与线路';

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

alter table StopRoute comment '站点与线路对应表';

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

alter table TrafficViolation comment '违章情况，包括违章人工号，违章时间，事发地，车牌号，违章类型等';

/*==============================================================*/
/* Table: ViolationType                                         */
/*==============================================================*/
create table ViolationType
(
   Vtype                varchar(10) not null,
   punish               smallint,
   primary key (Vtype)
);

alter table ViolationType comment '所有违章类型';

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

