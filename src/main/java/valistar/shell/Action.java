package valistar.shell;

public class Action implements ExpressionValue {

    private String action;

    public Action(String action) {
        this.action = action;
    }

    public String toString() {
        return action;
    }

    public String execute() {
        String output = ""; //TODO DEV

        return output;

    }

}
