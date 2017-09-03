package com.kumuluz.ee.cxf.sample;

import javax.enterprise.context.ApplicationScoped;

/**
 * Just for injection test purpose.
 */
@ApplicationScoped
public class TestBean {

    public String getString() {
        System.out.println("getString called!");
        return "It works!";
    }

}
