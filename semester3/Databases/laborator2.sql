CREATE TABLE Employee(
	employee_id INT PRIMARY KEY IDENTITY(1,1),
	employee_name VARCHAR(100),
	month_of_birth INT,
	salary INT,
	
);
CREATE TABLE Departament(
	departament_id INT PRIMARY KEY IDENTITY(1,1),
	departament_name VARCHAR(100),
	
	
);
CREATE TABLE Manager(
	manager_id INT PRIMARY KEY IDENTITY(1,1),
	manager_name VARCHAR(100),
	year_of_birth INT,
	departament_id INT FOREIGN KEY REFERENCES Departament(departament_id)
	
);

CREATE TABLE Works_at(
	employee_id INT FOREIGN KEY REFERENCES Employee(employee_id),
	departament_id  INT FOREIGN KEY REFERENCES Departament(departament_id),
	UNIQUE(employee_id,departament_id )
	);

CREATE TABLE Brand(
	brand_id INT PRIMARY KEY IDENTITY(1,1),
	brand_name VARCHAR(100)
	
);
CREATE TABLE Product(
	product_id INT PRIMARY KEY IDENTITY(1,1),
	product_name VARCHAR(100),
	brand_id INT FOREIGN KEY REFERENCES Brand(brand_id)
	
);
CREATE TABLE Usage(
	usage_id INT PRIMARY KEY IDENTITY(1,1),
	usage_name VARCHAR(100)
);
CREATE TABLE Used_for(
	product_id INT FOREIGN KEY REFERENCES Product(product_id),
	usage_id  INT FOREIGN KEY REFERENCES Usage(usage_id),
	
	UNIQUE(product_id,usage_id )
	);
CREATE TABLE Location(
	location_id INT PRIMARY KEY IDENTITY(1,1),
	location_name VARCHAR(100),
	
	
);
CREATE TABLE Street(
	street_id INT PRIMARY KEY IDENTITY(1,1),
	street_name VARCHAR(100),
	location_id INT FOREIGN KEY REFERENCES Location(location_id),
	number INT
);
--INSERT FOR AT LEAST 4 TABLES ; at least 1 statement must violate referential integrity constraints;

INSERT INTO Employee(employee_name,month_of_birth,salary) VALUES ('Stefana',6,3000);
INSERT INTO Employee(employee_name,month_of_birth,salary) VALUES ('Ana',12,1500);
INSERT INTO Employee(employee_name,month_of_birth,salary) VALUES ('Maria',4,1700);
INSERT INTO Employee(employee_name,month_of_birth,salary) VALUES ('Oana',3,3500);
INSERT INTO Employee(employee_name,month_of_birth,salary) VALUES ('Adrian',1,1900);
INSERT INTO Employee(employee_name,month_of_birth,salary) VALUES ('Marian',5,2500);
INSERT INTO Employee(employee_name,month_of_birth,salary) VALUES ('NU E BINE 1',13,344);
INSERT INTO Employee(employee_name,month_of_birth,salary) VALUES ('NU E BINE 2',20,341);
INSERT INTO Employee(employee_name,month_of_birth,salary) VALUES ('Emilia',3,1800);
INSERT INTO Employee(employee_name,month_of_birth,salary) VALUES ('Amelia',3,1900);
INSERT INTO Employee(employee_name,month_of_birth,salary) VALUES ('Dragos',12,6000);

SELECT * FROM Employee;
INSERT INTO Manager(manager_name,year_of_birth,departament_id) VALUES ('Emilia',2000,1);
INSERT INTO Manager(manager_name,year_of_birth,departament_id) VALUES ('Maria',1980,2);
INSERT INTO Manager(manager_name,year_of_birth,departament_id) VALUES ('Vasile',1964,3);
INSERT INTO Manager(manager_name,year_of_birth) VALUES ('Aurel',1970);
INSERT INTO Manager(manager_name,year_of_birth,departament_id) VALUES ('ANA',1980,2);
SELECT * FROM Manager;
INSERT INTO Departament(departament_name) VALUES ('Cleaning');
INSERT INTO Departament(departament_name) VALUES ('Makeup');
INSERT INTO Departament(departament_name) VALUES ('Security');
INSERT INTO Departament(departament_name) VALUES ('Parfumes');
SELECT * FROM Departament;
INSERT INTO Works_at(employee_id,departament_id) VALUES (1,4);
INSERT INTO Works_at(employee_id,departament_id) VALUES (2,4);
INSERT INTO Works_at(employee_id,departament_id) VALUES (3,1);
INSERT INTO Works_at(employee_id,departament_id) VALUES (4,2);
INSERT INTO Works_at(employee_id,departament_id) VALUES (5,3);
INSERT INTO Works_at(employee_id,departament_id) VALUES (6,3);
INSERT INTO Works_at(employee_id,departament_id) VALUES (6,1);
--INSERT INTO Works_at(employee_id,departament_id) VALUES (500,1);
--this statement violates referential integrity constraints because there is no such employee_id with the value 500.
SELECT * FROM Works_at;
INSERT INTO Brand(brand_name) VALUES ('Tarte');
INSERT INTO Brand(brand_name) VALUES ('Rare Beauty');
INSERT INTO Brand(brand_name) VALUES ('Kat Von D');
SELECT * FROM Brand;
INSERT INTO Product(product_name,brand_id) VALUES ('Tattoo liner',3);
INSERT INTO Product(product_name,brand_id) VALUES ('Contour palette',3);
INSERT INTO Product(product_name,brand_id) VALUES ('Liquid blush',2);
INSERT INTO Product(product_name,brand_id) VALUES ('Shape tape',1);
INSERT INTO Product(product_name,brand_id) VALUES ('Body lotion',2);
SELECT * FROM Product;
INSERT INTO Location(location_name) VALUES ('Sephora Iulius Mall');
INSERT INTO Location(location_name) VALUES ('MAC Iulius Mall');
INSERT INTO Location(location_name) VALUES ('Vivo');
INSERT INTO Location(location_name) VALUES (' Centru');
SELECT * FROM Location;
INSERT INTO Street(street_name,location_id,number) VALUES ('Strada Alexandru Vaida Voevod',1,53);
INSERT INTO Street(street_name,location_id,number) VALUES ('Calea Unirii',1,22);
INSERT INTO Street(street_name,location_id,number) VALUES ('Strada Fluturilor ',4,43);
INSERT INTO Street(street_name,location_id,number) VALUES ('Strada Avram Iancu ',2,492);
SELECT * FROM Street;

INSERT INTO Usage(usage_name) VALUES ('face');
INSERT INTO Usage(usage_name) VALUES ('body');
INSERT INTO Usage(usage_name) VALUES ('hair');
SELECT * FROM Usage;
INSERT INTO Used_for(product_id,usage_id) VALUES (1,1);
INSERT INTO Used_for(product_id,usage_id) VALUES (5,2);
SELECT * FROM Used_for;

--update data – for at least 3 tables;
--UPDATE FOR Departament

UPDATE Departament SET departament_name='Comercials' WHERE departament_id=1 ;-- I USED =
SELECT *
FROM Departament;
--UPDATE FOR Manager

UPDATE Manager SET departament_id=1 WHERE manager_name='Maria' ;
UPDATE Manager SET manager_name='ALINA'WHERE departament_id IS NULL ; -- I USED iS NULL

SELECT *
FROM Manager;
--UPDATE FOR Employee
UPDATE Employee SET month_of_birth=month_of_birth+6 WHERE employee_name IN ('Stefana','Maria') AND employee_id=1 ;
--I USED IN and AND
SELECT *
FROM Employee;
--delete data – for at least 2 tables.
DELETE
FROM Employee
WHERE employee_name LIKE '%NU E BINE%'; -- I USED  LIKE
DELETE
FROM Manager
WHERE year_of_birth BETWEEN 1960 and 1970; -- I USED BETWEEN

--a. 2 queries with the union operation; use UNION [ALL] and OR;
--Show the names of the managers that work at the departament with the id 2 and the department name using union.
SELECT  M.manager_name
FROM Manager M
WHERE M.departament_id = 2
UNION
SELECT D.departament_name
FROM Departament D
WHERE D.departament_id = 2;
--Show to location names of the locations that have the ids in the street table OR that have "Sephora" in their names
Select DISTINCT L.location_name
FROM  Location L,Street S
WHERE (L.location_id=S.location_id) OR L.location_name LIKE '%Sephora%'; -- I USED DISTINCT
--b. 2 queries with the intersection operation; use INTERSECT and IN;
--Show all employees that are managers -with intersect
SELECT E.employee_name
FROM Employee E
INTERSECT
SELECT M.manager_name
FROM Manager M
--Show all employees that are managers- with in
Select E.employee_name
FROM Employee E
WHERE E.employee_name IN(SELECT m.manager_name FROM Manager M);
--c. 2 queries with the difference operation; use EXCEPT and NOT IN;
--Show the employees that are born in december but are not managers with EXCEPT
SELECT E.employee_name 
FROM Employee E
WHERE E.month_of_birth = 12 
EXCEPT
SELECT M.manager_name
FROM Manager M;
--Show the employees that are born in december but are not managers with  NOT IN
SELECT E.employee_name 
FROM Employee E
WHERE E.month_of_birth = 12 AND E.employee_name NOT IN (SELECT M.manager_name FROM Manager M);
--d. 4 queries with INNER JOIN, LEFT JOIN, RIGHT JOIN, and FULL JOIN (one query per operator);
--one query will join at least 3 tables, while another one will join at least two many-to-many relationships;
--INNER JOIN
--Show all the products with its specific brand
SELECT P.product_name,B.brand_name
FROM Product P INNER JOIN Brand B on P.brand_id = B.[brand_id];
--LEFT JOIN
--Print all the managers and all  departament names, showing witch is the manager for every departament
---3 tables
SELECT  Distinct M.manager_name, D.departament_name
FROM Works_at W
LEFT JOIN Manager M ON M.departament_id = W.departament_id 
LEFT JOIN Departament D ON D.departament_id = W.departament_id 
--RIGHT JOIN
--Show all the product names with their specific brand
SELECT P.product_name, B.brand_name
FROM Product P
RIGHT JOIN Brand B ON P.brand_id = B.brand_id
--FULL JOIN  join at least two many-to-many relationships;
--Show the products and their usage that are for the face or the body and are at the makeup department
SELECT Distinct P.product_name, U.usage_name, D.departament_name
FROM Product P
FULL JOIN Used_for UF ON P.product_id = UF.product_id
FULL JOIN Usage U ON U.usage_id = UF.usage_id
FULL JOIN Departament D ON( (U.usage_name LIKE '%face%') OR (U.usage_name LIKE '%body%')) AND (D.departament_name LIKE '%Makeup%')
WHERE P.product_id IS NOT NULL AND U.usage_id IS NOT NULL
--e. 2 queries with the IN operator and a subquery in the WHERE clause; 
--in at least one case, the subquery must include a subquery in its own WHERE clause;
--Show the employees that work at a department that is coordinated by a manager.
SELECT E.employee_name
FROM Employee E
WHERE E.employee_id IN (
	SELECT EP.employee_id
	FROM Departament D 
	INNER JOIN Works_at WA ON D.departament_id = WA.departament_id
	INNER JOIN Employee EP ON EP.employee_id = WA.employee_id
	WHERE D.departament_id IN (
		SELECT M.departament_id
		FROM  Manager M
		
	)
)
--show the brand of the products that dont  have the id 2 and are not used in hair care.
SELECT B.brand_name 
FROM Brand b
WHERE B.brand_id IN (
	SELECT P.brand_id
	FROM Product P
	WHERE P.product_id IN (
		SELECT UF.product_id
		FROM Used_for UF, Usage U
		WHERE NOT UF.product_id=2 AND U.usage_name IN (
			SELECT U1.usage_name
			FROM Usage U1
			WHERE NOT U1.usage_name LIKE '%hair%'
		)
	)
)
--f. 2 queries with the EXISTS operator and a subquery in the WHERE clause;
--Show the existing  streets that have the location in Iulius Mall and increment the nr street by one
SELECT S.street_name, S.number+1 AS NewStreetNumber
FROM Street S
WHERE EXISTS(
	SELECT *
	FROM Street S2 
	INNER jOIN Location L ON S2.location_id = L.location_id
	WHERE L.location_name LIKE '%Iulius%'
)
--Show all  the existing deparments if the departament "Comercials" exists 
SELECT D.departament_name
FROM Departament D
WHERE EXISTS(
	Select *
	FROM Departament D1
	WHERE D1.departament_name  LIKE '%Comercials%'
)
--g. 2 queries with a subquery in the FROM clause;    
--Show all the manager names that are not born after the year 1990 and the departament id is smaller than 10
SELECT M2.manager_name
FROM (
	SELECT *
	FROM Manager M
	WHERE NOT M.year_of_birth >1990
)M2 WHERE M2.departament_id <10
--	Show all the employees that are born before September and their names must be different than 'Marian' or 'Adrian'
SELECT E2.employee_name
FROM (
	SELECT *
	FROM Employee E
	WHERE  E.month_of_birth <9
)E2 WHERE E2.employee_name NOT IN('Marian','Adrian')
--h. 4 queries with the GROUP BY clause, 3 of which also contain the HAVING clause; 2 of the latter will also have a subquery in the HAVING clause;
--use the aggregation operators: COUNT, SUM, AVG, MIN, MAX;
--Show all the employee salaries and how many employees have that salary
SELECT E.salary, COUNT(*) AS number_of_employees
FROM Employee E
GROUP BY E.salary
--Show the employees that work at the most departments
--Using COUNT,MAX, has a subquery in the having clause
SELECT E.employee_id, E.employee_name, COUNT(*) AS Number_of_departments
FROM  Employee E INNER JOIN Works_at WA ON E.employee_id = WA.employee_id INNER JOIN Departament D ON D.departament_id = WA.departament_id
GROUP BY E.employee_id, E.employee_name
HAVING COUNT(*) = (
	SELECT MAX(C2.C)
	FROM (
		SELECT COUNT(*) C
		FROM Employee E2 INNER JOIN  Works_at WA2 ON E2.employee_id = WA2.employee_id INNER JOIN Departament D2 ON D2.departament_id = WA2.departament_id
		GROUP BY E2.employee_id, E2.employee_name
	)C2
)
--Show all the departaments and the average salaries of the employees that work on each departament.
--Using AVG,  has a subquery in the having clause
SELECT D.departament_name,AVG(E.salary) AS average_salary
FROM Employee E  INNER JOIN Works_at WA ON E.employee_id = WA.employee_id INNER JOIN Departament D ON D.departament_id = WA.departament_id
GROUP BY D.departament_name
HAVING 0 < (SELECT COUNT(WA2.employee_id)
			FROM Employee E2 INNER JOIN Works_at WA2 ON E2.employee_id = WA2.employee_id INNER JOIN Departament D2 ON D2.departament_id = WA2.departament_id
			WHERE WA2.employee_id=E2.employee_id AND WA2.departament_id=D2.departament_id)
--Show the minimum and the maximum salary on every departament
SELECT D.departament_name,MIN(E.salary) AS minimum_salary, MAX(E.salary) AS maximum_salary,SUM(E.salary) AS total_sum_of_salaries
FROM Employee E  INNER JOIN Works_at WA ON E.employee_id = WA.employee_id INNER JOIN Departament D ON D.departament_id = WA.departament_id
GROUP BY D.departament_name
HAVING 0 < (SELECT COUNT(WA2.employee_id)
			FROM Employee E2 INNER JOIN Works_at WA2 ON E2.employee_id = WA2.employee_id INNER JOIN Departament D2 ON D2.departament_id = WA2.departament_id
			WHERE WA2.employee_id=E2.employee_id AND WA2.departament_id=D2.departament_id)
--i. 4 queries using ANY and ALL to introduce a subquery in the WHERE clause (2 queries per operator);
--rewrite 2 of them with aggregation operators, and the other 2 with IN / [NOT] IN.
--Show the products that are not from the brands 'Prada ', 'Gucci' or 'Tarte'
--Using ANY
SELECT P.product_name
FROM Product P
WHERE P.brand_id = ANY(
	SELECT B.brand_id
	FROM Brand B
	WHERE B.brand_name  NOT IN('Prada','Gucci','Tarte'))
--Rewritten with in
SELECT P.product_name
FROM Product P
WHERE P.brand_id IN (
	SELECT B.brand_id
	FROM Brand B
	WHERE  B.brand_name  NOT IN('Prada','Gucci','Tarte'))
--
--Show the top 3 employees that have a better salary  than the employee with the smallest salary that was born before november
SELECT TOP 3 E.employee_name
FROM Employee E
WHERE E.salary > ANY (
	SELECT E2.salary
	FROM Employee E2
	WHERE E2.month_of_birth <=10)
ORDER BY E.salary 

--rewritten with Min(aggregation operator) instead of Any 
SELECT TOP 3 E.employee_name
FROM Employee E
WHERE E.salary > (
	SELECT MIN(E2.salary)
	FROM Employee E2
	WHERE E2.month_of_birth <=10)
ORDER BY E.salary 

--Find the employee with the best salary
--Used ALL
SELECT E.employee_name
FROM Employee E
WHERE E.salary >  ALL (
  SELECT E2.salary
  FROM Employee E2
  Where E.employee_id !=E2.employee_id
);
--rewritten with MAX instead of ALL
SELECT E.employee_name
FROM Employee E
WHERE E.salary > (
	SELECT MAX(E2.salary)
	FROM Employee E2
	WHERE E.employee_id!=E2.employee_id)

--Find all the streets that dont have the location in a place where has 'Sephora' in it
SELECT S.*
FROM Street S
WHERE S.location_id<> ALL (
	SELECT L.location_id
	FROM Location L
	WHERE L.location_name LIKE '%Sephora%')

--rewritten using NOT IN
SELECT S.*
FROM Street S
WHERE S.location_id NOT IN (
	SELECT L.location_id
	FROM Location L
	WHERE L.location_name LIKE '%Sephora%')
