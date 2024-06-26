CREATE DATABASE DriverCradle
GO
USE DriverCradle
GO
create table Schools
(schoolNO int, --（分校编号），
schoolName nvarchar(100),--（分校名称），
leaderNO char(7),--（分校校长工号），
city nvarchar(10),--（所在城市），
addr nvarchar(100))--（分校地址）；

create table Employees
(empNO  char(7),--（职工工号），
empName nvarchar(10),--（职工姓名），
empGender char(1),--性别:F/M
schoolNO int, --（所在分校），
empType char(2),--（职工类别） JL,XZ
title char(2))--（职称）PT,GJ

create table Trainees
(trNO char(8),--（学员编号），
trName nvarchar(10),--（学员姓名），
trGender nchar(1),--（学员性别），男/女
regSchoolNO int, --（分校编号），
regDate datetime,--（注册日期），
finishDate datetime)--（获得驾照日期）

create table ExamRequests
(reqNO int ,--（申请记录号），
trNO char(8),--（学员编号），
reqDate datetime,--（申请日期），
examType nvarchar(2),--（考试类型），
favorDate datetime,--（期望考试日期），
realDate datetime,--（实际考试日期），
isPass bit,--（考试是否通过），
failReason nvarchar(100))--（不通过原因）；