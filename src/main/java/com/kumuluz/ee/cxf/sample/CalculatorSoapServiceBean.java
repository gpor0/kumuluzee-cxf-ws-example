package com.kumuluz.ee.cxf.sample;


import https.github_com.gpor89.soap.sample.CalculateSum;
import https.github_com.gpor89.soap.sample.CalculateSumResponse;
import https.github_com.gpor89.soap.sample.Calculator;
import org.apache.cxf.annotations.SchemaValidation;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import java.util.ServiceLoader;

@ApplicationScoped
@WebService(serviceName = "Calculator", portName = "CalculatorPort", targetNamespace = "https://github.com/gpor89/soap/sample",
        endpointInterface = "https.github_com.gpor89.soap.sample.Calculator")
@SchemaValidation
public class CalculatorSoapServiceBean implements Calculator {

    @Inject
    private TestBean testBean;

    @Resource(name = "FOO-BAR")
    private WebServiceContext webServiceContext;


    @Override
    public CalculateSumResponse calculateSum(CalculateSum parameters) {

        CalculateSumResponse response = new CalculateSumResponse();
        response.setResult(parameters.getX() + parameters.getY());

        //should not be null
         System.out.println(testBean == null ? null : testBean.getString());

        Object soapAction = webServiceContext.getMessageContext().get("SOAPAction");

        if (soapAction != null) {
            System.out.println("webServiceContext works!");
        }


        return response;
    }

}
