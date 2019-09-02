package rpn.operators;

import rpn.RpnContext;

import java.math.BigDecimal;


public class Undo implements Operator {
    @Override
    public void operate(RpnContext context, BigDecimal ... args) {
        context.resetBack(1);
    }
}

