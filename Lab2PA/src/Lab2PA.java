import java.time.LocalTime;
import java.util.Arrays;

public class Lab2PA { //Iacobut Denisa-Delia
    public static void main(String[] args) {
    Client client1 = new Client();
    client1.setName("Client 1");
    client1.setType(Client.Type.PREMIUM);
    client1.setMinTime(LocalTime.of(8,0));
    client1.setMaxTime(LocalTime.of(12,30));
    System.out.println(client1.getName());


    Client client2 = new Client("Client 2", Client.Type.REGULAR);
    client2.setMinTime(LocalTime.of(12,40));
    client2.setMaxTime(LocalTime.of(20,30));
    System.out.println(client2);

    Problem problem1 = new Problem();

    Depot depot1 = new Depot("Depot 1");
    Depot depot2 = new Depot("Depot 2");

    Truck truck1 = new Truck("Truck 1", 50);
    Truck truck2 = new Truck("Truck 2", 80);

    Drone drone1 = new Drone("Drone 1", 60);
    Drone drone2 = new Drone("Drone 2", 90);

    problem1.addDepots(depot1);
    problem1.addDepots(depot2);
    depot2.SetVehicles(drone1,drone2);


    problem1.addClients(client1);
    problem1.addClients(client2);


    problem1.addVehicles(drone1);
    problem1.addVehicles(truck1);
    problem1.addVehicles(drone2);
    problem1.addVehicles(truck2);

    System.out.println(problem1);

    Tour tour1 = new Tour(drone1, client1);

    tour1.alocareClienti(drone1,client2);
    tour1.alocareClienti(drone1,client1);
    System.out.println(tour1);

    /*Vehicle vehicle1 = new Vehicle("Vehicul 1");
    Vehicle vehicle2 = new Vehicle("Vehicul 2");
    Vehicle vehicle3 = new Vehicle("Vehicul 3");

    Depot depot1 = new Depot("Depot 1");
    depot1.setAddress("Bulevardul Mihai Eminescu");
    depot1.SetVehicles(vehicle1,vehicle2);

    vehicle1.setDepot(depot1);
    vehicle2.setDepot(depot1);

    Depot depot2 = new Depot("Depot 2");
    depot2.SetVehicles(vehicle3);
    vehicle3.setDepot(depot2);
    System.out.println(depot1);
    System.out.println(vehicle3);*/

    }
}





