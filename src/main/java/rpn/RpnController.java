package rpn;

import rpn.operators.Operator;
import rpn.operators.Undo;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Map;
import java.util.Stack;

public class RpnController {

    public static void evaluate(RpnContext rpnContext, Map<String, Operator> operators, String ... inputs) {
        for (int i = 0; i< inputs.length; i++) {
            String numberOrOp = inputs[i];
            try {
                if (operators.containsKey(numberOrOp)) {
                    operate(rpnContext, operators.get(numberOrOp));
                } else {
                    BigDecimal [] paras = new BigDecimal[1];
                    paras[0] = new BigDecimal(numberOrOp);
                    operate(rpnContext, operators.get("push"), paras);
                }
            } catch (NumberFormatException e) {
                System.out.println("operator <" + numberOrOp + "> (position: <" + (2*i + 1) + ">): is not supported");
                break;
            } catch (EmptyStackException e) {
                System.out.println("operator <" + numberOrOp + "> (position: <" + (2*i + 1) + ">): insufficient parameters");
                break;
            }
        }
        System.out.println("stack: " + Arrays.toString(rpnContext.getNumberStack().toArray()).replace(",", "").replace("[", "").replace("]", ""));
    }

    private static void operate(RpnContext rpnContext, Operator operator, BigDecimal... args) {
        if (! (operator instanceof Undo)) {
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
