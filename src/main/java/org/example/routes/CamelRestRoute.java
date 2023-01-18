package org.example.routes;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class CamelRestRoute extends RouteBuilder {

    @Override
    public void configure() {
        rest("/api")
            .post("/create1")
                .id("create1")
                .consumes("application/json")
                .produces("application/json")
                .to("direct:create1");

        rest("/api")
                .post("/create2")
                .id("create1")
                .consumes("application/json")
                .produces("application/json")
                .to("direct:create2");
    }
}
