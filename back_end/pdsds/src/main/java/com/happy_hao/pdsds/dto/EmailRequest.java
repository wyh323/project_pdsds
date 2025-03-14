package com.happy_hao.pdsds.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmailRequest {
    @NotBlank(message = "The username cannot be empty.")
    @Pattern(regexp = "^[a-zA-Z0-9_]{5,20}$", message = "The username must consist of 5 to 20 letters, digits or underscores.")
    private String username;
    @NotBlank(message = "The email cannot be empty.")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "The email must be a valid email address.")
    private String email;

}
