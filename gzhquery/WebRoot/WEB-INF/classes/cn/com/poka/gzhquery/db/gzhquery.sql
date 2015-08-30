-- Create table
create table BANKINFO
(
  BANKNO   VARCHAR2(20) not null,
  BANKNAME VARCHAR2(120) not null,
  IPADDR   VARCHAR2(20),
  IPPORT   VARCHAR2(8),
  ADDRESS  VARCHAR2(200),
  TELPHONE VARCHAR2(20),
  REMARK   VARCHAR2(512)
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table BRANCHINFO
(
  AGENCYNO   VARCHAR2(20) not null,
  BRANCHNAME VARCHAR2(120) not null,
  ADDRESS    VARCHAR2(200),
  TELPHONE   VARCHAR2(20),
  REMARK     VARCHAR2(512),
  BANKNO     VARCHAR2(20) not null
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table BRANCHINFO
  add constraint PK_BRANCHINFO primary key (BANKNO, AGENCYNO)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



-- Create table
create table BUNDLEINFO
(
  BUNDLEID       VARCHAR2(24),
  PERCODE        VARCHAR2(24),
  SCANTIME       DATE,
  CREATETIME     DATE,
  SEQID          NUMBER,
  OPERATORID     VARCHAR2(20),
  CHECKER        VARCHAR2(20),
  ADDMONOPERATOR VARCHAR2(20),
  ADDMONCHECKER  VARCHAR2(20),
  ADDMONTIME     DATE,
  FLAG           CHAR(1)
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );



-- Create table
create table CHECKMON
(
  CHECKMONID  NUMBER not null,
  MONHEAD     VARCHAR2(10) not null,
  STARTMON    VARCHAR2(20) not null,
  ENDMON      VARCHAR2(20) not null,
  CHECKSTART  DATE not null,
  CHECKEND    DATE not null,
  CHECKSTATUS CHAR(1)
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



-- Create table
create table GETFILEINFO
(
  BANKNO      VARCHAR2(20) not null,
  GETFILETIME DATE not null,
  GETFILENAME VARCHAR2(120) not null,
  STARTTIME   DATE,
  ENDTIME     DATE,
  FILESIZE    VARCHAR2(20),
  GETSTATUS   CHAR(1),
  INSTATUS    CHAR(1),
  SEQID       NUMBER not null
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table GETFILEINFO
  add constraint PK_GETFILEINFO primary key (SEQID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



-- Create table
create table MONBOXADDMON
(
  SCANID     VARCHAR2(24) not null,
  FLAG       VARCHAR2(1),
  MONBOXID   VARCHAR2(11),
  REMARK     VARCHAR2(512),
  OPERTIME   DATE,
  OPERATORID VARCHAR2(20),
  CHECKER    VARCHAR2(20)
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table MONCODEQUERY
(
  PERCODE      VARCHAR2(24),
  COLTIME      DATE,
  MON          VARCHAR2(20),
  MONVAL       VARCHAR2(3),
  MONVER       VARCHAR2(4),
  OPERID       VARCHAR2(20),
  OPERDATE     DATE,
  BUNDLEID     VARCHAR2(24),
  BUSINESSTYPE VARCHAR2(4),
  BANKNO       VARCHAR2(20),
  BANKNAME     VARCHAR2(120),
  NETNO        VARCHAR2(20),
  NETNAME      VARCHAR2(120),
  PERTYPE      VARCHAR2(20),
  DES1         VARCHAR2(24),
  DES2         VARCHAR2(24),
  DES3         VARCHAR2(24),
  FLAG         NUMBER
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table MONEYDATA
(
  PERCODE        VARCHAR2(24),
  COLTIME        DATE,
  MON            VARCHAR2(20),
  MONTYPE        CHAR(3),
  MONVAL         CHAR(3),
  MONVER         CHAR(4),
  TRUEFLAG       CHAR(1),
  QUANLITY       CHAR(2),
  IMAGESNO       BLOB,
  OPERATORID     VARCHAR2(20),
  OPERDATE       DATE,
  BUNDLEID       VARCHAR2(24),
  CHECKER        VARCHAR2(20),
  IMAGECHAR      VARCHAR2(4000),
  BUSINESSTYPE   VARCHAR2(4),
  MONBOXID       VARCHAR2(24),
  ATMID          VARCHAR2(24),
  ADDMONOPERATOR VARCHAR2(20),
  ADDMONCHECKER  VARCHAR2(20),
  TESTIMAGECHAR  CLOB
)
partition by range (COLTIME)
(
  partition PART1 values less than (TO_DATE(' 2013-06-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace RMBSYS
    pctfree 10
    initrans 1
    maxtrans 255
    storage
    (
      initial 64K
      next 1M
      minextents 1
      maxextents unlimited
    ),
  partition SYS_P25 values less than (TO_DATE(' 2013-07-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace RMBSYS
    pctfree 10
    initrans 1
    maxtrans 255
    storage
    (
      initial 64K
      next 1M
      minextents 1
      maxextents unlimited
    ),
  partition SYS_P41 values less than (TO_DATE(' 2013-08-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace RMBSYS
    pctfree 10
    initrans 1
    maxtrans 255
    storage
    (
      initial 64K
      next 1M
      minextents 1
      maxextents unlimited
    ),
  partition SYS_P81 values less than (TO_DATE(' 2013-09-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace RMBSYS
    pctfree 10
    initrans 1
    maxtrans 255
    storage
    (
      initial 64K
      next 1M
      minextents 1
      maxextents unlimited
    ),
  partition SYS_P121 values less than (TO_DATE(' 2013-10-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace RMBSYS
    pctfree 10
    initrans 1
    maxtrans 255
    storage
    (
      initial 64K
      next 1M
      minextents 1
      maxextents unlimited
    ),
  partition SYS_P61 values less than (TO_DATE(' 3993-04-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN'))
    tablespace RMBSYS
    pctfree 10
    initrans 1
    maxtrans 255
    storage
    (
      initial 64K
      next 1M
      minextents 1
      maxextents unlimited
    )
);
-- Create/Recreate indexes 
create index MONEYDATA_MON_IDX on MONEYDATA (MON)
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table MONRULE
(
  MONCODE   VARCHAR2(22) not null,
  MONHEAD   VARCHAR2(12) not null,
  MONTYPE   VARCHAR2(20),
  MONYEAR   VARCHAR2(8),
  MONSTATUS VARCHAR2(1)
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table MONRULE
  add constraint PK_MONRULE primary key (MONCODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



-- Create table
create table PACKAGEINFO
(
  PACKAGEID  VARCHAR2(24) not null,
  BUNDLEID   VARCHAR2(24) not null,
  REMARK     VARCHAR2(512),
  OPERATORID VARCHAR2(20),
  OPERTIME   DATE,
  CHECKER    VARCHAR2(20)
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table PERINFO
(
  PERCODE  VARCHAR2(24) not null,
  PERTYPE  CHAR(2) not null,
  BANKNO   VARCHAR2(20) not null,
  AGENCYNO VARCHAR2(12) not null,
  ACCTADDR VARCHAR2(120),
  STATUS   CHAR(1)
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table PERINFO
  add constraint PK_PERINFO primary key (PERCODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table QUERYMONDATALOG
(
  USERCODE  VARCHAR2(20) not null,
  CACCOUNT  VARCHAR2(32),
  QUERYTIME DATE not null,
  MON       VARCHAR2(20) not null,
  QTYPE     VARCHAR2(2),
  MEMO      VARCHAR2(512),
  SEQID     NUMBER not null
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table QUERYTYPE
(
  QTYPE    CHAR(2) not null,
  QUERYTXT VARCHAR2(255) not null
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table SMARTINFO
(
  SMARTCODE  VARCHAR2(22) not null,
  BANKNO     VARCHAR2(20) not null,
  AGENCYNO   VARCHAR2(20) not null,
  ACCTNAME   VARCHAR2(20),
  ACCTADDR   VARCHAR2(200),
  MNOFILE    VARCHAR2(255),
  DATATRAN   CHAR(2),
  UPDATETIME DATE,
  STATUS     CHAR(1)
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table SMARTINFO
  add constraint PK_SMARTINFO primary key (SMARTCODE)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table SMODULE
(
  MODULEID       NUMBER,
  MODULENAME     VARCHAR2(50),
  PARENTMODULEID NUMBER
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table SUSERMODULEPERMISSION
(
  USERID     VARCHAR2(10),
  MODULEID   NUMBER,
  PERMISSION NUMBER
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );



-- Create table
create table TRANSCODEINFO
(
  TRANSCODE CHAR(3) not null,
  TRANSNAME VARCHAR2(64) not null,
  TRANSMEMO VARCHAR2(255)
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table TRANSLIST
(
  TRANSID     VARCHAR2(20) not null,
  TRANSCODE   VARCHAR2(3),
  SUBDATE     VARCHAR2(10) not null,
  SUBTIME     VARCHAR2(30) not null,
  BANKNO      VARCHAR2(20),
  PERCODESUM  NUMBER,
  GETFILETIME DATE,
  RUNTYPE     CHAR(1),
  PSTAUS      CHAR(2)
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table TRANSLOGS
(
  SEQID       NUMBER not null,
  TRANSCODE   CHAR(3) not null,
  SUBDATE     DATE not null,
  BANKNO      VARCHAR2(20),
  PERCODESUM  NUMBER,
  GETFILETIME DATE,
  RUNTYPE     CHAR(1),
  PSTATUS     VARCHAR2(2)
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
-- Create/Recreate primary, unique and foreign key constraints 
alter table TRANSLOGS
  add constraint PK_TRANSLOGS primary key (SEQID)
  using index 
  tablespace SYSTEM
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table USERINFO
(
  USERCODE     VARCHAR2(20) not null,
  AGENCYNO     VARCHAR2(20),
  USERNAME     VARCHAR2(20) not null,
  USERPASSWORD VARCHAR2(30) not null,
  USERTYPE     CHAR(1),
  USERSTATUS   CHAR(1),
  USERTEL      VARCHAR2(20),
  USERADDRESS  VARCHAR2(200)
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table USEROPERLOG
(
  USERCODE VARCHAR2(20) not null,
  USERNAME VARCHAR2(20),
  OPERTIME NVARCHAR2(20) not null,
  ACTION   VARCHAR2(200) not null
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );


-- Create table
create table WITHDRAWINFO
(
  SCANID       VARCHAR2(30) not null,
  FLAGE        VARCHAR2(2) not null,
  REMARK       VARCHAR2(512),
  OPERTIME     DATE,
  BUSINESSTYPE VARCHAR2(4),
  OPERATORID   VARCHAR2(20),
  CHECKER      VARCHAR2(20)
)
tablespace RMBSYS
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 8K
    minextents 1
    maxextents unlimited
  );


