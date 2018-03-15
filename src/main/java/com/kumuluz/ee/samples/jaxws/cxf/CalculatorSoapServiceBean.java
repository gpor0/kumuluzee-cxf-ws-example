package com.kumuluz.ee.samples.jaxws.cxf;


import com.kumuluz.ee.jaxws.cxf.annotations.WsContext;
import com.kumuluz.ee.samples.jaxws.cxf.interceptors.WsInterceptor;
import com.kumuluz.ee.samples.jaxws.cxf.jpa.Customer;
import com.kumuluz.ee.samples.jaxws.cxf.jpa.CustomerService;
import https.github_com.gpor89.soap.sample.CalculateSum;
import https.github_com.gpor89.soap.sample.CalculateSumResponse;
import https.github_com.gpor89.soap.sample.Calculator;
import org.apache.cxf.annotations.SchemaValidation;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.jws.HandlerChain;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import java.util.UUID;

@WsContext(contextRoot = "/soap", urlPattern = "/calculator")
@ApplicationScoped
@WebService(serviceName = "Calculator", portName = "CalculatorPort", targetNamespace = "https://github.com/gpor89/soap/sample",
        endpointInterface = "https.github_com.gpor89.soap.sample.Calculator", wsdlLocation = "/webapp/WEB-INF/wsdls/calculatorSample.wsdl")
@SchemaValidation
@Interceptors(WsInterceptor.class)
@HandlerChain(file = "/META-INF/handler-chains.xml")
public class CalculatorSoapServiceBean implements Calculator {

    @Inject
    private TestBean testBean;

    @Inject
    private CustomerService customerService;

    @Resource(name = "FOO-BAR")
    private WebServiceContext webServiceContext;


    @Override
    public CalculateSumResponse calculateSum(CalculateSum parameters) {

        CalculateSumResponse response = new CalculateSumResponse();
        response.setResult(parameters.getX() + parameters.getY());

        //should not be null
        System.out.println(testBean == null ? null : testBean.getString());

        Customer c = createCustomer();
        customerService.saveCustomer(c);
        Customer customer = customerService.getCustomer(c.getId());
        System.out.print("customer: " + customer);

        Object soapAction = webServiceContext.getMessageContext().get("SOAPAction");

        if (soapAction != null) {
            System.out.println("webServiceContext works!");
        }


        return response;
    }

    private Customer createCustomer() {
        Customer c = new Customer();

        c.setId(UUID.randomUUID().toString().substring(0,10));
        c.setFirstName("John");
        c.setLastName("Smith");

        return c;
    }

}
