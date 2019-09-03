package rpn.operators;

import rpn.RpnContext;
import rpn.exception.RpnException;

import java.math.BigDecimal;

public interface Operator {
    void operate(RpnContext context, BigDecimal ... args) throws RpnException;
}
