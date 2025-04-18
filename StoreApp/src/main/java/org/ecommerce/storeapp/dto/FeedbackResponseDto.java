package org.ecommerce.storeapp.dto;

import lombok.Data;

@Data
public class FeedbackResponseDto {

    private Long id;
    private String feedback;
    private int rating;
    private String username;
}