package com.lambda_complex.generala.dto.request;

import com.lambda_complex.generala.constants.ValidationConstraints;
import com.lambda_complex.generala.constants.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class ReqPlayerDto {
    @NotNull(message= ValidationMessages.REQUIRED)
    @NotBlank(message= ValidationMessages.NOT_BLANK)
    @Size(
            min= ValidationConstraints.MIN_NAME_CHARS,
            max= ValidationConstraints.MAX_NAME_CHARS,
            message = ValidationMessages.NAME_SIZE
    )
    private String name;

    @NotNull(message= ValidationMessages.REQUIRED)
    @NotBlank(message= ValidationMessages.NOT_BLANK)
    @Size(
            max= ValidationConstraints.MAX_EMAIL_CHARS,
            message = ValidationMessages.EMAIL_SIZE
    )
    private String email;

    @NotNull(message= ValidationMessages.REQUIRED)
    @NotBlank(message= ValidationMessages.NOT_BLANK)
    @Size(
            min= ValidationConstraints.MIN_PASS_CHARS,
            max= ValidationConstraints.MAX_PASS_CHARS,
            message = ValidationMessages.PASS_SIZE
    )
    private String password;
}
