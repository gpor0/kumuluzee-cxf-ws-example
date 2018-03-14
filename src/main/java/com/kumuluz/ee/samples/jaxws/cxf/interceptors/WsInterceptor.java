package com.kumuluz.ee.samples.jaxws.cxf.interceptors;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.xml.ws.WebServiceContext;

public class WsInterceptor {

    @Resource(name = "FOO-BAR")
    private WebServiceContext webServiceContext;

    @AroundInvoke
    public Object intercept(InvocationContext context) throws Exception {

        Object soapAction = webServiceContext.getMessageContext().get("SOAPAction");

        if (soapAction != null) {
            System.out.println("WS interceptor works!");
        }

        System.out.print("");
        return context.proceed();
    }
}
