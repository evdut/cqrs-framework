package net.evdut.cqrs.framework.security;

import javax.annotation.Resource;
import javax.ejb.EJBAccessException;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

@RolesAllowed
@Interceptor
public class RolesAllowedInterceptor {

    @Resource
    SessionContext sc;

    @AroundInvoke
    public Object invoke(InvocationContext context) throws Exception {
        RolesAllowed ra = context.getMethod().getAnnotation(RolesAllowed.class);

        if (!hasRole(ra.value())) {
            throw new EJBAccessException("Client not authorized for this invocation");
        }

        return context.proceed();

    }

    private boolean hasRole(String[] roles) {
        for (String role : roles) {
            if (sc.isCallerInRole(role)) {
                return true;
            }
        }

        return false;
    }

}
