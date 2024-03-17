package com.educationsystem.education_sys.dto;

import lombok.Data;

@Data
public class CourseMaterialDto {
    private String courseId;
    private String name;
    private String description;
    private String videoUrl;
    private String thumbnailUrl;
}
