package io.github.syakuis.restdocs;

import io.github.syakuis.restdocs.constraints.DescriptorCollectors;
import io.github.syakuis.restdocs.constraints.RestDocsDescriptor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.restdocs.headers.HeaderDescriptor;
import org.springframework.restdocs.hypermedia.LinkDescriptor;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.request.ParameterDescriptor;
import org.springframework.restdocs.request.RequestPartDescriptor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;


/**
 * @author Seok Kyun. Choi.
 * @since 2021-09-14
 */
@Slf4j
class DescriptorCollectorTest {
    private RestDocsDescriptor restDocsSpecHandler;

    @BeforeEach
    void init() {
        restDocsSpecHandler = new RestDocsDescriptor(SampleDescriptor.values());
    }

    @Test
    void parameterDescriptor() {
        List<ParameterDescriptor> descriptors = restDocsSpecHandler.of().collect(DescriptorCollectors::parameterDescriptor);

        List<String> names = Arrays.stream(SampleDescriptor.values()).map(Enum::name).collect(Collectors.toList());
        assertTrue(descriptors.stream().allMatch(o -> names.contains(o.getName())));
    }

    @Test
    void fieldDescriptor() {
        List<FieldDescriptor> descriptors = restDocsSpecHandler.of().collect(DescriptorCollectors::fieldDescriptor);

        List<String> names = Arrays.stream(SampleDescriptor.values()).map(Enum::name).collect(Collectors.toList());
        assertTrue(descriptors.stream().allMatch(o -> names.contains(o.getPath())));
    }

    @Test
    void headerDescriptor() {
        List<HeaderDescriptor> descriptors = restDocsSpecHandler.of().collect(DescriptorCollectors::headerDescriptor);

        List<String> names = Arrays.stream(SampleDescriptor.values()).map(Enum::name).collect(Collectors.toList());
        assertTrue(descriptors.stream().allMatch(o -> names.contains(o.getName())));
    }

    @Test
    void linkDescriptor() {
        List<LinkDescriptor> descriptors = restDocsSpecHandler.of().collect(DescriptorCollectors::linkDescriptor);

        List<String> names = Arrays.stream(SampleDescriptor.values()).map(Enum::name).collect(Collectors.toList());
        assertTrue(descriptors.stream().allMatch(o -> names.contains(o.getRel())));
    }

    @Test
    void requestPartDescriptor() {
        List<RequestPartDescriptor> descriptors = restDocsSpecHandler.of().collect(DescriptorCollectors::requestPartDescriptor);

        List<String> names = Arrays.stream(SampleDescriptor.values()).map(Enum::name).collect(Collectors.toList());
        assertTrue(descriptors.stream().allMatch(o -> names.contains(o.getName())));
    }
}