package org.example.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class Camel3Route extends RouteBuilder {

    @Autowired
    private CamelContext context;

    @Override
    public void configure() throws Exception {

        context.setStreamCaching(true);
        context.setTracing(false);
        context.setUseMDCLogging(true);

        onException(java.lang.Exception.class)
                .handled(true)
                .to("direct:genericError");

        from("direct:create1")
                .routeId("create1")
                .log(LoggingLevel.INFO, "Message received on create1 with body: ${body}")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        System.out.println("Stop debugger here");
                    }
                })
                .delay(2000)
                .to("direct:create-response")
                .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.TEXT_PLAIN_VALUE));

        from("direct:create-response")
                .routeId("create-response")
                .delay(3000)
                .setBody(simple("Hello"));

        from("direct:create2")
                .routeId("create2")
                .to("direct:create-response")
                .delay(1000)
                .setBody(simple("${body} 2"))
                .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.TEXT_PLAIN_VALUE));

        from("direct:genericError")
                .routeId("generic-error")
                .setBody(simple("Error happened"));
    }
}
