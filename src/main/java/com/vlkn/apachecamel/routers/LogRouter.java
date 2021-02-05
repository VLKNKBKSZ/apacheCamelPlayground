package com.vlkn.apachecamel.routers;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class LogRouter extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:vlkn?period=500000")
                .routeGroup("vlkn-timer")
                .log(LoggingLevel.INFO, this.log,"first route in apache camel")
                .to("direct:https");
    }
}
