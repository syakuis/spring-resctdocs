package io.github.syakuis.application;

import io.github.syakuis.restdocs.constraints.Descriptor;
import lombok.Getter;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-09-02
 */

// https://stackoverflow.com/questions/20299074/can-i-make-an-abstract-enum-in-java
@Getter
public enum ChangePasswordField implements Descriptor {
    currentPassword("현재 비밀번호", false),
    newPassword("새로운 비밀번호", false),
    newPasswordConfirm("새로운 비밀번호 확인", false);

    private final String description;
    private final boolean optional;

    ChangePasswordField(String description, boolean optional) {
        this.description = description;
        this.optional = optional;
    }
}
