package os.psy.research.spotlight.domain.factory;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import os.psy.research.spotlight.domain.adpater.jira.JiraApi;
import os.psy.research.spotlight.domain.adpater.monday.MondayApi;
import os.psy.research.spotlight.infrastructure.errorHandling.exceptions.UnknownPmVendor;

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
                Arguments.of(MondayApi.class, "monday"),
                Arguments.of(JiraApi.class, "jira")
        );
    }

    @Test
    void whenUnknownVendorIsProvidedThenThrowsUnknownPmException() {
        Assertions.assertThrows(UnknownPmVendor.class,() -> factory.get("unknown"));
    }

}