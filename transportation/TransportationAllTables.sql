
CREATE TABLE Vehicle(
	id INT PRIMARY KEY,
	name VARCHAR(255),
    model VARCHAR(255),
    speed INT,
    year_produced INT,
    vehicle_type ENUM("CAR", "TRAIN", "PLANE", "SHIP", "BUS") ,
    hourly_rate DOUBLE
);

CREATE TABLE Plane(
	id INT PRIMARY KEY REFERENCES Vehicle(id),
    fans_length int
);


CREATE TABLE Bus(
	id INT PRIMARY KEY REFERENCES Vehicle(id),
    registration_number VARCHAR(255)
);

CREATE TABLE Ship(
	id INT PRIMARY KEY REFERENCES Vehicle(id)
);

CREATE TABLE Car(
	id INT PRIMARY KEY REFERENCES Vehicle(id),
    registration_number VARCHAR(255)
);

CREATE TABLE ElectricCar(
	id INT PRIMARY KEY REFERENCES Car(id)
);

CREATE TABLE InternalCombustionEngineCar(
	id INT PRIMARY KEY REFERENCES Car(id)
);

CREATE TABLE Train(
	id INT PRIMARY KEY REFERENCES Vehicle(id),
    distance_type ENUM("LONG_DISTANCE","SHORT_DISTANCE")
);

CREATE TABLE FastTrain(
	id INT PRIMARY KEY REFERENCES Train(id)
);

CREATE TABLE NormalTrain(
	id INT PRIMARY KEY REFERENCES Train(id)
);

-- Destinations

CREATE TABLE Destination(
	id INT PRIMARY KEY,
	name VARCHAR(255),
    rating INT,
    is_visited_ofter BOOLEAN
);

CREATE TABLE ExoticDestination(
	id INT PRIMARY KEY REFERENCES Destination(id)
); 

CREATE TABLE NormalDestination(
	id INT PRIMARY KEY REFERENCES Destination(id)
); 

-- Tickets

CREATE TABLE Ticket(
	id INT PRIMARY KEY,
    destination_id INT REFERENCES Destination(id),
    price DOUBLE,
    is_discount_rate_available BOOLEAN
);

CREATE TABLE VIPTicket(
	id INT PRIMARY KEY REFERENCES Ticket(id)
);

CREATE TABLE NormalTicket(
	id INT PRIMARY KEY REFERENCES Ticket(id)
);

CREATE TABLE ParkingTicket(
	id INT PRIMARY KEY REFERENCES Ticket(id)
);

CREATE TABLE Person(
	id INT PRIMARY KEY,
    name VARCHAR(255),
    money DOUBLE,
    gender ENUM("MALE","FEMALE"),
    age INT
);

CREATE TABLE Driver(
	id INT PRIMARY KEY REFERENCES Person(id),
    vehicle_id INT REFERENCES Vehicle(id),
    destination_id INT REFERENCES Destination(id),
    transport_industry_id INT REFERENCES Transport_Industry(id),
    airport_id INT REFERENCES Airport(id),
    parking_ticket_id INT REFERENCES Parking_Ticket(id),
    hours_parked INT
);

CREATE TABLE Customer(
	id INT PRIMARY KEY REFERENCES Person(id),
    has_discount_code BOOLEAN,
    discount_percentage INT,
    driver_id INT REFERENCES Driver(id)
);

CREATE TABLE Seller(
	id INT PRIMARY KEY REFERENCES Person(id),
    ticket_id INT REFERENCES Ticket(id),
    transport_industry_id INT REFERENCES Tranport_Industry(id)
);

CREATE TABLE DestinationTicketsSeller(
	id INT PRIMARY KEY REFERENCES Seller(id)
);

CREATE TABLE AirportTicketsSeller(
	id INT PRIMARY KEY REFERENCES Seller(id),
    airport_id INT REFERENCES Airport(id)
);

CREATE TABLE Transport_Industry(
	id INT PRIMARY KEY,
    name VARCHAR(255)
);
CREATE TABLE Airport(
	id INT PRIMARY KEY,
    name VARCHAR(255),
    parking_level INT
);




