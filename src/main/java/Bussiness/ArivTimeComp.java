package Bussiness;

import Model.Clients;

import java.util.Comparator;
public class ArivTimeComp implements Comparator<Clients>{
    public int compare(Clients c1, Clients c2) {
        return c1.gettArrival() - c2.gettArrival() ;
    }
}
