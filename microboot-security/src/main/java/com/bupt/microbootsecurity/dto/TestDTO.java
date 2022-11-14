package com.bupt.microbootsecurity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Data
@Getter
@Setter
public class TestDTO {

    @NotBlank
    private Integer num;

    @NotBlank
    @Length
    private String type;

    @NotNull
    @Email
    private String email;
}
