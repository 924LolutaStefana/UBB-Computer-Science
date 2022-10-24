CREATE TABLE Employee(
	employee_id INT PRIMARY KEY IDENTITY(1,1),
	employee_name VARCHAR(100),
	
);
CREATE TABLE Departament(
	departament_id INT PRIMARY KEY IDENTITY(1,1),
	departament_name VARCHAR(100),
	
	
);
CREATE TABLE Manager(
	manager_id INT PRIMARY KEY IDENTITY(1,1),
	manager_name VARCHAR(100),
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

INSERT INTO Employee(employee_name,age) VALUES ('Stefana',19);
INSERT INTO Employee(employee_name,age) VALUES ('Ana',30);
INSERT INTO Employee(employee_name,age) VALUES ('Maria',40);
INSERT INTO Employee(employee_name,age) VALUES ('Oana',23);
INSERT INTO Employee(employee_name,age) VALUES ('Adrian',45);
INSERT INTO Employee(employee_name,age) VALUES ('Marian',50);
SELECT * FROM Employee;
INSERT INTO Manager(manager_name,age) VALUES ('Liliana',54);
INSERT INTO Manager(manager_name,age) VALUES ('Emilia',47);
SELECT * FROM Manager;
INSERT INTO Departament(departament_name,manager_id) VALUES ('Cleaning',1);
INSERT INTO Departament(departament_name,manager_id) VALUES ('Makeup',2);
INSERT INTO Departament(departament_name,manager_id) VALUES ('Security',1);
INSERT INTO Departament(departament_name,manager_id) VALUES ('Parfumes',2);
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


