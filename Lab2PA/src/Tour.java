import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;

public class Tour{  //Iacobut Denisa-Delia
    private Vehicle vehicle;
    private Client[] clients;
    private int countClients = 0;
    private int currentHour = 0;
    private int currentMinute = 0;
    ;

    /**
     *
     * @param vehicle
     * @param clients
     */
    public Tour(Vehicle vehicle, Client[] clients){
        this.vehicle = vehicle;
        this.clients = clients;
    }

    /**
     *
     * @param vehicle
     * @param clients
     */
    public Tour(Vehicle vehicle, Client clients){
        this.clients = new Client[8];
        this.vehicle = vehicle;
        if(countClients == 0){
            this.clients[countClients++] = clients;
        }


    }

    /**
     *
     * @param vehicle
     * @param clients
     */

    public void alocareClienti(Vehicle vehicle, Client... clients){

        for (Client v : clients){
            if(countClients != 0 && this.clients[countClients - 1].equals(v)){
                new Solution("Try another client..");
            }
            else{
                if( currentHour < v.getMaxTime().getHour()){
                    if(currentMinute < v.getMaxTime().getMinute() ){
                        this.clients[countClients++] = v;
                        currentHour += v.getMinTime().getHour();
                        currentMinute += v.getMinTime().getMinute();
                    }
                }
                else {
                    if(currentHour == v.getMaxTime().getHour() && currentMinute < v.getMaxTime().getMinute() ){
                        this.clients[countClients++] = v;
                        currentMinute += v.getMinTime().getMinute();
                    }
                }

            }
        }


    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Tour{" +
                "vehicle=" + vehicle +
                ", clients=" + Arrays.toString(clients) +
                '}';
    }
}