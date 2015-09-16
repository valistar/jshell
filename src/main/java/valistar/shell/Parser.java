package valistar.shell;

import java.util.ArrayList;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    //Doesnt work with escape characters.
    //Doesn't work if a token is just whitespace. Not sure if that ever should be allowed, though.
    //Doesn't work for input with 0 operators. Because of empty overloaded constructor, I think.
    //Should probably break apart on ';' and return an array of expressions or something.

    public static Expression parse(String input) {
        ArrayList<String> tokens = new ArrayList<String>();
        String currentToken = "";
        char currentChar;
        boolean enQuote = false;
        boolean enOperator = false;
        for (int i = 0; i < input.length(); i++) {
            currentChar = input.charAt(i);
            //Don't check if stuff inside quotes are operators.
            if(currentChar == '"' || currentChar == '\'') {
                enQuote = !enQuote;
            }
            if(enQuote) {
                currentToken += currentChar;
            }
            else {
                //If this character is an operator, we'll split our string around here.
                if(Operator.characterList.contains(currentChar)) {
                    if(enOperator) { //Some operators can be 2+ characters long...
                        currentToken += currentChar;
                    }
                    else { //...but this one isn't, so split input here
                        if(currentToken.trim().length() > 0) {
                            tokens.add(currentToken.trim());
                        }
                        else if(tokens.size() == 0) {
                            tokens.add(""); //If we start with an operator, we want an empty string lvalue
                        }
                        currentToken = Character.toString(currentChar);
                        enOperator = true;
                    }
                }
                else if(enOperator) { //Last char was an operator, split input here
                    if(currentToken.trim().length() > 0) {
                        tokens.add(currentToken.trim());
                    }
                    currentToken = Character.toString(currentChar);
                    enOperator = false;
                }
                else {
                    currentToken += currentChar;
                }
            }
        }
        if(currentToken.trim().length() > 0) {
            tokens.add(currentToken.trim());
        }

        //System.out.println("Token Array");
        ArrayList<ExpressionValue> current = new ArrayList<ExpressionValue>();
        ArrayList<ExpressionValue> next = new ArrayList<ExpressionValue>();
        //Efficiency be damned.
        for (ListIterator<String> iterator = tokens.listIterator(); iterator.hasNext();) {
            String token = iterator.next();
            if(iterator.nextIndex() % 2 == 0) {
                current.add(new Operator(token.trim()));
            }
            else {
                current.add(new Action(token.trim()));
            }
        }

        for(int i = current.size() - 1; i >=0; i--) {
            //We're at a value, the next item is a right associative operator, and the item after that exists (and is a value)
            if(i % 2 == 0 && i - 2 >= 0 && ((Operator)(current.get(i-1))).getAssociativity() == Operator.Associativity.RIGHT) {
                next.add(0, new Expression(current.get(i-2), (Operator)current.get(i-1), current.get(i)));
                i = i - 2;
            }
            else {
                next.add(0, current.get(i));
            }
        }

        current = next; //Lets see how java handles references, this might not work.
        next = new ArrayList<ExpressionValue>();


        System.out.println("Do While!");
        do {
            if(current.size() > 1) {
                System.out.println(String.format("New Express: %s | %s | %s", current.get(0), current.get(1), current.get(2)));
                current.set(0, new Expression(current.get(0), (Operator)current.get(1), current.get(2)));
                current.remove(1);
                current.remove(1);
            }
            else {
                current.set(0, new Expression((Action)current.get(0))); //TODO Check.
            }

        } while(current.size() > 1);

        System.out.println("Expressions!");
        for(ExpressionValue express : current) {
            System.out.println(express.toString());
        }
        return (Expression)current.get(0);

    }
}