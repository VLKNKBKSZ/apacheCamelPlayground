package com.vlkn.apachecamel.routers;

import com.vlkn.apachecamel.domain.Address;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class BeanValidateRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        Address address = Address.builder()
                .streetName("Kneza Milosa")
                .buildingNumber(1)
                .city("Belgrade")
                .zipcode(11000)
                .country("Serbia")
                .build();

        from("timer:vlkn?period=100000")
                .setBody().constant(address)
                .to("bean-validator:labelCanBeAnyName"); // apache is figuring out the actual class when you set the body
                                                        // bean validator will always be called upon the body.
                                                        // the label is just anyname you think is usefull

    }
}
