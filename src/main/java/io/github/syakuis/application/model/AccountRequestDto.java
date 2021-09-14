package io.github.syakuis.application.model;

import lombok.*;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-08-11
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountRequestDto {
    @NoArgsConstructor(access = AccessLevel.PRIVATE) @AllArgsConstructor(access = AccessLevel.PRIVATE) @Builder @Getter @EqualsAndHashCode @ToString
    public static class ChangePassword {
        private String currentPassword;
        private String newPassword;
        private String newPasswordConfirm;
    }
}
