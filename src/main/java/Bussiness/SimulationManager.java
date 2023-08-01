package Bussiness;

import Bussiness.ArivTimeComp;
import Bussiness.Program;
import Model.Clients;
import Model.Coada;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Collections;
public class SimulationManager implements Runnable {
    public int timeLimit;
    public int numberOfCozi;
    public int numberOfClients;
    public int minArivTime;
    public int maxArivTime;
    public int minServiceTime;
    public int maxServiceTime;
    public ArrayList<Integer> peekHour = new ArrayList<Integer>() ;
    private JTextArea rez;
    private Program p1 ;
    private List<Clients> generatedClienti = new ArrayList<>();
    public SimulationManager(int timeLimit,int minArivTime, int maxArivTime, int minServiceTime, int maxServiceTime, int numberOfClients, int numberOfCozi, JTextArea rez) {
        this.timeLimit = timeLimit ;
        this.rez = rez ;
        p1 = new Program(numberOfCozi);
        while (numberOfClients > 0){
            int idRandom = ThreadLocalRandom.current().nextInt(1, 1000 + 1);
            int tAr = ThreadLocalRandom.current().nextInt(minArivTime, maxArivTime + 1);
            int tS = ThreadLocalRandom.current().nextInt(minServiceTime, maxServiceTime + 1);
            Clients c = new Clients(idRandom,tAr,tS);
            generatedClienti.add(c);
            numberOfClients-- ;
        }
        Collections.sort(generatedClienti, new ArivTimeComp());
    }
    public void run() {
        int curTime = 1 ;
        while(curTime < timeLimit){
            List<Coada> cozi = p1.getCozi() ;
            for( int k = 0 ; k < p1.getNrCozi() ; k++ ){
                if(cozi.get(k).getClienti().isEmpty()){
                    ;
                }
                else{
                    if(cozi.get(k).getClienti().peek().gettService() == 0){
                        cozi.get(k).getClienti().poll();
                    }
                    else{
                        cozi.get(k).getClienti().peek().settService(cozi.get(k).getClienti().peek().gettService()-1);
                        cozi.get(k).decrementeazaPerioadaDeAsteptare() ;
                    }
                }
            }
            int count = 0;
            for(int i = 0 ; i < generatedClienti.size() ; i++ ) {
                if (generatedClienti.get(i).gettArrival() == curTime) {
                    int retine = generatedClienti.get(i).gettArrival();
                    count = 1;
                    for (int j = i + 1; j < generatedClienti.size(); j++) {
                        int retine2 = generatedClienti.get(j).gettArrival();
                        if (retine == retine2) {
                            count++;
                        } else {
                            j = generatedClienti.size();
                        }
                    }
                    for (int k = 0; k < count; k++) {
                        p1.dispatchTask(generatedClienti.get(0));
                        generatedClienti.remove(generatedClienti.get(0));
                    }
                    count = 0;
                }
                i = generatedClienti.size();
            }

            for(int i = 0 ; i < p1.getNrCozi() ; i++){
                if(cozi.get(i).getClienti().isEmpty()){
                    cozi.get(i).setAverageWaitingTime(0);
                    cozi.get(i).setAverageServiceTime(0);
                }
            }

            int max = p1.getPeekHour() ;
            peekHour.add(max);

            if(peekHour.size() == (timeLimit - 1)){
                int max2 = -1 ;
                int retine = -1 ;
                for(int i = 0 ; i < peekHour.size(); i++){
                    if(max2 < peekHour.get(i)){
                        max2 = peekHour.get(i);
                        retine = i ;
                    }
                }
                retine += 1 ;
                rez.append("PeekHour: " + max2 + " la timpul: " + retine );
            }

            rez.append("Time " + curTime + "\n");
            rez.append("Waiting clients: " + generatedClienti + "\n");
            rez.append("Queue " + p1.getCozi() + "\n");
            curTime++ ;
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
