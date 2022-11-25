CREATE TABLE Employee(
	employee_id INT,
	employee_name VARCHAR(100),
	month_of_birth INT,
	salary INT,
	CONSTRAINT PrimaryKeyEmployee PRIMARY KEY(employee_id),
	manager_id INT
	
);

CREATE TABLE Departament(
	departament_id INT PRIMARY KEY IDENTITY(1,1),
	departament_name VARCHAR(100),
	
	
);
CREATE TABLE Manager(
	manager_id INT PRIMARY KEY IDENTITY(1,1),
	manager_name VARCHAR(100),
	year_of_birth INT,
	departament_id INT FOREIGN KEY REFERENCES Departament(departament_id)  ON DELETE CASCADE ON UPDATE CASCADE,
	
);

CREATE TABLE Works_at(
	employee_id INT FOREIGN KEY REFERENCES Employee(employee_id) ON DELETE CASCADE ON UPDATE CASCADE,
	departament_id  INT FOREIGN KEY REFERENCES Departament(departament_id) ON DELETE CASCADE ON UPDATE CASCADE,
	UNIQUE(employee_id,departament_id )
	);

CREATE TABLE Brand(
	brand_id INT PRIMARY KEY IDENTITY(1,1),
	brand_name VARCHAR(100)
	
);
CREATE TABLE Product(
	product_id INT PRIMARY KEY IDENTITY(1,1),
	product_name VARCHAR(100),
	brand_id INT FOREIGN KEY REFERENCES Brand(brand_id)  ON DELETE CASCADE ON UPDATE CASCADE,
	
);
CREATE TABLE Usage(
	usage_id INT PRIMARY KEY IDENTITY(1,1),
	usage_name VARCHAR(100)
);
CREATE TABLE Used_for(
	product_id INT FOREIGN KEY REFERENCES Product(product_id) ON DELETE CASCADE ON UPDATE CASCADE,
	usage_id  INT FOREIGN KEY REFERENCES Usage(usage_id)  ON DELETE CASCADE ON UPDATE CASCADE,
	
	UNIQUE(product_id,usage_id )
	);
CREATE TABLE Location(
	location_id INT PRIMARY KEY IDENTITY(1,1),
	location_name VARCHAR(100),
	
	
);
CREATE TABLE Street(
	street_id INT PRIMARY KEY IDENTITY(1,1),
	street_name VARCHAR(100),
	location_id INT FOREIGN KEY REFERENCES Location(location_id)  ON DELETE CASCADE ON UPDATE CASCADE,
	number INT
);
----------------
DELETE FROM Employee
USE laborator2;
-- modify the type of a column

GO

CREATE OR ALTER PROCEDURE setEmployeeSalaryToVarChar 
AS
    ALTER TABLE Employee ALTER COLUMN salary  VARCHAR(10)

GO
--reverse:
CREATE OR ALTER PROCEDURE  setEmployeeSalaryToInt
AS
	ALTER TABLE Employee ALTER COLUMN salary INT


-- add  a column
GO
CREATE OR ALTER PROCEDURE addRatingForProduct
AS
	ALTER TABLE Product ADD product_rating INT

--reverse:
GO
CREATE OR ALTER PROCEDURE dropRatingForProduct 
AS
	ALTER TABLE Product DROP COLUMN product_rating 


-- add a DEFAULT constraint
GO
CREATE OR ALTER PROCEDURE addDefaultContraintToStreet
AS
	ALTER TABLE Street ADD CONSTRAINT default_number DEFAULT(0) FOR number

--reverse:
GO
CREATE OR ALTER PROCEDURE dropDefaultContraintToStreet
AS
	ALTER TABLE Street DROP CONSTRAINT default_number



-- add a primary key

GO
CREATE OR ALTER PROCEDURE addIdAndNameToCostumerAsPK
AS
	ALTER TABLE LoyalCostumer
		DROP Constraint  PK_costumer
	ALTER TABLE LoyalCostumer
		ADD CONSTRAINT PK_costumer PRIMARY KEY(costumer_id, costumer_name)
--reverse
GO 
CREATE OR ALTER PROCEDURE dromIdAndNameToCostumerAsPK
AS
	ALTER TABLE LoyalCostumer
		DROP CONSTRAINT  PK_costumer
	ALTER TABLE LoyalCostumer
		ADD CONSTRAINT PK_costumer PRIMARY KEY(costumer_id)


-- add a candidate key
GO
CREATE OR ALTER PROCEDURE candidateKeyProduct 
AS	
	ALTER TABLE Product
		ADD CONSTRAINT Product_Candidate_key UNIQUE(product_id,brand_id)
--reverse
GO
CREATE OR ALTER PROCEDURE dropCandidateKeyProduct 
AS
	ALTER TABLE Product
		DROP CONSTRAINT Product_Candidate_key


-- add / remove a foreign key
GO
CREATE OR ALTER PROCEDURE foreignKeyEmployee 
AS
	ALTER TABLE Employee
		ADD CONSTRAINT Fk_Employee FOREIGN KEY(manager_id) REFERENCES Manager(manager_id)

GO
CREATE OR ALTER PROCEDURE dropForeignKeyEmployee
AS
	ALTER TABLE Employee
		DROP CONSTRAINT Fk_Employee


-- create a table
GO
CREATE OR ALTER PROCEDURE addTableLoyalCostumer
AS
	CREATE TABLE LoyalCostumer (
		costumer_id INT,
		costumer_name VARCHAR(100) NOT NULL,
		years_of_loyalty INT,
		date_of_birth VARCHAR(100),
		CONSTRAINT PK_costumer PRIMARY KEY(costumer_id)
	);
--reverse:
GO 
CREATE OR ALTER PROCEDURE dropLoyalCostumer
AS
	DROP TABLE LoyalCostumer

--Create a new table that holds the current version of the database schema. 
--Simplifying assumption: the version is an integer number.

CREATE TABLE CurrentVersion  (
	current_version INT
)

INSERT INTO CurrentVersion(current_version) VALUES(1) ;


-- a table that contains the initial version, the version after the execution of the procedure and the procedure that changes the table in this way
CREATE TABLE TableProcedure (
	initial_version INT,
	final_version INT,
	procedure_name VARCHAR(MAX),
	PRIMARY KEY (initial_version, final_version)
)
DROP TABLE TableProcedure


INSERT INTO TableProcedure VALUES (1, 2, 'setEmployeeSalaryToVarChar');
INSERT INTO TableProcedure VALUES   (2, 1, 'setEmployeeSalaryToInt');
INSERT INTO TableProcedure VALUES	(2, 3, 'addRatingForProduct');
INSERT INTO TableProcedure VALUES	(3, 2, 'dropRatingForProduct');
INSERT INTO TableProcedure VALUES	(3, 4, 'addDefaultContraintToStreet');
INSERT INTO TableProcedure VALUES	(4, 3, 'dropDefaultContraintToStreet');
INSERT INTO TableProcedure VALUES	(4, 5, 'addTableLoyalCostumer');
INSERT INTO TableProcedure VALUES	(5, 4, 'dropLoyalCostumer');
INSERT INTO TableProcedure VALUES	(5, 6, 'addIdAndNameToCostumerAsPK');
INSERT INTO TableProcedure VALUES	(6, 5, 'dropIdAndNameToCostumerAsPK');
INSERT INTO TableProcedure VALUES	(6, 7, 'candidateKeyProduct');
INSERT INTO TableProcedure VALUES	(7, 6, 'dropCandidateKeyProduct');
INSERT INTO TableProcedure VALUES	(7, 8, 'foreignKeyEmployee');
INSERT INTO TableProcedure VALUES	(8, 7, 'dropForeignKeyEmployee');
DELETE FROM TableProcedure

-- procedure to bring the database to the specified version
GO
CREATE OR ALTER PROCEDURE goToVersion(@newCurrentVersion INT) 
AS
	DECLARE @current_version INT
	DECLARE @procedureName VARCHAR(MAX)
	SELECT @current_version = current_version FROM CurrentVersion

	IF (@newCurrentVersion > (SELECT MAX(final_version) FROM TableProcedure) OR @newCurrentVersion < 1)
		RAISERROR ('The version should be smaller than 9 and bigger than 0', 10, 1)
	ELSE
	BEGIN
		IF @newCurrentVersion = @current_version
			PRINT('This is the version your currently on..');
		ELSE
		BEGIN
			IF @current_version > @newCurrentVersion
			BEGIN
				WHILE @current_version > @newCurrentVersion
					BEGIN
						SELECT @procedureName = procedure_name FROM TableProcedure WHERE initial_version = @current_version AND final_version = @current_version-1
						PRINT('Executing ' + @procedureName);
						EXEC (@procedureName)
						SET @current_version = @current_version - 1
					END
			END

			IF @current_version < @newCurrentVersion
			BEGIN
				WHILE @current_version < @newCurrentVersion
					BEGIN
						SELECT @procedureName = procedure_name FROM TableProcedure WHERE initial_version = @current_version AND final_version = @current_version+1
						PRINT('Executing ' + @procedureName);
						EXEC (@procedureName)
						SET @current_version = @current_version + 1
					END
			END

			UPDATE CurrentVersion SET current_version = @newCurrentVersion
		END
	END

EXEC goToVersion 7

ALTER TABLE Employee ADD manager_id INT
SELECT *
FROM CurrentVersion

SELECT *
FROM TableProcedure