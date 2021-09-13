package io.github.syakuis.restdocs.application;

import io.github.syakuis.restdocs.application.model.AccountRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-08-09
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/v1/me")
class AuthenticatedAccountRestController {

    @PatchMapping(value = "/password", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> changePassword(@RequestBody AccountRequestDto.ChangePassword password) {
        return ResponseEntity.ok().build();
    }
}
