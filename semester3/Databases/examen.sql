IF OBJECT_ID('MedicalCenter', 'U') IS NOT NULL
	Drop table MedicalCenter
IF OBJECT_ID('Doctor', 'U') IS NOT NULL
	Drop table Doctor
IF OBJECT_ID('BloodType', 'U') IS NOT NULL
	Drop table BloodType
IF OBJECT_ID('Donor', 'U') IS NOT NULL
	Drop table Donor
SET DATEFORMAT dmy
IF OBJECT_ID('Donation', 'U') IS NOT NULL
	Drop table Donation
create table MedicalCenter (
	idMedical INT PRIMARY KEY IDENTITY(1,1),
	name varchar(100) Unique,
	address varchar(200))
create table Doctor (
	idDoctor INT PRIMARY KEY IDENTITY(1,1),
	name varchar(100),
	date_of_birth DATE,
	idMedical INT REFERENCES MedicalCenter(idMedical))
create table BloodType (
	idBlood INT PRIMARY KEY IDENTITY(1,1),
	name varchar(100) )
	
create table Donor (
	idDonor INT PRIMARY KEY IDENTITY(1,1),
	name varchar(100),
	date_of_birth DATE,
	idBlood INT REFERENCES BloodType(idBlood))
create table Donation (
	idDonor INT REFERENCES Donor(idDonor),
	idBlood INT REFERENCES BloodType(idBlood),
	date_of_donation DATE,
	idMedical INT REFERENCES  MedicalCenter(idMedical),
	idDoctor INT REFERENCES  Doctor(idDoctor),
	flag varchar(100),
	primary key (idDonor, idBlood)
)
insert into MedicalCenter(name,address) values ('Sfanta Maria', 'strada pitesti'), ('Spital', 'strada lalelelor') 

insert into Doctor (name, date_of_birth,idMedical) values ('maria', '21.09.2022',1), ('andrei', '21.10.2022',1), ('mara', '19.09.2022',2)
insert into BloodType(name) values ('A2'), ('B3'), ('0')
insert into Donor (name,date_of_birth,idBlood) values ('Donator1','21.09.2000',1), ('Donator2','13.09.2002',2), ('Donator3','19.12.1999',3)



insert into Donation(idDonor,idBlood,date_of_donation,idMedical,idDoctor,flag) values
(1, 1, '21.09.2021',1,1,'succesfull'),
(1, 2, '21.09.2022',1,1,'unsuccesfull'),
(2, 1, '14.10.2021',2,1,'succesfull'),
(2, 2, '20.03.2021',1,2,'succesfull')
select * from MedicalCenter
select * from Doctor
select * from BloodType
select * from Donor
select * from Donation

--2 procedure
go
create or alter proc uspAddDonation(@DonorName VARCHAR(100), 
@BloodName VARCHAR(100), @date_donation Date, @DoctorName VARCHAR(100),@MedicalName VARCHAR(100),@flag varchar(100) ) as
begin
	declare @idDonor INT = (select idDonor from Donor where name = @DonorName)
	declare @idBlood INT = (select idBlood from BloodType where name = @BloodName)
	declare @idDoctor INT = (select idDoctor from Doctor where name = @DoctorName)
	declare @idMedical INT = (select idMedical from MedicalCenter where name = @MedicalName)

	if @idDonor is not null and @idBlood is not null if @idDoctor is not null and @idMedical is not null
		if not exists (select * from Donation where idDonor = @idDonor and idBlood = @idBlood and idDoctor = @idDoctor and idMedical = @idMedical)
			insert into Donation (idDonor, idBlood, date_of_donation, idMedical,idDoctor,flag) values
			(@idDonor, @idBlood, @date_donation, @idMedical,@idDoctor,@flag)
		
	else 
		raiserror('The date of donation is less than 6 months apart', 12, 1)
end
go


exec uspAddDonation @DonorName = 'Marius', @BloodName = 'A2', @date_donation = '22.04.2019',  @DoctorName = 'Ana',@MedicalName='Spital Maria',@flag='succesfull'
--3 view
go
create view viewNamesOfMedicalWithMaximumSuccesfull AS
select M.name
from MedicalCenter M
where idMedical in (select idMedical
			from Donation D
			group by idMedical,flag
			having D.flag = (select max(D.flag) from Donation)
			)
go


DROP VIEW [viewNamesOfMedicalWithMaximumSuccesfull];

select * from viewNamesOfMedicalWithMaximumSuccesfull


--4 function
go
create function ufListDonors (@R INT) 
returns Table as return
	select name
	from Donor
	where idDonor in (
		select Don.idDonor
		from Donation Don
		group by Don.idDonor,Don.flag
		having count(*) > @R and don.flag='succesfull'
		)

go

select * from ufListDonors(1)
DROP FUNCTION  ufListDonors;