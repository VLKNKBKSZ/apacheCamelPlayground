package com.vlkn.apachecamel.routers;

import com.vlkn.apachecamel.domain.AddressList;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jackson.JacksonDataFormat;
import org.springframework.stereotype.Component;


public class HttpRouter extends RouteBuilder {




    @Override
    public void configure() throws Exception {

        JacksonDataFormat jacksonDataFormat = new JacksonDataFormat(AddressList.class);
        jacksonDataFormat.setPrettyPrint(true);
        from("direct:https").routeId("https")
                .log(LoggingLevel.INFO, this.log,"first route message is printend and then this route is executed")
                .to("https://fakerapi.it/api/v1/addresses?_quantity=1")
                .unmarshal(jacksonDataFormat)
                .log(LoggingLevel.INFO, this.log,"deserialized data: ${body}")
                .marshal(jacksonDataFormat)
                .log(LoggingLevel.INFO, this.log,"Serializded data ${body}");
    }
}
