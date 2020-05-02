package p.plagodzinski.blogengine.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import p.plagodzinski.blogengine.application.exceptions.NotFoundPostException;
import p.plagodzinski.blogengine.application.exceptions.NotFoundReviewException;
import p.plagodzinski.blogengine.entity.PostReview;
import p.plagodzinski.blogengine.entity.dto.CreateReviewDTO;
import p.plagodzinski.blogengine.entity.dto.CreateReviewResponseDTO;
import p.plagodzinski.blogengine.infrastructure.BlogPostsRepository;
import p.plagodzinski.blogengine.infrastructure.ReviewRepository;

import javax.transaction.Transactional;

@Service
public class ReviewManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(ReviewManager.class);

    private final ReviewRepository reviewRepository;
    private final BlogPostsRepository blogPostsRepository;
    private final ReviewMapper reviewMapper;

    public ReviewManager(final ReviewRepository reviewRepository, final BlogPostsRepository blogPostsRepository, final ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.blogPostsRepository = blogPostsRepository;
        this.reviewMapper = reviewMapper;
    }

    @Transactional
    public CreateReviewResponseDTO createNewReview(final CreateReviewDTO createReviewDTO) {
        return blogPostsRepository.findById(createReviewDTO.getPostId()).map(blogPost -> {
            var postReview = new PostReview();
            postReview.fill(createReviewDTO, blogPost);
            postReview = reviewRepository.save(postReview);
            LOGGER.info("Create new review with id {}", postReview.getId());
            return reviewMapper.mapToCreateReviewResponseDto(postReview);
        }).orElseThrow(() -> new NotFoundPostException(createReviewDTO.getPostId()));
    }

    @Transactional
    public void removeReviewIfExists(final long reviewId) {
        try {
            reviewRepository.deleteById(reviewId);
            LOGGER.info("Review {} successfully removed", reviewId);
        } catch (final EmptyResultDataAccessException e) {
            LOGGER.error("Not found review with id {}", reviewId);
            throw new NotFoundReviewException(reviewId);
        }
    }
}
