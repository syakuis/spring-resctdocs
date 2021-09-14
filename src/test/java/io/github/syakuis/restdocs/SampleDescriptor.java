package io.github.syakuis.restdocs;

import io.github.syakuis.restdocs.constraints.Descriptor;
import lombok.Getter;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-08-25
 */
@Getter
public enum SampleDescriptor implements Descriptor {
    id("번호", false),
    username("사용자 이름", false),
    password("비밀번호", false),
    name("이름", false),
    disabled("비활성", false),
    blocked("잠금", false),
    uid("사용자 식별자", false),
    updatedOn("수정일", false),
    registeredOn("생성일", true);

    private final String description;
    private final boolean optional;

    SampleDescriptor(String description, boolean optional) {
        this.description = description;
        this.optional = optional;
    }

    public static Descriptor[] request() {
        return new Descriptor[]{
            username,
            password,
            name,
            disabled,
            blocked
        };
    }

    public static Descriptor[] profile() {
        return new Descriptor[]{
            uid,
            username,
            name,
            registeredOn,
            updatedOn
        };
    }
}
