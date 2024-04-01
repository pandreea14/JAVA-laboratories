package org.example;

import org.graph4j.Graph;
import org.graph4j.GraphBuilder;
import org.graph4j.alg.matching.HopcroftKarpMaximumMatching;
import org.graph4j.util.Matching;

import java.util.*;

public class GraphAlgorithm {
    List<Person> driver = new ArrayList<>();
    Set<Person> passengers = new TreeSet<>();
    Graph g = GraphBuilder.empty().buildGraph();


    GraphAlgorithm(List<Person> driver, Set<Person> passengers, Graph g) {
        this.driver = driver;
        this.passengers = passengers;
        this.g = g;
        algGraph4J();
        //outputGraph();
    }

    public void algGraph4J() {

        for (Person driver : driver) {
            for (Person passenger : passengers) {
                int index1 = -1;
                int index2 = -1;

                if (driver.getStops().containsKey(passenger.getCurrentAndDestination().get(0))) {
                    index1 = driver.getId(passenger.getCurrentAndDestination().get(0));
                }

                if (driver.getStops().containsKey(passenger.getCurrentAndDestination().get(1))) {
                    index2 = driver.getId(passenger.getCurrentAndDestination().get(1));
                }

                //se adauga muchie intre driver si passanger daca se potrivesc rutele lor
                if (index1 != -1 && index2 != -1 & index1 <= index2) {
                    g.addEdge(driver.getMyNumber(), passenger.getMyNumber());
                }
            }
        }

        HopcroftKarpMaximumMatching alg = new HopcroftKarpMaximumMatching(g);

        Matching m = alg.getMatching();
        int[][] matchingEdges = new int[5000][2];
        matchingEdges = m.edges();

        //afisarea perechilor de soferi-pasageri care se potrivesc in functie de punctul de pornire si destinatie
        for(int i = 0 ; i < m.edges().length; i++){
            System.out.println("edge : " + matchingEdges[i][0] + " -> " + matchingEdges[i][1]);
        }
    }

    public void outputGraph(){
        for (int i = 0; i < g.vertices().length; i++) {
            System.out.println(Arrays.toString(g.edgesOf(i)));

        }
    }
}
