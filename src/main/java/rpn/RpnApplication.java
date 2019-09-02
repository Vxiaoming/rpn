package rpn;

import rpn.operators.*;

import java.util.*;

public class RpnApplication {
    public static void main(String [] args) {
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

        RpnContext context = new RpnContext();

        while (scanner.hasNextLine()) {
            String [] inputs = scanner.nextLine().trim().split(" ");
            RpnController.evaluate(context, operators, inputs);
        }
    }
}
