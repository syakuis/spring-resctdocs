package io.github.syakuis.restdocs.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.restdocs.payload.FieldDescriptor;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-08-19
 */
@RequiredArgsConstructor
public final class RestDocsFieldHandler {
    private final List<FieldDescriptor> fields;

    public RestDocsFieldHandler(FieldSpec[] fields) {
        this.fields = Arrays.stream(fields)
            .map(it -> {
                FieldDescriptor fieldDescriptor = fieldWithPath(it.name()).description(it.getDescription());

                if (it.isOptional()) {
                    fieldDescriptor.optional();
                }

                return fieldDescriptor;
            })
            .collect(Collectors.toList());
    }

    public FieldDescriptorStream payload(String... fields) {
        List<String> paths = Arrays.asList(fields);

        if (paths.isEmpty()) {
            return new FieldDescriptorStream(this.fields.stream());
        }

        return new FieldDescriptorStream(this.fields.stream().filter(it -> paths.contains(it.getPath())));
    }

    public static class FieldDescriptorStream {
        private final Stream<FieldDescriptor> stream;

        public FieldDescriptorStream(Stream<FieldDescriptor> stream) {
            this.stream = stream;
        }

        public List<FieldDescriptor> exclude(String... fields) {
            if (fields == null || fields.length < 1) {
                throw new IllegalArgumentException("fields 는 null 이거나 empty 일 수 없습니다.");
            }

            List<String> paths = Arrays.asList(fields);
            return stream.filter(it -> !paths.contains(it.getPath())).collect(Collectors.toList());
        }

        public List<FieldDescriptor> collect() {
            return stream.collect(Collectors.toList());
        }
    }
}
