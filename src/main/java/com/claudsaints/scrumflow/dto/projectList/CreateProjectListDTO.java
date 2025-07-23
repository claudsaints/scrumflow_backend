package com.claudsaints.scrumflow.dto.projectList;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CreateProjectListDTO {
    private String title;
    private Integer position;
}
