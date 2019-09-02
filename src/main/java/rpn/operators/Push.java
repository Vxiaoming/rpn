package rpn.operators;

import rpn.RpnContext;

import java.math.BigDecimal;

public class Push implements Operator {
    @Override
    public void operate(RpnContext context, BigDecimal... args) {
        context.push(args[0]);
    }
}
