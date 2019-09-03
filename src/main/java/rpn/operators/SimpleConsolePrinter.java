package rpn.operators;

import rpn.RpnContext;

import java.math.BigDecimal;

public class SimpleConsolePrinter implements Operator {
    @Override
    public void operate(RpnContext context, BigDecimal... args) {

        String stack = context.getNumberStack().stream()
                .map(number -> number.setScale(10, BigDecimal.ROUND_HALF_EVEN).stripTrailingZeros().toPlainString())
                .reduce((a, b) -> a + " " + b)
                .orElse("");

        System.out.println("stack:" + stack);
    }
}
