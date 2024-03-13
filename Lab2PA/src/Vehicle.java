import java.util.Objects;

abstract class Vehicle{    //Iacobut Denisa-Delia
    private String name;
    private Depot depot;

    /**
     * metoda setName
     * @param name
     */
    public void setName(String name){
        this.name=name;
    }

    /**
     * metoda getName
     * @return
     */
    public String getName(){
        return this.name;
    }

    /**
     * metoda getDepot
     * @return
     */
    public Depot getDepot(){
        return this.depot;
    }

    /**
     * metoda setDepot
     * @param depot
     */
    public void setDepot(Depot depot) {
        if (this.depot != null && !this.depot.equals(depot)) {
           new Solution("Vehicle already belongs to another depot.");
        }
        this.depot = depot;
    }

    /**
     * constructor Vehicle
     * @param name
     */
    public Vehicle(String name){
        this.name = name;
    }
    public Vehicle(String name, Depot depot){
        this.name = name;
        this.depot = depot;
    }

    /**
     * Constructor Vehicle
     */
    public Vehicle(){

    }
    public abstract int getCapacity();

    /**
     * metoda toString
     * @return
     */
    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\''+
                '}';
    }

    /**
     * Metoda equals
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return Objects.equals(name, vehicle.name) && Objects.equals(depot, vehicle.depot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, depot);
    }
}

