package org.example.routes.util;

import org.apache.camel.LoggingLevel;
import org.apache.camel.model.RouteDefinition;
import org.springframework.stereotype.Component;

@Component
public class RouteBuilder2 extends AbstractRouteBuilder {
    @Override
    protected String getFrom() {
        return "direct:activate2";
    }

    @Override
    protected String getRouteId() {
        return "activate-2";
    }

    @Override
    protected void extendRoute(RouteDefinition route) {
        route
                .log(LoggingLevel.INFO, "Activate 2 here")
                .to("direct:activate2-1");

        from("direct:activate2-1")
                .log(LoggingLevel.INFO, "Activate 2-1")
                .to("direct:activate2-2");

        from("direct:activate2-2")
                .log(LoggingLevel.INFO, "Activate 2-2")
                .setBody(simple("Hello Activate 2-2"));
    }
}
