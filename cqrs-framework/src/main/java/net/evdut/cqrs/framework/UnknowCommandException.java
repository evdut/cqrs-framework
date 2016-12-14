package net.evdut.cqrs.framework;

import javax.ejb.ApplicationException;

@ApplicationException(rollback = true)
public class UnknowCommandException extends RuntimeException {

    
    
}
