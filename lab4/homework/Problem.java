package org.example;

import java.util.*;

public class Problem {
    List<Person> drivers = new ArrayList<>();
    TreeSet<Person> passangers = new TreeSet<>();

    public Problem(List<Person> drivers, TreeSet<Person> passangers) {
        this.drivers = drivers;
        this.passangers = passangers;
    }

    public void greedyAlgorithm() {
        Map<String, Boolean> hasDriver = new HashMap<>(); //ce soferi au fost luati

        List<Pair> pair = new ArrayList<>();

        for(Person driver : drivers) {
            System.out.print("Driver name: " + driver.getFirstName()); //selectat sofer
            int assign = 0;
            for(Person passanger : passangers) {
                int index1 = -1;
                int index2 = -1;

                // verifica daca unul din stopuri se afla intre locatiile prin care trece soferul
                if (driver.getStops().containsKey(passanger.getListPassanger().getFirst())) {
                    index1 = driver.getId(passanger.getListPassanger().getFirst());
                }

                if (driver.getStops().containsKey(passanger.getListPassanger().getLast())) {
                    index2 = driver.getId(passanger.getListPassanger().getLast());
                }
                //add pass pe dist ind1 si ind2 daca indecsii locatiilor sunt valizi si consecutivi in drumul soferului
                if (index1!=-1 && index2!=-1 && index1<index2) {
                    assign = 1;
                    pair.add(new Pair(passanger.getFirstName(), index1, index2));
                    System.out.println(" -> with passanger " + passanger.getFirstName() + passanger.getLastName());
                }
            }
            if(assign == 0) {
                System.out.println(" -> Couldn't assign passanger to this driver");
            }
            int currentpoz=0;
            while(true) {
                int highest = 10000000;
                int leaving = 0;
                String name = null;
                for (Pair par:pair) {
                    if (par.indexLocation2<highest && par.indexLocation1>=currentpoz && !hasDriver.containsKey(par.name)) {
                        highest = par.indexLocation2;
                        leaving = par.indexLocation1;
                        name = par.name;
                    }
                }

                if (highest == 10000000) {
                    break;
                } else {
                    hasDriver.put(name, true);
                    System.out.println(name + " leaving from " + leaving + " and the destination is " + highest);
                    currentpoz = highest;
                }
            }

        }
    }
}
