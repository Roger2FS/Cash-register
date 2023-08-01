package Model;
public class Clients {
    private int ID ;
    private int tArrival ;
    private int tService ;
    public Clients( int ID , int tArrival, int tService ){
        this.ID = ID ;
        this.tArrival = tArrival ;
        this.tService = tService ;
    }
    public int gettArrival() {
        return tArrival;
    }
    public int gettService() {
        return tService;
    }
    public int settService(int tService) {
        this.tService = tService;
        return this.tService;
    }
    public String toString(){
        return "[" + ID + "," + tArrival + "," + tService + "]" ;
    }
}
