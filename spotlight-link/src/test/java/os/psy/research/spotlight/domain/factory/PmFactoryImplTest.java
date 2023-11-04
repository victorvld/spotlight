package os.psy.research.spotlight.domain.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import os.psy.research.spotlight.domain.adpater.jira.software.cloud.rest.api.JiraSoftwareCloudAdapterImpl;
import os.psy.research.spotlight.domain.adpater.monday.graph.ql.api.MondayGraphQlAdapterImpl;
import os.psy.research.spotlight.infrastructure.errorhandling.exceptions.UnknownPmVendor;

import java.util.stream.Stream;

class PmFactoryImplTest {

    private final PmFactoryImpl factory = new PmFactoryImpl();

    @ParameterizedTest
    @MethodSource("typeProvider")
    void testThatRightVendorsAreGenerated(Class<?> expectedType, String type) {
        Assertions.assertInstanceOf(expectedType, factory.get(type));
    }

    static Stream<Arguments> typeProvider() {
        return Stream.of(
                Arguments.of(MondayGraphQlAdapterImpl.class, "monday"),
                Arguments.of(JiraSoftwareCloudAdapterImpl.class, "jira")
        );
    }

    @Test
    void whenUnknownVendorIsProvidedThenThrowsUnknownPmException() {
        Assertions.assertThrows(UnknownPmVendor.class,() -> factory.get("unknown"));
    }

}