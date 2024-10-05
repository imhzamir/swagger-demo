package com.ba.swagger.bean;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentInfo {
    private Integer oid;
    @NotNull(message = "student name can't be null or empty")
    private String name;
    private String phone;
}
