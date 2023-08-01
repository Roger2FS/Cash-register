package Model;
import Model.Clients;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
public class Coada implements Runnable{
    private BlockingQueue<Clients> clienti ;
    private AtomicInteger perioadaDeAsteptare ;
    private int averageWaitingTime ;
    private int averageServiceTime;
    public Coada(){
        this.clienti = new LinkedBlockingQueue<>() ;
        this.perioadaDeAsteptare = new AtomicInteger(0);
        this.averageWaitingTime = averageWaitingTime;
        this.averageServiceTime = averageServiceTime;
    }
    public void addClient(Clients client){
        this.clienti.add(client) ;
        int a = this.perioadaDeAsteptare.get() ;
        this.perioadaDeAsteptare.set(a+client.gettService());

        if(this.averageWaitingTime == 0){
            int b = this.averageWaitingTime ;
            this.averageWaitingTime = b + client.gettArrival() ;
        }
        else{
            int c = this.averageWaitingTime ;
            this.averageWaitingTime = (c + client.gettArrival()) / 2 ;
        }
        if(this.averageServiceTime == 0){
            int d = this.averageServiceTime ;
            this.averageServiceTime = d + client.gettService() ;
        }
        else{
            int e = this.averageServiceTime ;
            this.averageServiceTime = (e + client.gettService()) / 2 ;
        }
    }
    public AtomicInteger getPerioadaDeAsteptare() {
        return perioadaDeAsteptare;
    }
    public int decrementeazaPerioadaDeAsteptare(){
        return perioadaDeAsteptare.decrementAndGet() ;
    }
    public void setAverageWaitingTime(int averageWaitingTime) {
        this.averageWaitingTime = averageWaitingTime;
    }
    public void setAverageServiceTime(int averageServiceTime) {
        this.averageServiceTime = averageServiceTime;
    }
    public BlockingQueue<Clients> getClienti() {
        return clienti;
    }
    public void run() {
        while(true) {
            try {
                Thread.sleep(clienti.peek().gettService());
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            decrementeazaPerioadaDeAsteptare() ;
        }
    }
    public String toString() {
        return "Server{" +
                "clienti = " + clienti +
                ", perioadaDeAsteptare = " + perioadaDeAsteptare +
                ", averageWaitingTime = " + averageWaitingTime +
                ", averageServiceTime = " + averageServiceTime +
                '}';
    }
}
