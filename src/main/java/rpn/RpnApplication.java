package rpn;

import rpn.exception.RpnException;
import rpn.operators.*;

import java.util.*;

public class RpnApplication {
    public static void main(String [] args) throws RpnException {
        Scanner scanner = new Scanner(System.in);

        Map<String, Operator> operators = new HashMap<>();
        operators.put("+", new Addition());
        operators.put("-", new Subtraction());
        operators.put("*", new Multiplication());
        operators.put("/", new Division());
        operators.put("sqrt", new Sqrt());
        operators.put("undo", new Undo());
        operators.put("clear", new Clear());
        operators.put("push", new Push());
        operators.put("print", new SimpleConsolePrinter());

        RpnContext context = new RpnContext();

        while (scanner.hasNextLine()) {
            try {
                RpnExecutor.execute(context, operators, scanner.nextLine());
                RpnExecutor.execute(context, operators, "print");
            } catch (RpnException e) {
                System.out.println(e.getMessage());
                RpnExecutor.execute(context, operators, "print");

                if (e.remainingExpression != null && !e.remainingExpression.isEmpty()) {
                    System.out.println("(the " + e.remainingExpression + " were not pushed on to the stack due to the previous error)");
                }

            }
        }
    }
}
