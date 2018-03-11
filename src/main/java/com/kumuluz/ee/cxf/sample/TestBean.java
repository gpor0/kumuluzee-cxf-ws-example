package com.kumuluz.ee.cxf.sample;

import javax.enterprise.context.ApplicationScoped;

/**
 * Just for injection test purpose.
 */
@ApplicationScoped
public class TestBean {

    public String getString() {
        return "CDI works!";
    }

}
