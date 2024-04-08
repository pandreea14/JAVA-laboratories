package org.example;

public class ReportCommand implements Command {

    private final Repository repository;
    //private final String fileName;


    public ReportCommand(Repository repository) {
        this.repository = repository;
        //this.fileName = fileName;
    }

    @Override
    public void execute() throws CommandExecutionException {
        try {
            repository.generateReport();
        } catch (Exception e) {
            throw new CommandExecutionException("Error generating report: " + e.getMessage());
        }
    }
}
