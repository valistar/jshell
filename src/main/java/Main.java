import valistar.shell.Expression;
import valistar.shell.Parser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        Expression command;
        while(scanner.hasNextLine()) {
            input = scanner.nextLine();
            if(input.equals("exit")) { //TODO DEV
                break;
            }
            System.out.println("Our input:" + input);
            command = Parser.parse(input);

        }
    }
}
