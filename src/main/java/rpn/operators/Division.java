package rpn.operators;

import rpn.RpnContext;
import rpn.exception.RpnException;

import java.math.BigDecimal;
import java.math.MathContext;

public class Division implements Operator {

    @Override
    public void operate(RpnContext context, BigDecimal ... args) throws RpnException {
        BigDecimal a = context.pop();
        BigDecimal b = context.pop();

        if (a.compareTo(BigDecimal.ZERO) == 0) {
            throw new RpnException("cannot divide by zero");
        }

        context.push(b.divide(a, MathContext.DECIMAL64));
    }
}
