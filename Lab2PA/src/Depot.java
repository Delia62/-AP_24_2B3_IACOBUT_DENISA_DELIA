import java.util.Arrays;

public class Depot {     //Iacobut Denisa-Delia
    private String name;
    private String address;
    private Vehicle[] vehicles;

    Depot(String name, String adresa, Vehicle[] vehicles) {
        this.name = name;
        this.address = adresa;
        this.vehicles = vehicles;
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

    public void SetVehicles(Vehicle... vehicles) {
        this.vehicles = vehicles;
        for (Vehicle v : vehicles) {
            v.setDepot(this);
        }
    }

    public Vehicle[] getVehicles() {
        return this.vehicles;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", vehicles=" + Arrays.toString(vehicles) +
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null || !(obj instanceof Depot)){
            return false;
        }
        Depot other = (Depot) obj;
        return name.equals(other.name);
    }
}

