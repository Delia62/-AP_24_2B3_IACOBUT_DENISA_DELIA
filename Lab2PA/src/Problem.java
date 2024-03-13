import java.util.Arrays;

public class Problem{  //Iacobut Denisa-Delia

    private Depot[] depots = new Depot[3];
    private Client[] clients = new Client[3];
    private Vehicle[] vehicles = new Vehicle[5];
    private int depCount;
    private int clientCount;
    private int vehicleCount;
    /**
     * metoda getVehicles

     */
    public Vehicle[] getVehicles() {
        return vehicles;
    }

    /**
     * //Constructor
     */
    public Problem() {
        vehicleCount = 0;
        depCount = 0;
        clientCount = 0;


    }

    /**
     * Functie pentru adaugare depozit nou
     * @param depots
     */
    public void addDepots(Depot depots) {
        int ok = 1;
        if(depCount == 0)
        {
            this.depots[depCount++] = depots;
        }
        else{
            for (int i = 0; i< depCount; i++ ) {
                if(depots.equals(this.depots[i])){
                    ok = 0;
                    break;
                }
            }

            if(ok == 0){
                new Solution("Try another depot!");
            }
            else {
                depCount++;
                this.depots[depCount] = depots;
            }
        }


    }

    /**
     *
     * @param clients
     */
    public void addClients(Client  clients) {
        int ok = 1;
        if(clientCount == 0)
        {
            this.clients[clientCount++] = clients;
        }
        else{
            for (int i = 0; i< clientCount; i++ ) {
                if(clients.equals(this.clients[i])){
                    ok = 0;
                    break;
                }
            }

            if(ok == 0){
                new Solution("Try another client!");
            }
            else {
                clientCount++;
                this.clients[clientCount] = clients;
            }
        }

    }

    public void addVehicles(Drone  vehicles) {
        int ok = 1;
        if(vehicleCount == 0)
        {
            this.vehicles[vehicleCount++] = vehicles;
        }
        else{
            for (int i = 0; i< vehicleCount; i++ ) {
                if(vehicles.equals(this.vehicles[i])){
                    ok = 0;
                    break;
                }
            }

            if(ok == 0){
                new Solution("Try another drone!");
            }
            else {
                vehicleCount++;
                this.vehicles[vehicleCount] = vehicles;
            }
        }

    }
    public void addVehicles(Truck vehicles) {
        int ok = 1;
        if(vehicleCount == 0)
        {
            this.vehicles[vehicleCount++] = vehicles;
        }
        else{
            for (int i = 0; i< vehicleCount; i++ ) {
                if(vehicles.equals(this.vehicles[i])){
                    ok = 0;
                    break;
                }
            }

            if(ok == 0){
                new Solution("Try another truck!");
            }
            else {
                vehicleCount++;
                this.vehicles[vehicleCount] = vehicles;
            }
        }
    }

    @Override
    public String toString() {
        return "Problem{" +
                "depots=" + Arrays.toString(depots) +
                ", clients=" + Arrays.toString(clients) +
                ", vehicles=" + Arrays.toString(vehicles) +
                ", depCount=" + depCount +
                ", clientCount=" + clientCount +
                ", vehicleCount=" + vehicleCount +
                '}';
    }
}