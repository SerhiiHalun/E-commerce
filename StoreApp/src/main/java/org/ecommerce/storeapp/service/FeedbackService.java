package org.ecommerce.storeapp.service;

import org.ecommerce.storeapp.model.Feedback;
import org.ecommerce.storeapp.repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class FeedbackService {
    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Transactional
    public Feedback createFeedback(Feedback feedback) {

        return feedbackRepository.save(feedback);
    }


    @Transactional(readOnly = true)
    public Feedback getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Feedback with id " + id + " not found"));
    }


    @Transactional(readOnly = true)
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepository.findAll();
    }
    @Transactional(readOnly = true)
    public List<Feedback> getFeedbacksByProductId(int productId) {
        return feedbackRepository.findAllByProductId(productId);
    }

    @Transactional(readOnly = true)
    public List<Feedback> getFeedbacksByUserId(long userId) {
        return feedbackRepository.findAllByUserId(userId);
    }

    @Transactional
    public Feedback updateFeedback(Long id, Feedback updatedFeedback) {

        Feedback existing = getFeedbackById(id);

        existing.setFeedback(updatedFeedback.getFeedback());
        existing.setProduct(updatedFeedback.getProduct());
        existing.setUser(updatedFeedback.getUser());

        return feedbackRepository.save(existing);
    }


    @Transactional
    public void deleteFeedback(Long id) {
        if (!feedbackRepository.existsById(id)) {
            throw new NoSuchElementException("Feedback with id " + id + " not found");
        }
        feedbackRepository.deleteById(id);
    }

}
