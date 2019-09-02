package rpn.operators;

import rpn.RpnContext;

import java.math.BigDecimal;
import java.math.MathContext;

public class Division implements Operator {

    @Override
    public void operate(RpnContext context, BigDecimal ... args) {
        BigDecimal a = context.pop();
        BigDecimal b = context.pop();
        context.push(b.divide(a, MathContext.DECIMAL64));
    }
}
