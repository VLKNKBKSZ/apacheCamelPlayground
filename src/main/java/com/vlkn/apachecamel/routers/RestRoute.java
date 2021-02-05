package com.vlkn.apachecamel.routers;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class RestRoute extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("netty-http")
                .host("localhost")
                .port(8081)
                .dataFormatProperty("prettyPrint","true")
                .contextPath("/")
                .apiContextPath("api-doc")
                .apiProperty("api.title","USER API")
                .apiProperty("api.version","v1")
                .apiProperty("cors","true")
                .bindingMode(RestBindingMode.auto);

        rest("/say")
                .consumes("text/plain")
                .produces("text/plain")
                    .get("/hello")
                    .description("GET CALL")
                    .to("direct:hello")
                    .get("/bye")
                    .consumes("application/json")
                    .to("direct:bye")
                    .post("/bye")
                    .to("mock:update");

        from("direct:hello")
                .transform().constant("Hello World");
        from("direct:bye")
                .transform().constant("Bye World");
    }
}
