package org.example.routes;

import com.consol.citrus.config.CitrusConfigImport;
import com.consol.citrus.config.ComponentLifecycleProcessor;
import com.consol.citrus.context.SpringBeanReferenceResolver;
import com.consol.citrus.context.TestContextFactoryBean;
import com.consol.citrus.endpoint.DefaultEndpointFactory;
import com.consol.citrus.endpoint.EndpointFactory;
import com.consol.citrus.functions.FunctionConfig;
import com.consol.citrus.log.DefaultLogModifier;
import com.consol.citrus.log.LogModifier;
import com.consol.citrus.report.*;
import com.consol.citrus.reporter.ReporterConfig;
import com.consol.citrus.spi.ReferenceResolver;
import com.consol.citrus.util.SpringBeanTypeConverter;
import com.consol.citrus.util.TypeConverter;
import com.consol.citrus.validation.MessageValidatorConfig;
import com.consol.citrus.validation.interceptor.MessageProcessorsFactory;
import com.consol.citrus.validation.matcher.ValidationMatcherConfig;
import com.consol.citrus.variable.SegmentVariableExtractorRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        FunctionConfig.class,
        ValidationMatcherConfig.class,
        MessageValidatorConfig.class,
        ReporterConfig.class,
        CitrusConfigImport.class
})
public class CitrusSpringConfig {

    @Bean
    public TestContextFactoryBean testContextFactory() { return new TestContextFactoryBean(); }

    @Bean
    public EndpointFactory endpointFactory() { return new DefaultEndpointFactory(); }

    @Bean
    public ReferenceResolver referenceResolver() { return new SpringBeanReferenceResolver(); }

    @Bean(name = "citrusConverter")
    public TypeConverter typeConverter() { return SpringBeanTypeConverter.INSTANCE; }

    @Bean
    public LogModifier logModifier() { return new DefaultLogModifier(); }

    @Bean
    public MessageProcessorsFactory messageProcessors() { return new MessageProcessorsFactory(); }

    @Bean
    public TestListenersFactory testListeners() { return new TestListenersFactory(); }

    @Bean
    public TestActionListenersFactory testActionListeners() { return new TestActionListenersFactory(); }

    @Bean
    public TestSuiteListenersFactory testSuiteListeners() { return new TestSuiteListenersFactory(); }

    @Bean
    public MessageListenersFactory messageListeners() { return new MessageListenersFactory(); }

    @Bean
    public FailureStackTestListener failureStackTestListener() { return new FailureStackTestListener(); }

    @Bean
    public ComponentLifecycleProcessor componentInitializer() { return new ComponentLifecycleProcessor(); }

    @Bean
    public SegmentVariableExtractorRegistry segmentVariableExtractorRegistry() {
        return new SegmentVariableExtractorRegistry();
    }

}
