CREATE TABLE Employee(
	employee_id INT PRIMARY KEY IDENTITY(1,1),
	employee_name VARCHAR(100),
	month_of_birth INT,
	
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

INSERT INTO Employee(employee_name,month_of_birth) VALUES ('Stefana',6);
INSERT INTO Employee(employee_name,month_of_birth) VALUES ('Ana',12);
INSERT INTO Employee(employee_name,month_of_birth) VALUES ('Maria',4);
INSERT INTO Employee(employee_name,month_of_birth) VALUES ('Oana',3);
INSERT INTO Employee(employee_name,month_of_birth) VALUES ('Adrian',4);
INSERT INTO Employee(employee_name,month_of_birth) VALUES ('Marian',5);
SELECT * FROM Employee;
INSERT INTO Manager(manager_name,year_of_birth) VALUES ('Emilia',2000);
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
SELECT * FROM Works_at;
INSERT INTO Brand(brand_name) VALUES ('Tarte');
INSERT INTO Brand(brand_name) VALUES ('Rare Beauty');
INSERT INTO Brand(brand_name) VALUES ('Kat Von D');
SELECT * FROM Brand;
INSERT INTO Product(product_name,brand_id) VALUES ('Tattoo liner',3);
INSERT INTO Product(product_name,brand_id) VALUES ('Contour palette',3);
INSERT INTO Product(product_name,brand_id) VALUES ('Liquid blush',2);
INSERT INTO Product(product_name,brand_id) VALUES ('Shape tape',1);
SELECT * FROM Product;