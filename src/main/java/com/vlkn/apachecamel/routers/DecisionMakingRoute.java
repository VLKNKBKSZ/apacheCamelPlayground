package com.vlkn.apachecamel.routers;

import org.apache.camel.Predicate;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class DecisionMakingRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {

        Predicate isOne = header("dummy").isEqualTo(1);

        from("timer:vlkn?period=10000")
                .id("DecisionMakingRoute")
                .setHeader("dummy").constant(1)
                .choice()
                .when(isOne)
                .log("FirstCondition")
                .to("direct:test")
                .when(header("dummy").isEqualTo(2))
                .log("SecondCondition")
                .otherwise()
                .log("OTHERWISE")
                .endChoice();

        from("direct:test")
                .log("ANOTHER ROUTE");
    }
}
