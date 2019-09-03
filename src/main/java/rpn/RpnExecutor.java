package rpn;

import rpn.exception.RpnException;
import rpn.operators.Operator;
import rpn.operators.SimpleConsolePrinter;
import rpn.operators.Undo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Map;
import java.util.Stack;

public class RpnExecutor {

    public static void execute(RpnContext rpnContext, Map<String, Operator> operators, String expression) throws RpnException {
        String [] inputs = expression.trim().split(" ");
        String numberOrOp = "";
        int position = 0;
        try {
            for (int i = 0; i< inputs.length; i++) {
                numberOrOp= inputs[i];
                position = i;

                if (operators.containsKey(numberOrOp)) {
                    operate(rpnContext, operators.get(numberOrOp));
                } else {
                    BigDecimal [] paras = new BigDecimal[1];
                    paras[0] = new BigDecimal(numberOrOp);
                    operate(rpnContext, operators.get("push"), paras);
                }
            }
        } catch (NumberFormatException e) {
            throw new RpnException(
                    "operator <" + numberOrOp + "> (position: <" + (2*position + 1) + ">): is not supported",
                    Arrays.asList(inputs).subList(position + 1, inputs.length).stream().reduce((a, b) -> a + " " + b).orElse("")
            );
        } catch (EmptyStackException e) {
            throw new RpnException(
                    "operator <" + numberOrOp + "> (position: <" + (2*position + 1) + ">): insufficient parameters",
                    Arrays.asList(inputs).subList(position + 1, inputs.length).stream().reduce((a, b) -> a + " " + b).orElse("")
            );
        }
    }

    private static void operate(RpnContext rpnContext, Operator operator, BigDecimal... args) {
        if (! (operator instanceof Undo || operator instanceof SimpleConsolePrinter)) {
            rpnContext.getLog().push((Stack<BigDecimal>) rpnContext.getNumberStack().clone());
        }

        try {
            operator.operate(rpnContext, args);
        } catch (EmptyStackException e) {
            if (rpnContext.getLog().size() > 0) {
                rpnContext.resetBack(1);
            }

            throw e;
        }
    }

}
