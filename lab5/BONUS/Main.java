package org.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Main {
    //am folosit Set de String pentru ca poate avea mai multe abilitati o persoana
    private Map<String, Set<String>> personAbilitiesMap;
    public Main() {
        personAbilitiesMap = new HashMap<>();
    }

    public void readAbilitiesFromExcel(String excelFilePath) throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // date pe prima fila

        for (Row row : sheet) {
            Cell personCell = row.getCell(0); // numele e prima coloana
            Cell abilitiesCell = row.getCell(1); // abilitati in a doua coloana

            String personName = personCell.getStringCellValue();
            String[] abilities = abilitiesCell.getStringCellValue().split(","); // abilitati separate prin ,

            Set<String> personAbilities = new HashSet<>(Arrays.asList(abilities));
            personAbilitiesMap.put(personName, personAbilities);
        }

        workbook.close();
        inputStream.close();
    }

    public List<Set<String>> determineMaximalGroups() {
        List<Set<String>> maximalGroups = new ArrayList<>();
        Map<String, Set<String>> graph = new HashMap<>();

        // graph
        for (String person1 : personAbilitiesMap.keySet()) {
            graph.put(person1, new HashSet<>()); // empty set pentru vecini
            for (String person2 : personAbilitiesMap.keySet()) {
                if (person1.equals(person2)) continue; // sare peste el insusi
                Set<String> abilities1 = personAbilitiesMap.get(person1);
                Set<String> abilities2 = personAbilitiesMap.get(person2);
                if (haveCommonAbilities(abilities1, abilities2)) {
                    graph.get(person1).add(person2); // daca au abilitati comune se adauga muchie intre ei
                }
            }
        }

        // cauta componente conexe in graf (persoane cu abilitati comune conectati ianinte)
        Set<String> visited = new HashSet<>();
        for (String person : personAbilitiesMap.keySet()) {
            if (!visited.contains(person)) {
                Set<String> connectedComponent = new HashSet<>();
                dfs(person, graph, visited, connectedComponent);
                maximalGroups.add(connectedComponent);
            }
        }

        return maximalGroups;
    }

    private boolean haveCommonAbilities(Set<String> abilities1, Set<String> abilities2) {
        for (String ability : abilities1) {
            if (abilities2.contains(ability)) {
                return true;
            }
        }
        return false;
    }

    private void dfs(String person, Map<String, Set<String>> graph, Set<String> visited, Set<String> connectedComponent) {
        visited.add(person);
        connectedComponent.add(person);
        for (String neighbor : graph.get(person)) {
            if (!visited.contains(neighbor)) {
                dfs(neighbor, graph, visited, connectedComponent);
            }
        }
    }

    public static void main(String[] args) {
        try {
            Main analyzer = new Main();
            analyzer.readAbilitiesFromExcel("M:\\FACULTATE\\ANUL2\\SEM2\\JAVA\\lab5\\Book1.xlsx");
            List<Set<String>> maximalGroups = analyzer.determineMaximalGroups();
            int groupNumber = 1;
            for (Set<String> group : maximalGroups) {
                System.out.println("Group " + groupNumber++ + ": " + group);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
