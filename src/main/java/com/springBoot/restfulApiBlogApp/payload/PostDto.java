package com.springBoot.restfulApiBlogApp.payload;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class PostDto {
    private Long id;
    @NotEmpty
    @Size(min=2)
    private String title;
    @NotEmpty
    @Size(min=2)
    private String description;
    @NotEmpty
    @Size(min=2)
    private String content;
}
