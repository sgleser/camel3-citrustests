package org.example.routes.util;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.RouteDefinition;

public abstract class AbstractRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        RouteDefinition route = from(getFrom()).routeId(getRouteId())
                .to("direct:setId")
                .to("direct:logRequest");

        extendRoute(route);
    }

    protected abstract String getRouteId();

    protected abstract String getFrom();

    protected abstract void extendRoute(RouteDefinition route);
}
