package rpn.operators;

import rpn.RpnContext;
import rpn.exception.RpnException;

import java.math.BigDecimal;


public class Undo implements Operator {
    @Override
    public void operate(RpnContext context, BigDecimal ... args) throws RpnException {
        if (context.getLog().size() <= 0) {
            throw new RpnException("already reached at the very beginning, cannot undo anymore");
        }

        context.resetBack(1);
    }
}

