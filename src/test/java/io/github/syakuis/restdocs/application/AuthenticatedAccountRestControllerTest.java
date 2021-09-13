package io.github.syakuis.restdocs.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.syakuis.restdocs.application.model.AccountRequestDto;
import io.github.syakuis.restdocs.configuration.AutoConfigureMvcRestDocs;
import io.github.syakuis.restdocs.configuration.RestDocsFieldHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.requestHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.requestFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Seok Kyun. Choi.
 * @since 2021-08-13
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureMvcRestDocs
class AuthenticatedAccountRestControllerTest {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper mapper;

    private final RestDocsFieldHandler changePasswordFieldHandler = new RestDocsFieldHandler(ChangePasswordField.values());

    private String pathPrefix;
    private String restdocsPath;

    @BeforeEach
    void init() {
        pathPrefix = "/v1/me";
        restdocsPath = "accounts/v1/me/{method-name}";
    }

    @Test
    void changePassword() throws Exception {

        AccountRequestDto.ChangePassword changePassword = AccountRequestDto.ChangePassword.builder()
            .currentPassword("1234")
            .newPassword("aaaa")
            .newPasswordConfirm("aaaa")
            .build();

        mvc.perform(patch(pathPrefix + "/password")
            .content(mapper.writeValueAsString(changePassword))
            .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk())

            .andDo(document(restdocsPath,
                requestHeaders(
                    headerWithName(HttpHeaders.CONTENT_TYPE).description(MediaType.APPLICATION_JSON)
                ),

                requestFields(
                    changePasswordFieldHandler.payload(ChangePasswordField.currentPassword.name(), ChangePasswordField.newPassword.name(), ChangePasswordField.newPasswordConfirm.name()).collect()
                )
            ));
    }
}