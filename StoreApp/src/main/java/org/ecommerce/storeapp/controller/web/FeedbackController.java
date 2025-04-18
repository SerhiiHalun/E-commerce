package org.ecommerce.storeapp.controller.web;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.ecommerce.storeapp.dto.FeedbackCreateDto;
import org.ecommerce.storeapp.service.FeedbackService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;


@Controller
@RequestMapping("/products/{productId}")
@RequiredArgsConstructor
public class FeedbackController {
    private final FeedbackService feedbackService;


    @PostMapping("/review")
    public String addReview(@ModelAttribute("review") @Valid FeedbackCreateDto dto,
                            @PathVariable int productId,
                            Principal principal) {
        feedbackService.addFeedback(productId, dto, principal.getName());
        return "redirect:/product/" + productId;
    }
}
