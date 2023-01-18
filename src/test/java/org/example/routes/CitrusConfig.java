package org.example.routes;

import com.consol.citrus.camel.endpoint.CamelSyncEndpoint;
import com.consol.citrus.camel.endpoint.CamelSyncEndpointConfiguration;
import org.apache.camel.CamelContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@TestConfiguration
public class CitrusConfig {

    @Autowired
    private CamelContext context;

    @Bean(name = "create1Endpoint")
    public CamelSyncEndpoint create1Endpoint() {
        CamelSyncEndpointConfiguration config = new CamelSyncEndpointConfiguration();
        config.setCamelContext(context);
        config.setEndpointUri("direct:create1");
        config.setPollingInterval(300L);
        config.setTimeout(1000L);
        return new CamelSyncEndpoint(config);
    }

    @Bean(name = "create2Endpoint")
    public CamelSyncEndpoint create2Endpoint() {
        CamelSyncEndpointConfiguration config = new CamelSyncEndpointConfiguration();
        config.setCamelContext(context);
        config.setEndpointUri("direct:create2");
        config.setPollingInterval(300L);
        config.setTimeout(1000L);
        return new CamelSyncEndpoint(config);
    }

}
