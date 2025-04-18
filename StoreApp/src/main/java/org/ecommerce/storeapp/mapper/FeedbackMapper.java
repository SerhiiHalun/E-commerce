package org.ecommerce.storeapp.mapper;

import lombok.AllArgsConstructor;
import org.ecommerce.storeapp.dto.FeedbackCreateDto;
import org.ecommerce.storeapp.dto.FeedbackResponseDto;
import org.ecommerce.storeapp.model.Feedback;
import org.ecommerce.storeapp.model.Product;
import org.ecommerce.storeapp.model.User;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class FeedbackMapper {

    public static FeedbackResponseDto toDto(Feedback feedback) {
        FeedbackResponseDto dto = new FeedbackResponseDto();
        dto.setId(feedback.getId());
        dto.setFeedback(feedback.getFeedback());
        dto.setRating(feedback.getRating());
        dto.setUsername(feedback.getUser().getFirstName()); // або getEmail()
        return dto;
    }

    public static Feedback toEntity(FeedbackCreateDto dto, Product product, User user) {
        Feedback feedback = new Feedback();
        feedback.setFeedback(dto.getFeedback());
        feedback.setRating(dto.getRating());
        feedback.setProduct(product);
        feedback.setUser(user);
        return feedback;
    }
}
