package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.awt.*;
import java.io.*;

public class Repository {
    private final File masterDirectory;

    public Repository(String masterDirectory) {
        this.masterDirectory = new File(masterDirectory);
    }

    public void displayContent() {
        File[] files = masterDirectory.listFiles();
        if (files != null) {
            System.out.println(masterDirectory.getName() + ":");
            for (File file : files) {
                System.out.println("\t" + file.getName());
            }
        } else {
            System.out.println("the masterDirectory is empty");
        }
    }

    public void viewDocument(String documentName) throws IOException {
        File document = new File(masterDirectory, documentName);
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (document.exists()) {
                desktop.open(document);
            } else {
                throw new FileNotFoundException("document not found: " + documentName);
            }
        } else {
            throw new UnsupportedOperationException("impossible operation");
        }
    }

    public void generateReport() throws IOException {
        File reportFile = new File(masterDirectory, "report.html");
        try (PrintWriter writer = new PrintWriter(new FileWriter(reportFile))) {
            writer.println("<html><head><title>Repository Report</title></head><body>");
            writer.println("<h1>Repository Report</h1>");
            writer.println("<ul>");
            for (File file : masterDirectory.listFiles()) {
                writer.println("<li>" + file.getName() + "</li>");
            }
            writer.println("</ul>");
            writer.println("</body></html>");
        }
    }

    public void exportToJson(String fileName) throws IOException {
        File jsonFile = new File(masterDirectory, fileName + ".json");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(jsonFile, masterDirectory.listFiles());
    }
}
