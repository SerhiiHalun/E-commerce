package org.ecommerce.storeapp.controller.api;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ecommerce.storeapp.dto.FeedbackCreateDto;
import org.ecommerce.storeapp.dto.FeedbackResponseDto;
import org.ecommerce.storeapp.service.FeedbackService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/products/{productId}/feedback")
@RequiredArgsConstructor
public class FeedbackApiController {
    private final FeedbackService feedbackService;

    @PostMapping
    public ResponseEntity<FeedbackResponseDto> addFeedback(@PathVariable int productId,
                                                           @Valid @RequestBody FeedbackCreateDto dto,
                                                           Principal principal) {
        FeedbackResponseDto saved = feedbackService.addFeedback(productId, dto, principal.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<FeedbackResponseDto> getAll(@PathVariable int productId) {
        return feedbackService.getFeedbacksByProductId(productId);

    }
}
