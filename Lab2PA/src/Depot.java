import java.util.Arrays;
import java.util.Objects;

public class Depot {     //Iacobut Denisa-Delia
    private String name;
    private String address;
    private Vehicle[] vehicles;

    public Depot(String name, String adresa, Vehicle[] vehicles) {
        this.name = name;
        this.address = adresa;
        this.vehicles = vehicles;
    }
public Depot(){

}
   public Depot(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return this.name;
    }

    public String getAddress() {
        return this.address;
    }

    /**
     *
     * @param vehicles
     */

    public void SetVehicles(Vehicle... vehicles) {

        this.vehicles = vehicles;
        for (Vehicle v : vehicles) {
            v.setDepot(this);
        }
    }

    /**
     *
     * @return
     */
    public Vehicle[] getVehicles() {
        return this.vehicles;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return "Depot{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", vehicles=" + Arrays.toString(vehicles) +
                '}';
    }

    /**
     *
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Depot depot = (Depot) o;
        return Objects.equals(name, depot.name) && Objects.equals(address, depot.address) && Arrays.equals(vehicles, depot.vehicles);
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(name, address);
        result = 31 * result + Arrays.hashCode(vehicles);
        return result;
    }
}

