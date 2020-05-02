import p.plagodzinski.blogengine.entity.BlogPost
import p.plagodzinski.blogengine.entity.PostReview
import p.plagodzinski.blogengine.entity.dto.ImmutableCreateReviewDTO
import spock.lang.Specification

class BlogPostTest extends Specification {

    def createReviewDTO(double rating) {
        return ImmutableCreateReviewDTO
                .builder()
                .postId(1)
                .title("Test")
                .content("Test")
                .rating(rating)
                .build()
    }

    def "Calculate average for 2 elements"() {
        given: "We create post with 2 reviews"
        def blogPost = new BlogPost();
        def review1 = new PostReview();
        def review2 = new PostReview();
        review1.fill(createReviewDTO(7.0D), blogPost)
        review2.fill(createReviewDTO(5.0D), blogPost)
        blogPost.postReviews.add(review1)
        blogPost.postReviews.add(review2)
        expect: "Average is 6.0"
        blogPost.calculateAverageRatingFromReviews() == 6.0D
    }

    def "Calculate average for 0 elements"() {
        given: "We"
        def blogPost = new BlogPost();
        expect:
        blogPost.calculateAverageRatingFromReviews() == 0.0D
    }
}
