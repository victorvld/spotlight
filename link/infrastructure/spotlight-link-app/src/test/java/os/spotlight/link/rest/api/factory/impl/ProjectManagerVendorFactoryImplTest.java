package os.spotlight.link.rest.api.factory.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import os.spotlight.link.factory.adpater.gateway.JiraGateway;
import os.spotlight.link.factory.adpater.gateway.MondayGateway;
import os.spotlight.link.rest.api.factory.impl.exception.UnknownPmVendor;

import java.util.stream.Stream;

class ProjectManagerVendorFactoryImplTest {

    @Mock
    private final MondayGateway mondayGateway = Mockito.mock(MondayGateway.class);
    @Mock
    private final JiraGateway jiraGateway = Mockito.mock(JiraGateway.class);
    private final ProjectManagerVendorFactoryImpl underTest = new ProjectManagerVendorFactoryImpl(jiraGateway, mondayGateway);

    @ParameterizedTest
    @MethodSource("typeProvider")
    void testThatRightVendorsAreGenerated(Class<?> expectedType, String type) {
        Assertions.assertInstanceOf(expectedType, underTest.get(type));
    }

    static Stream<Arguments> typeProvider() {
        return Stream.of(
                Arguments.of(MondayGateway.class, "monday"),
                Arguments.of(JiraGateway.class, "jira")
        );
    }

    @Test
    void whenUnknownVendorIsProvidedThenThrowsUnknownPmException() {
        Assertions.assertThrows(UnknownPmVendor.class,() -> underTest.get("unknown"));
    }

}