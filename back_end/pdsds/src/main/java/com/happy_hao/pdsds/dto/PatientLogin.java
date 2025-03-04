package com.happy_hao.pdsds.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PatientLogin {
    @NotBlank(message = "The username cannot be empty.")
    @Pattern(regexp = "^[a-zA-Z0-9_]{5,20}$", message = "The username must consist of 5 to 20 letters, digits or underscores.")
    private String username;

    @NotBlank(message = "The password cannot be empty.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-zA-Z])[a-zA-Z0-9]{8,20}$", message = "The password must be 8 to 20 characters long and contain both letters and numbers.")
    private String password;

}
