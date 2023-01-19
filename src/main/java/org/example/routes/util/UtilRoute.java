package org.example.routes.util;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UtilRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {

        from("direct:setId")
                .routeId("set-id")
                .setHeader("req-id", simple(UUID.randomUUID().toString()));

        from("direct:logRequest")
                .routeId("log-request")
                .log(LoggingLevel.INFO, "request: ${body}");
    }
}
