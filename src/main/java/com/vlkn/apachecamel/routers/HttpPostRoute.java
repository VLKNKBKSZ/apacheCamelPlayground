package com.vlkn.apachecamel.routers;

import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.http.protocol.HTTP;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.apache.camel.Exchange.HTTP_METHOD;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;

@Component
@Slf4j
public class HttpPostRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("timer:vlkn?period=50000") //So this route sets up a timer which is trigger every 5 seconds,
                .setHeader(HTTP_METHOD)     // adds header to the message
                .constant(GET)
                .log("HTTP METHOD: ${header" + HTTP_METHOD + "}")
                .process(exchange -> { // passes the message to a built in processor
                    log.info("WE ARE IN THE PROCESSOR");
                    exchange.getIn().setHeader(HTTP_METHOD, POST);  //changes the header from get to post
                    exchange.getIn().setHeader("sjaak", "jansen");    // adds a dummy header
                    exchange.getIn().setBody("PAYLOADDDDDDD"); // adds dummy payload to the message.
                })
                .log("HttpMethod :${header." + HTTP_METHOD+ "}")
                .to("https://camelprocessor.free.beeceptor.com/my/api/path");// makes a post request to the configured endpoint
    }
}
