package com.kumuluz.ee.cxf.sample;

import https.github_com.gpor89.soap.example.Calculator;
import org.apache.cxf.annotations.SchemaValidation;
import org.apache.cxf.feature.validation.DefaultSchemaValidationTypeProvider;
import org.apache.cxf.feature.validation.SchemaValidationFeature;
import org.apache.cxf.jaxws.JaxWsServerFactoryBean;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.xml.namespace.QName;
import java.util.HashMap;
import java.util.Map;

/**
 * imho this logic should be part of kumuluz framework.
 */
public class NonSpringServlet extends CXFNonSpringServlet {

    private static final long serialVersionUID = 1L;

    @Inject
    private Calculator calculator;

    @Override
    protected void loadBus(ServletConfig sc) {
        super.loadBus(sc);

        createFactoryBean();
    }

    private void createFactoryBean() {
        JaxWsServerFactoryBean fb = new JaxWsServerFactoryBean();
        fb.setWsdlLocation("/webapp/WEB-INF/wsdls/calculatorSample.wsdl");
        fb.setAddress("/soap");
        fb.setServiceBean(calculator);
        fb.setServiceClass(Calculator.class);
        fb.setServiceName(new QName("https://github.com/gpor89/soap/example", "Calculator"));
        Map map = new HashMap<>();

        //todo manually read annotation from bean and set features.
        //todo injection of context???
        map.put("calculateSum", SchemaValidation.SchemaValidationType.BOTH);

        fb.getFeatures().add(new SchemaValidationFeature(new DefaultSchemaValidationTypeProvider(map)));
        fb.create();
    }
}
