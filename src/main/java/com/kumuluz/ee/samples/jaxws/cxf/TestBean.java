package com.kumuluz.ee.samples.jaxws.cxf;

import javax.enterprise.context.RequestScoped;

/**
 * Just for injection test purpose.
 */
@RequestScoped
public class TestBean {

    public String getString() {
        return "CDI works!";
    }

}
