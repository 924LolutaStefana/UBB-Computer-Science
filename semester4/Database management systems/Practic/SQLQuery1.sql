create table Cities (
	cityId int primary key,
	cityname varchar(100),
	country varchar(100)

	
	
)

create table Stores (
	storeid INT Primary key,
	storename varchar(100),
	nremployees int,
	cityId INT REFERENCES Cities(cityId),
	dateOfproduction Date,
	dateOfexpiration Date,
	
)



create table Clients (
	clientId INT Primary key,
	clientname varchar(100),
	
	dateOfBirth Date,
	dateOfRegistration Date,

	country varchar(100),
	storeId INT REFERENCES Stores(storeId)
)


create table Products (
	productId INT Primary key,
	productname varchar(100),
	
	price int,

	storeId INT REFERENCES Stores(storeId)
)
insert into Cities (cityId,cityname,country)
values 
(1, 'Cluj', 'Romania'),
(2, 'Timisoara', 'Romania')
select * from Cities

insert into Stores (storeid, storename, nremployees, cityId, dateOfproduction,dateOfexpiration)
values 
(1,'Sephora', 10, 1,CAST(N'2023-03-03 10:10:10' AS DATETIME),CAST(N'2026-03-03 10:10:10' AS DATETIME)),
(2,'Lidl', 100, 2,CAST(N'2023-03-03 10:10:10' AS DATETIME),CAST(N'2025-03-03 10:10:10' AS DATETIME)),
(3,'Kaufland', 200, 1,CAST(N'2022-03-03 10:10:10' AS DATETIME),CAST(N'2024-03-03 10:10:10' AS DATETIME))


select * from Stores


insert into Clients (clientId, clientname,dateOfBirth, dateOfRegistration, country,storeId)
values 
(1,'Ana',CAST(N'2002-03-03 10:10:10' AS DATETIME),CAST(N'2022-03-03 10:10:10' AS DATETIME),'Romania',1),
(2,'Maria',CAST(N'1999-03-04 10:10:10' AS DATETIME),CAST(N'2021-04-03 10:10:10' AS DATETIME),'Romania',2),
(3,'Andrei',CAST(N'2000-03-03 10:10:10' AS DATETIME),CAST(N'2023-05-03 10:10:10' AS DATETIME),'Romania',1)

select * from Clients


