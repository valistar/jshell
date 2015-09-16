package valistar.shell;

public class Expression implements ExpressionValue {
    private ExpressionValue lvalue;
    private Operator operator;
    private ExpressionValue rvalue;
    //TODO Make operator enum.

    public Expression(Action action) {

    }

    //TODO Should recursively validate before evalutating.

    public Expression(ExpressionValue lvalue, Operator operator, ExpressionValue rvalue) {
        this.lvalue = lvalue;
        this.operator = operator;
        this.rvalue = rvalue;
    }

    public String toString() {
        return String.format("(%s %s %s)", lvalue, operator, rvalue);
    }

    public String evaluate(ProcessBuilder pb) {
        String output;
        if(lvalue instanceof Expression) {
            output = ((Expression) lvalue).evaluate(pb);
        }
        else {
            output = ((Action) lvalue).execute();
        }

        if(operator != null) {
            switch(operator.toString()) {
                case "|":
                    //Bind output to rvalue stdin
                    break;
                case ">":
                    //Write output to file
                    break;
                case ">>":
                    //Append output to file
                    break;
                case "<":
                    //Bind lvalue to rvalue stdin
                    break;
            }
        }

        return output;

    }


}
