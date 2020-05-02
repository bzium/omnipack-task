package p.plagodzinski.blogengine.application.exceptions;


public class NotFoundReviewException extends RuntimeException {
    private static final long serialVersionUID = 1370620096475447638L;

    private final long reviewId;

    public NotFoundReviewException(final long reviewId) {
        this.reviewId = reviewId;
    }

    public long getReviewId() {
        return reviewId;
    }
}
