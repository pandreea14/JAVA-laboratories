package org.example;

public class ExportCommand implements Command {
    private final Repository repository;
    private final String fileName;

    public ExportCommand(Repository repository, String fileName) {
        this.repository = repository;
        this.fileName = fileName;
    }

    @Override
    public void execute() throws CommandExecutionException {
        try {
            repository.exportToJson(fileName);
        } catch (Exception e) {
            throw new CommandExecutionException("Error exporting document: " + e.getMessage());
        }
    }
}
