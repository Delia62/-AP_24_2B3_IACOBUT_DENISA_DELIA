public class Truck extends Vehicle{  //Iacobut Denisa-Delia
    private int capacity;

    /**
     *
     * @param name
     * @param capacity
     */
    public Truck(String name, int capacity) {
        super(name);
        this.capacity = capacity;

    }

    /**
     *
     * @return
     */
    public int getCapacity(){
        return capacity;
    }
}
