package Bussiness;

import Model.Clients;
import Model.Coada;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
public class Program {
    private List<Coada> cozi = new LinkedList<>() ;
    private int nrCozi;
    public Program(int nrCozi) {
        for (int i = 0; i < nrCozi; i++) {
            Coada c1 = new Coada() ;
            cozi.add(i, c1);
            Thread thread1 = new Thread(c1);
            thread1.start();
        }
        this.nrCozi = nrCozi ;
    }
    public void dispatchTask(Clients client){
        List<Coada> retineCozi = new LinkedList<>();
        retineCozi = getCozi() ;
        int min = 999, retineCoada = -1 ;
        for(int i = 0 ; i < retineCozi.size() ; i++ ){
            AtomicInteger minimCoada = retineCozi.get(i).getPerioadaDeAsteptare() ;
            if(minimCoada.get() < min){
                min = minimCoada.get() ;
                retineCoada = i ;
            }
        }
        retineCozi.get(retineCoada).addClient(client);
    }
    public List<Coada> getCozi() {
        return cozi;
    }
    public int getNrCozi() {
        return nrCozi;
    }
    public int getPeekHour(){
        int max = -1 ;
        for(int i = 0 ; i < cozi.size() ; i++ ){
            if(max < cozi.get(i).getPerioadaDeAsteptare().get()){
                max = cozi.get(i).getPerioadaDeAsteptare().get() ;
            }
        }
        return max ;
    }
    public String toString() {
        return "Bussiness.Program{" +
                "cozi = " + cozi +
                '}';
    }
}



