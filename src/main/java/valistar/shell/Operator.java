package valistar.shell;

import java.util.Arrays;
import java.util.List;

public class Operator implements ExpressionValue {

    public enum Associativity {
        LEFT, //Associative operators are treated as left, for simplicity
        RIGHT,
        UNARY //Always left unary.
    }

    public enum Operators {
        AND,
        OR,
        TERMINATOR,
        BACKGROUND,
        PIPE,
        INPUT,
        OUTPUT,
        APPEND
    }
    static public List<Character> characterList = Arrays.asList('&', '|', ';', '<', '>');

    private Operators operator;
    private Associativity associativity;


    public Operator(String operator) {
        switch(operator) {
            case "&&":
                this.operator = Operators.AND;
                this.associativity = Associativity.LEFT;
                break;
            case "||":
                this.operator = Operators.OR;
                this.associativity = Associativity.LEFT;
                break;
            case ";":
                this.operator = Operators.TERMINATOR;
                this.associativity = Associativity.UNARY;
                break;
            case "&":
                this.operator = Operators.BACKGROUND;
                this.associativity = Associativity.UNARY;
                break;
            case "|":
                this.operator = Operators.PIPE;
                this.associativity = Associativity.LEFT;
                break;
            case "<":
                this.operator = Operators.INPUT;
                this.associativity = Associativity.RIGHT;
                break;
            case ">":
                this.operator = Operators.OUTPUT;
                this.associativity = Associativity.LEFT;
                break;
            case ">>":
                this.operator = Operators.APPEND;
                this.associativity = Associativity.LEFT;
                break;
            default:
                //TODO Throw
        }
    }

    public boolean is(Operator.Operators operator) {
        return this.operator == operator;
    }

    public Associativity getAssociativity() {
        return associativity;
    }
}
