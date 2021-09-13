package io.github.syakuis.restdocs.configuration;

import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-08-25
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigureRestDocs
@Import(RestDocsTestConfiguration.class)
public @interface AutoConfigureMvcRestDocs {
}
