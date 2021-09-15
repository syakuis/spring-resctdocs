package io.github.syakuis.restdocs;

import io.github.syakuis.restdocs.constraints.DescriptorCollectors;
import io.github.syakuis.restdocs.constraints.RestDocsDescriptor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-09-14
 */
class RestDocsDescriptorTest {
    private RestDocsDescriptor restDocsSpecHandler;

    @BeforeEach
    void init() {
        restDocsSpecHandler = new RestDocsDescriptor(SampleDescriptor.values());
    }

    @Test
    void of() {
        assertNotNull(restDocsSpecHandler.of());
    }

    @Test
    void stream() {
        List<String> names = Arrays.stream(SampleDescriptor.values()).map(Enum::name).collect(Collectors.toList());

        assertTrue(restDocsSpecHandler.of().stream().allMatch(o -> names.contains(o.name())));
    }

    @Test
    void exclude() {
        assertTrue(restDocsSpecHandler.of().exclude(SampleDescriptor.name).stream().noneMatch(o -> SampleDescriptor.name.name().contains(o.name())));
    }

    @Test
    void collect() {
        List<FieldDescriptor> descriptors = restDocsSpecHandler.of().collect(DescriptorCollectors::fieldDescriptor);

        List<String> names = Arrays.stream(SampleDescriptor.values()).map(Enum::name).collect(Collectors.toList());
        assertTrue(descriptors.stream().allMatch(o -> names.contains(o.getPath())));
    }
}