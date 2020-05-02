package p.plagodzinski.blogengine.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import p.plagodzinski.blogengine.application.ReviewManager;
import p.plagodzinski.blogengine.entity.dto.CreateReviewDTO;
import p.plagodzinski.blogengine.entity.dto.CreateReviewResponseDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
@Validated
public class ReviewController {

    private final ReviewManager reviewManager;

    public ReviewController(final ReviewManager reviewManager) {
        this.reviewManager = reviewManager;
    }

    @PostMapping(value = "/reviews", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public CreateReviewResponseDTO createReview(@Valid @RequestBody final CreateReviewDTO createReviewDTO) {
        return reviewManager.createNewReview(createReviewDTO);
    }

    @DeleteMapping(value = "/reviews/{reviewId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteReview(@NotNull @PathVariable("reviewId") final long reviewId) {
        reviewManager.removeReviewIfExists(reviewId);
    }
}
