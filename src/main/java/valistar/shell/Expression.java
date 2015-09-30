package valistar.shell;

public class Expression implements ExpressionValue {
    private ExpressionValue lvalue;
    private Operator operator;
    private ExpressionValue rvalue;
    //TODO Make operator enum.

    public Expression(Action action) {
        this.lvalue = action;

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
            if(operator.is(Operator.Operators.PIPE)) {
                //Bind output to rvalue stdin
            }
            else if(operator.is(Operator.Operators.INPUT)) {
                //Write output to file
            }
            else if(operator.is(Operator.Operators.OUTPUT)) {
                //Append output to file
            }
            else if(operator.is(Operator.Operators.APPEND)) {
                //Bind lvalue to rvalue stdin
            }
        }

        return output;

    }


}
