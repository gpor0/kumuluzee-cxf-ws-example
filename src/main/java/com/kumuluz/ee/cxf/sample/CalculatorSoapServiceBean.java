package com.kumuluz.ee.cxf.sample;


import https.github_com.gpor89.soap.example.CalculateSum;
import https.github_com.gpor89.soap.example.CalculateSumResponse;
import https.github_com.gpor89.soap.example.Calculator;
import org.apache.cxf.annotations.SchemaValidation;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.jws.WebService;

@ApplicationScoped
@WebService(serviceName = "Calculator", portName = "CalculatorPort", targetNamespace = "https://github.com/gpor89/soap/example",
        endpointInterface = "https.github_com.gpor89.soap.example.Calculator")
@SchemaValidation //this is not working automatically
public class CalculatorSoapServiceBean implements Calculator {

    @Inject
    private TestBean testBean;


    @Override
    public CalculateSumResponse calculateSum(CalculateSum parameters) {

        CalculateSumResponse response = new CalculateSumResponse();
        response.setResult(parameters.getX() + parameters.getY());

        //should not be null
        System.out.println(testBean == null ? null : testBean.getString());

        return response;
    }

}
