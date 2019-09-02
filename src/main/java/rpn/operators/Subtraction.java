package rpn.operators;

import rpn.RpnContext;

import java.math.BigDecimal;

public class Subtraction implements Operator {
    @Override
    public void operate(RpnContext context, BigDecimal ... args) {
        BigDecimal a = context.pop();
        BigDecimal b = context.pop();
        context.push(b.subtract(a));
    }
}
