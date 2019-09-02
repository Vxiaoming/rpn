package rpn.operators;

import rpn.RpnContext;

import java.math.BigDecimal;

public class Addition implements Operator {

    @Override
    public void operate(RpnContext context, BigDecimal ... args) {
        BigDecimal a = context.pop();
        BigDecimal b = context.pop();
        context.push(a.add(b));
    }
}
