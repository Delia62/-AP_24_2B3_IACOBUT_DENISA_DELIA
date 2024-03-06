public class Vehicle{    //Iacobut Denisa-Delia
    private String name;
    private Depot depot;
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public Depot getDepot(){
        return this.depot;
    }
    public void setDepot(Depot depot){
        this.depot = depot;
    }
    public Vehicle(String name){
        this.name = name;
    }
    public Vehicle(String name, Depot depot){
        this.name = name;
        this.depot = depot;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "name='" + name + '\''+
                '}';
    }

    @Override
    public boolean equals(Object obj){
        if(obj == null || !(obj instanceof Vehicle)){
            return false;
        }
        Vehicle other = (Vehicle) obj;
        return name.equals(other.name);
    }
}

