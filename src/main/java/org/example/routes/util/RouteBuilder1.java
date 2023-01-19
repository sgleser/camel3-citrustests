package org.example.routes.util;

import org.apache.camel.LoggingLevel;
import org.apache.camel.model.RouteDefinition;
import org.springframework.stereotype.Component;

@Component
public class RouteBuilder1 extends AbstractRouteBuilder {

    @Override
    protected String getFrom() {
        return "direct:activate1";
    }

    @Override
    protected String getRouteId() {
        return "activate-1";
    }

    @Override
    protected void extendRoute(RouteDefinition route) {
        route
                .log(LoggingLevel.INFO, "Activate 1 here")
                .to("direct:activate1-1");

        from("direct:activate1-1")
                .log(LoggingLevel.INFO, "Activate 1-1")
                .setBody(simple("Hello Activate 1-1"));
    }
}
