

-- Work on 3 tables of the form Ta(aid, a2, …), Tb(bid, b2, …), Tc(cid, aid, bid, …), where:

--aid, bid, cid, a2, b2 are integers;
--the primary keys are underlined;
--a2 is UNIQUE in Ta;
--aid and bid are foreign keys in Tc, referencing the primary keys in Ta and Tb, respectively.
CREATE TABLE Ta (
	aid INT PRIMARY KEY,
	a2 INT UNIQUE,
	aux INT
	
)


CREATE TABLE Tb (
	bid INT PRIMARY KEY,
	b2 INT
)

CREATE TABLE Tc (
	cid INT PRIMARY KEY,
	aid INT FOREIGN KEY REFERENCES Ta(aid),
	bid INT FOREIGN KEY REFERENCES Tb(bid)
)

DROP TABLE Tc
DROP TABLE Tb
DROP TABLE Ta

--Procedures to generate and add data into the tables Ta,Tb,Tc

--into Ta
GO
CREATE OR ALTER PROCEDURE insertIntoTa(@rows INT) AS
BEGIN
	DECLARE @aid INT
	DECLARE @a2 INT
	DECLARE @aux INT

	SET @aid  = (SELECT MAX(aid) + 1 FROM Ta)
	if @aid is NULL
		SET @aid = 1
	SET @a2 = (SELECT MAX(a2) + 1 FROM Ta)
	if @a2 is NULL
		SET @a2 = 1
	SET @aux = (SELECT MAX(aux) + 1 FROM Ta)
	if @aux is NULL
		SET @aux = 1
	WHILE @rows > 0
	BEGIN
		
		INSERT INTO Ta VALUES (@aid, @a2,@aux)
		SET @rows = @rows- 1
		SET @aid = @aid + 1
		SET @a2 = @a2 + 1
		SET @aux = @aux + 1
	END
END

--into Tb
GO
CREATE OR ALTER PROCEDURE insertIntoTb(@rows INT) AS
BEGIN
	DECLARE @bid INT
	DECLARE @b2 INT
	SET @bid  = (SELECT MAX(bid) + 1 FROM Tb)
	if @bid is NULL
		SET @bid = 1
	SET @b2 = (SELECT MAX(b2) + 1 FROM Tb)
	if @b2 is NULL
		SET @b2 = 1
	WHILE @rows > 0 
	BEGIN
		INSERT INTO Tb VALUES(@bid, @b2)
		SET @rows = @rows - 1
		SET @bid = @bid + 1
		SET @b2 = @b2 + 1
	END
END

--  into Tc
GO
CREATE OR ALTER PROCEDURE insertIntoTc(@rows INT) AS
BEGIN
	DECLARE @aid INT
	DECLARE @bid INT
	WHILE @rows > 0
	BEGIN
		SET @aid = (SELECT TOP 1 aid FROM Ta ORDER BY NEWID())
		SET @bid = (SELECT TOP 1 bid FROM Tb ORDER BY NEWID())
		INSERT INTO Tc VALUES(@rows, @aid, @bid)
		SET @rows = @rows - 1
	END
END

EXEC insertIntoTa 300
SELECT * FROM Ta

EXEC insertIntoTb 300
SELECT * FROM Tb

EXEC insertIntoTc 300
SELECT * FROM Tc

---a. Write queries on Ta such that their execution plans contain the following operators:

--clustered index scan;
--- We have a clustered index automatically created for the aid column from Ta

SELECT *
FROM Ta    ---cost:0,003612
--clustered index seek;
SELECT *
FROM Ta
WHERE aid < 100   ---cost:0.0033909

--nonclustered index scan;
-- We have a nonclustered index automatically created for the a2 column from Ta
SELECT a2
FROM Ta  ---cost:0.003612

--nonclustered index seek;
SELECT a2
FROM Ta
WHERE a2 >100--- cost:0.003502
--key lookup.
SELECT  a2, aux
FROM Ta
WHERE a2 = 200---cost:0.0000042


--b. Write a query on table Tb with a WHERE clause of the form WHERE b2 = value and analyze its execution plan.
--Create a nonclustered index that can speed up the query. Examine the execution plan again.
SELECT *
FROM Tb
WHERE b2 = 120---cost without nonclustered index:0.003612

DROP INDEX b2_index ON Tb
CREATE NONCLUSTERED INDEX b2_index ON Tb(b2)--cost:0.0103

-- c) Create a view that joins at least 2 tables. Check whether existing indexes are helpful; if not, reassess existing indexes / examine the cardinality of the tables.

GO
CREATE OR ALTER VIEW View1 AS
	SELECT A.aid, B.bid, C.cid
	FROM Tc C
	INNER JOIN Ta A ON A.aid = C.aid
	INNER JOIN Tb B ON B.bid = C.bid
	WHERE B.b2 > 100

GO
SELECT *
FROM View1---cost:0.0237926

DROP INDEX Tc_index ON Tc
CREATE NONCLUSTERED INDEX Tc_index ON Tc(aid, bid)---cost:0.0103
