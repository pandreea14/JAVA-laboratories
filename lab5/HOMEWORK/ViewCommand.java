package org.example;

public class ViewCommand implements Command{
    private final Repository repository;
    private final String documentName;

    public ViewCommand(Repository repository, String documentName) {
        this.repository = repository;
        this.documentName= documentName;
    }

    @Override
    public void execute() throws CommandExecutionException {
        try{
            repository.viewDocument(documentName);
        } catch (Exception e) {
            throw new CommandExecutionException("error viewing document: "+e.getMessage());
        }
    }
}
