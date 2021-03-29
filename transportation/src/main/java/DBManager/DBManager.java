package DBManager;

import transportation.parkingindustry.Airport;
import transportation.person.Customer;
import transportation.person.Driver;
import transportation.person.sellers.AirportTicketSeller;
import transportation.person.sellers.DestinationTicketsSeller;
import transportation.person.sellers.Seller;
import transportation.transportindustry.TransportIndustry;
import transportation.transportindustry.destinations.Destination;
import transportation.transportindustry.destinations.ExoticDestination;
import transportation.transportindustry.tickets.NormalTicket;
import transportation.transportindustry.tickets.ParkingTicket;
import transportation.transportindustry.tickets.Ticket;
import transportation.transportindustry.tickets.VIPTicket;
import transportation.transportindustry.vehicles.Bus;
import transportation.transportindustry.vehicles.Plane;
import transportation.transportindustry.vehicles.Vehicle;
import transportation.transportindustry.vehicles.cars.Car;
import transportation.transportindustry.vehicles.cars.ElectricCar;
import transportation.transportindustry.vehicles.trains.FastTrain;
import transportation.transportindustry.vehicles.trains.Train;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DBManager {
    public static final int SELLER_INDEX = 0;
    public static final int TRANSPORT_INDUSTRY_ID = 1;
    public static final int AIRPORT_ID = 1;
    public static final String DB_LOCALHOST = "localhost";
    public static final String DB_PORT = "3306";
    public static final String DB_NAME = "transportation";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "asdfasdf"; // enter your password
    private static DBManager dbManager = new DBManager();
    private static int counterForPersonInDB = 1;
    private Connection connection;

    private DBManager(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Problem with driver ! -> Not found");
        }
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + DB_LOCALHOST + ":" + DB_PORT +"/" + DB_NAME, DB_USERNAME, DB_PASSWORD);
        }catch (SQLException e){
            System.out.println("There was a problem with the connection ! " + e.getMessage());
        }
    }

    public static DBManager getInstance(){
        return dbManager;
    }

    public Connection getConnection() {
        return connection;
    }

    public void printOutput(){
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareCall("SELECT * FROM Person p JOIN Driver d on p.id = d.id");
            ResultSet resultSet = preparedStatement.executeQuery(); // statistic 1
            while(resultSet.next()){
                System.out.println("Person id: " + resultSet.getInt(  1)); // ids
                System.out.println("Person name: " + resultSet.getString(2)); //names
                System.out.println("Person money: " + resultSet.getDouble(3));    // money
                System.out.println("Person gender: " + resultSet.getString(4)); // genders
                System.out.println();
            }
            System.out.println();
            preparedStatement = connection.prepareCall("SELECT * FROM Vehicle"); // statistic 2
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("Vehicle id: " + resultSet.getInt(1)); // ids
                System.out.println("Vehicle name: " + resultSet.getString(2)); // names
                System.out.println("Vehicle model: " + resultSet.getString(3)); // models
                System.out.println();
            }
            System.out.println();
            preparedStatement = connection.prepareCall("SELECT * FROM Destination"); // statistic 3
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                System.out.println("Destination id: " + resultSet.getInt(1)); //ids
                System.out.println("Destination name: " + resultSet.getString(2)); //names
                System.out.println("Destination rating: " + resultSet.getInt(3)); //ratings
                System.out.println();
            }
        } catch (SQLException throwables) {
            System.out.println("There is a problem with queries! " + throwables.getMessage());
        }

    }

    public void addAll(TransportIndustry transportIndustry, HashSet<Vehicle> vehicles, List<Driver> drivers,
                       List<DestinationTicketsSeller> destinationTicketsSellers, Airport airport,
                       List<AirportTicketSeller> airportTicketSellers,
                       List<Driver> parkingDrivers, List<Customer> customers){
         int idCounter = 1;
         Connection connection = getConnection();
         HashMap<String,Integer> destinationsById = addDestinationsToDB(connection,idCounter,destinationTicketsSellers);
         HashMap<ParkingTicket,Integer> parkingTicketsById = addTicketsToDB(connection,idCounter,destinationTicketsSellers,airportTicketSellers,destinationsById); // add both tickets
         HashMap<String,Integer> vehiclesById = addVehiclesToDB(connection,idCounter,vehicles,parkingDrivers);
         HashMap<String,Integer> driversById = addDriversToDB(connection,drivers,parkingDrivers,vehiclesById,destinationsById,parkingTicketsById);
         addCustomersToDB(connection,customers,driversById);
         addSellersToDB(connection,destinationTicketsSellers, airportTicketSellers);
         addTransportIndustryToDB();
         addAirportToDB();
    }

    private void addTransportIndustryToDB(){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Transport_Industry(id) VALUE(?)");
            preparedStatement.setInt(1, TRANSPORT_INDUSTRY_ID);
            preparedStatement.executeUpdate();
        }catch (SQLException throwables) {
            System.out.println("TRANSPORT INDUSTRY");
            System.out.println("There is a problem with queries! " + throwables.getMessage());
        }
    }

    private void addAirportToDB(){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Airport(id) VALUE(?)");
            preparedStatement.setInt(1, AIRPORT_ID);
            preparedStatement.executeUpdate();
        }catch (SQLException throwables) {
            System.out.println("AIRPORT");
            System.out.println("There is a problem with queries! " + throwables.getMessage());
        }
    }

    private void addSellersToDB(Connection connection,List<DestinationTicketsSeller> destinationTicketsSellers,
                                List<AirportTicketSeller> airportTicketSellers){
            String subtype = "";
            for (Seller s : destinationTicketsSellers) {
                subtype = "DestinationTicketsSeller";
                addPersonToDB(connection, counterForPersonInDB, s.getName(), s.getMoney(), String.valueOf(s.getGender()), s.getAge());
                addSellerToDB(connection, counterForPersonInDB, TRANSPORT_INDUSTRY_ID);
                addSubtypeSellerToDB(connection,subtype,counterForPersonInDB);
                counterForPersonInDB++;
            }
            for (Seller s : airportTicketSellers) {
                subtype = "AirportTicketsSeller";
                addPersonToDB(connection, counterForPersonInDB, s.getName(), s.getMoney(), String.valueOf(s.getGender()), s.getAge());
                addSellerToDB(connection, counterForPersonInDB, TRANSPORT_INDUSTRY_ID);
                addSubtypeSellerToDB(connection,subtype,counterForPersonInDB);
                counterForPersonInDB++;
            }

    }

    private void addSubtypeSellerToDB(Connection connection,String sellerType,int idCounter){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO " + sellerType + "(id) VALUE(?)");
            preparedStatement.setInt(1, idCounter);
            preparedStatement.executeUpdate();
        }catch (SQLException throwables) {
            System.out.println("SELLERTYPE");
            System.out.println("There is a problem with queries! " + throwables.getMessage());
        }
    }

    private void addSellerToDB(Connection connection,int idCounter,int transportIndustryId){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Seller(id,transport_industry_id) VALUE(?,?)");
            preparedStatement.setInt(1, idCounter);
            preparedStatement.setInt(2, transportIndustryId);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("SELLER");
            System.out.println("There is a problem with queries! " + throwables.getMessage());
        }
    }

    private  void addCustomersToDB(Connection connection,List<Customer> customers,HashMap<String,Integer> driversById){
        for(Customer c : customers){
            addPersonToDB(connection,counterForPersonInDB,c.getName(),c.getMoney(),String.valueOf(c.getGender()),c.getAge());
            int driverId = 0;
            if(c.getDriver() != null && driversById.get(c.getDriver().getName()) != null){
                driverId = driversById.get(c.getDriver().getName());
            }
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Customer(id, has_discount_code,discount_percentage,driver_id) VALUE(?,?,?,?)");
                preparedStatement.setInt(1, counterForPersonInDB);
                preparedStatement.setBoolean(2, c.getIsHasDiscountCode());
                preparedStatement.setInt(3, c.getDiscountCodePercentage());
                preparedStatement.setInt(4, driverId);
                preparedStatement.executeUpdate();
                counterForPersonInDB++;
            } catch (SQLException throwables) {
                System.out.println("CUSTOMER");
                System.out.println("There is a problem with queries! " + throwables.getMessage());
            }
        }
    }

    private HashMap<String,Integer> addDriversToDB(Connection connection,List<Driver> drivers,
                                                   List<Driver> parkingDrivers,
                                                   HashMap<String,Integer> vehiclesById,
                                                   HashMap<String,Integer> destinationsById,
                                                   HashMap<ParkingTicket,Integer> parkingTicketsById){
        HashMap<String,Integer> driversById = new HashMap<>();

        drivers.addAll(parkingDrivers);

        for(Driver d : drivers) {
            int destinationId = 0;
            int parkingTicketId = 0;
            if(d == null){
                System.out.println("There is no driver!");
                continue;
            }
            if (d.getHoursParked() <= 0 && destinationsById.get(d.getDestination().getName()) != null) {
                destinationId = destinationsById.get(d.getDestination().getName());
            }else{
                parkingTicketId = parkingTicketsById.get(d.getParkingTicket());
            }
            addPersonToDB(connection,counterForPersonInDB,d.getName(),d.getMoney(),String.valueOf(d.getGender()),d.getAge());
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = connection.prepareStatement("INSERT INTO Driver(id, vehicle_id,destination_id,transport_industry_id,airport_id ,parking_ticket_id,hours_parked) VALUE(?,?,?,?,?,?,?)");
                preparedStatement.setInt(1, counterForPersonInDB);
                preparedStatement.setInt(2, vehiclesById.get(d.getVehicle().getName()));
                preparedStatement.setInt(3, destinationId);
                preparedStatement.setInt(4, TRANSPORT_INDUSTRY_ID);
                preparedStatement.setInt(5, AIRPORT_ID);
                preparedStatement.setInt(6,parkingTicketId);
                preparedStatement.setInt(7,d.getHoursParked());
                preparedStatement.executeUpdate();
                driversById.put(d.getName(),counterForPersonInDB);
                counterForPersonInDB++;
            } catch (SQLException throwables) {
                System.out.println("DRIVER");
                System.out.println("There is a problem with queries! " + throwables.getMessage());
            }
        }
        return driversById;
    }

    private void addPersonToDB(Connection connection,int idCounter,String name,double money,String gender,int age){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Person(id,name,money,gender,age) VALUE(?,?,?,?,?)");
            preparedStatement.setInt(1, idCounter);
            preparedStatement.setString(2, name);
            preparedStatement.setDouble(3, money);
            preparedStatement.setString(4, gender);
            preparedStatement.setInt(5, age);
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println("PERSON");
            System.out.println("There is a problem with queries! " + throwables.getMessage());
        }
    }


    private HashMap<String,Integer> addVehiclesToDB(Connection connection,int idCounter,HashSet<Vehicle> vehicles,List<Driver> parkingDrivers){
        HashMap<String,Integer> vehiclesById = new HashMap<>();
        HashSet<Vehicle> parkingDriverVehicles = new HashSet<>();
        for (Driver d : parkingDrivers){
            parkingDriverVehicles.add(d.getVehicle());
        }
        vehicles.addAll(parkingDriverVehicles);

        for(Vehicle v : vehicles){
            if(v == null){
                System.out.println("There is no vehicle!");
                continue;
            }
            String vehicleType = "";
            switch (v.getVehicleType()){
                case BUS: vehicleType = "Bus";break;
                case CAR: vehicleType = "Car";break;
                case TRAIN: vehicleType = "Train";break;
                case PLANE: vehicleType = "Plane";break;
                case SHIP: vehicleType = "Ship";break;
            }
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Vehicle(id,name,model,speed,year_produced,vehicle_type,hourly_rate) VALUE(?,?,?,?,?,?,?)");
                preparedStatement.setInt(1,idCounter);
                preparedStatement.setString(2,v.getName());
                preparedStatement.setString(3,v.getModel());
                preparedStatement.setInt(4,v.getSpeed());
                preparedStatement.setInt(5,v.getYearProduced());
                preparedStatement.setString(6,String.valueOf(v.getVehicleType()));
                preparedStatement.setDouble(7,v.getHourlyRate());
                preparedStatement.executeUpdate();

                switch (v.getVehicleType()){
                    case BUS: {
                        preparedStatement = connection.prepareStatement("INSERT INTO " + vehicleType +"(id,registration_number) VALUE(?,?)");
                        preparedStatement.setInt(1,idCounter);
                        preparedStatement.setString(2,((Bus)v).getRegistrationNumber());
                    }break;
                    case CAR: {
                        preparedStatement = connection.prepareStatement("INSERT INTO " + vehicleType +"(id,registration_number) VALUE(?,?)");
                        preparedStatement.setInt(1,idCounter);
                        preparedStatement.setString(2,((Car)v).getRegistrationNumber());
                        String subtype = "";
                        if(v instanceof ElectricCar){
                            subtype = "ElectricCar";
                        }else{
                            subtype = "InternalCombustionEngineCar";
                        }
                        preparedStatement = connection.prepareStatement("INSERT INTO " + subtype +"(id) VALUE(?)");
                        preparedStatement.setInt(1,idCounter);

                    }break;
                    case TRAIN: {
                        preparedStatement = connection.prepareStatement("INSERT INTO " + vehicleType +"(id,distance_type) VALUE(?,?)");
                        preparedStatement.setInt(1,idCounter);
                        preparedStatement.setString(2,String.valueOf(((Train)v).getDistanceType()));
                        String subtype = "";
                        if(v instanceof FastTrain){
                            subtype = "FastTrain";
                        }else{
                            subtype = "NormalTrain";
                        }
                        preparedStatement = connection.prepareStatement("INSERT INTO " + subtype +"(id) VALUE(?)");
                        preparedStatement.setInt(1,idCounter);
                    }break;
                    case PLANE: {
                        preparedStatement = connection.prepareStatement("INSERT INTO " + vehicleType +"(id,fans_length) VALUE(?,?)");
                        preparedStatement.setInt(1,idCounter);
                        preparedStatement.setInt(2,((Plane)v).getFansLength());
                    }break;
                    case SHIP: {
                        preparedStatement = connection.prepareStatement("INSERT INTO " + vehicleType +"(id) VALUE(?)");
                        preparedStatement.setInt(1,idCounter);
                    }break;
                }
                vehiclesById.put(v.getName(),idCounter);
                preparedStatement.executeUpdate();
                idCounter++;
            } catch (SQLException throwables) {
                System.out.println("VEHICLES");
                System.out.println("There is a problem with queries! " + throwables.getMessage());
            }


        }

        return vehiclesById;
    }

    private HashMap<ParkingTicket,Integer> addTicketsToDB(Connection connection,int idCounter, List<DestinationTicketsSeller> destinationTicketsSellers,
                                                List<AirportTicketSeller> airportTicketSellers,HashMap<String,Integer> destinationsById){
        HashMap<ParkingTicket,Integer> parkingTicketsById = new HashMap<>();

        List<Ticket> destinationTickets = destinationTicketsSellers.get(SELLER_INDEX).getTickets();
        destinationTickets.addAll(airportTicketSellers.get(SELLER_INDEX).getTickets());
        for(Ticket t : destinationTickets){

            String ticketType = "";
            int idDestination = 0;
            if(t instanceof NormalTicket){
                ticketType = "NormalTicket";
                idDestination = destinationsById.get(t.getDestination().getName());
            }else if(t instanceof VIPTicket){
                ticketType = "VIPTicket";
                idDestination = destinationsById.get(t.getDestination().getName());
            }else{
                ticketType = "ParkingTicket";
                parkingTicketsById.put(((ParkingTicket) t),idCounter);
            }
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Ticket(id,destination_id,price,is_discount_rate_available) VALUES(?,?,?,?)");
                preparedStatement.setInt(1,idCounter);
                preparedStatement.setInt(2, idDestination);
                preparedStatement.setDouble(3,t.getPrice());
                preparedStatement.setBoolean(4,t.getIsDiscountRateAvailable());
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("INSERT INTO "+ ticketType +" VALUES(?)");
                preparedStatement.setInt(1,idCounter);
                preparedStatement.executeUpdate();
                idCounter++;

            } catch (SQLException throwables) {
                System.out.println("TICKETS");
                System.out.println("There is a problem with queries! " + throwables.getMessage());
            }
        }

        return parkingTicketsById;
    }

    private HashMap<String,Integer> addDestinationsToDB(Connection connection,int idCounter,List<DestinationTicketsSeller> destinationTicketsSellers){
        HashSet<String> destinationNames = new HashSet<>();
        List<Destination> destinations = new ArrayList<>();

        for(Ticket t : destinationTicketsSellers.get(SELLER_INDEX).getTickets()){
            if(t != null && t.getDestination() != null) {
                destinations.add(t.getDestination());
            }
        }
        HashMap<String,Integer> destinationsById = new HashMap<>();
        for(Destination s : destinations){

            if(!destinationNames.contains(s.getName())){
                destinationNames.add(s.getName());
            }else{
                continue;
            }
            String destinationType = "";
            if(s instanceof ExoticDestination){
                destinationType = "ExoticDestination";
            }else{
                destinationType = "NormalDestination";
            }
            destinationsById.put(s.getName(),idCounter);
            try {
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Destination(id,name,rating,is_visited_ofter) VALUES(?,?,?,?)");
                preparedStatement.setInt(1,idCounter);
                preparedStatement.setString(2, s.getName());
                preparedStatement.setInt(3,s.getRating());
                preparedStatement.setBoolean(4,s.getIsVisitedOften());
                preparedStatement.executeUpdate();

                preparedStatement = connection.prepareStatement("INSERT INTO "+ destinationType +"(id) VALUES(?)");
                preparedStatement.setInt(1,idCounter);
                preparedStatement.executeUpdate();
                idCounter++;

            } catch (SQLException throwables) {
                System.out.println("DESTINATIONS");
                System.out.println("There is a problem with queries! " + throwables.getMessage());
            }

        }

        return destinationsById;
    }

}
