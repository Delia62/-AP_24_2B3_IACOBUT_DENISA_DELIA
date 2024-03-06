import java.time.LocalTime;
import java.util.Arrays;

public class Lab2PA { //Iacobut Denisa-Delia
    public static void main(String[] args) {
    Client client1 = new Client();
    client1.setName("Client 1");
    client1.setType(Client.ClientType.PREMIUM);
    client1.setMinTime(LocalTime.of(8,0));
    client1.setMaxTime(LocalTime.of(12,30));
    System.out.println(client1.getName());

    Client client2 = new Client("Client 2", Client.ClientType.REGULAR);
    System.out.println(client2);

    Client client3 = new Client("Client 3", LocalTime.NOON, LocalTime.MIDNIGHT);
    System.out.println(client3);

    Vehicle vehicle1 = new Vehicle("Vehicul 1");
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
    System.out.println(vehicle3);
    }
}





