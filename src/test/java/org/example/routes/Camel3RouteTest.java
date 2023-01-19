package org.example.routes;

import com.consol.citrus.TestActionRunner;
import com.consol.citrus.annotations.CitrusResource;
import com.consol.citrus.annotations.CitrusTest;
import com.consol.citrus.camel.endpoint.CamelSyncEndpoint;
import com.consol.citrus.junit.jupiter.spring.CitrusSpringSupport;
import com.consol.citrus.message.MessageType;
import org.apache.camel.CamelContext;
import org.apache.camel.Route;
import org.apache.camel.builder.AdviceWith;
import org.apache.camel.test.spring.junit5.CamelSpringBootTest;
import org.example.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import static com.consol.citrus.actions.SendMessageAction.Builder.send;
import static com.consol.citrus.actions.ReceiveMessageAction.Builder.receive;

@Disabled("Disabled until 2nd Consumer problem is fixed")
@SpringBootTest(
        webEnvironment =  SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = { Application.class }
)
@ActiveProfiles("test")
@DirtiesContext
@CitrusSpringSupport
@CamelSpringBootTest
@ContextConfiguration(classes = {
        CitrusSpringConfig.class,
        CitrusConfig.class
} )
public class Camel3RouteTest {

    @Autowired
    private CamelContext context;

    @Autowired
    private Environment environment;

    @Autowired
    CamelSyncEndpoint create1Endpoint;

    @Autowired
    CamelSyncEndpoint create2Endpoint;

    private static boolean routesAreSetup;

    @BeforeEach
    public void setupRoutesToReturnString() throws Exception {
        /*if (routesAreSetup) return;
        for (Route route: context.getRoutes()) {
            AdviceWith.adviceWith(context, route.getId(), r-> {
                r.weaveAddLast().convertBodyTo(String.class);
            });
        }
        routesAreSetup = true;*/
    }

    @CitrusTest
    @Test
    public void testCreate1(@CitrusResource TestActionRunner test) throws Exception {

        test.$(send("camel:sync:direct:create1")
                .fork(true)
                .message()
                .type(MessageType.PLAINTEXT)
                .body("")
        );

        test.$(receive("camel:sync:direct:create1")
                .timeout(500000)
                .message()
                .type(MessageType.PLAINTEXT)
                .body("Hello")
        );
    }

    @CitrusTest
    @Test
    public void testCreate12(@CitrusResource TestActionRunner test) throws Exception {

        test.$(send("camel:sync:direct:create1")
                .fork(true)
                .message()
                .type(MessageType.PLAINTEXT)
                .body("Test 2")
        );

        test.$(receive("camel:sync:direct:create1")
                .timeout(500000)
                .message()
                .type(MessageType.PLAINTEXT)
                .body("Hello")
        );
    }

    @CitrusTest
    @Test
    public void testCreate2(@CitrusResource TestActionRunner test) throws Exception {

        test.$(send("camel:sync:direct:create2")
                .fork(true)
                .message()
                .type(MessageType.PLAINTEXT)
                .body("")
        );

        test.$(receive("camel:sync:direct:create2")
                .timeout(500000)
                .message()
                .type(MessageType.PLAINTEXT)
                .body("Hello 2")
        );
    }

    @CitrusTest
    @Test
    public void testCreate22(@CitrusResource TestActionRunner test) throws Exception {

        test.$(send("camel:sync:direct:create2")
                .fork(true)
                .message()
                .type(MessageType.PLAINTEXT)
                .body("Test 2")
        );

        test.$(receive("camel:sync:direct:create2")
                .timeout(500000)
                .message()
                .type(MessageType.PLAINTEXT)
                .body("Hello 2")
        );
    }
}
