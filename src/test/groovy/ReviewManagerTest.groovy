import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import p.plagodzinski.blogengine.BlogEngineApplication
import p.plagodzinski.blogengine.application.PostsManager
import p.plagodzinski.blogengine.application.ReviewManager
import p.plagodzinski.blogengine.application.exceptions.NotFoundReviewException
import p.plagodzinski.blogengine.entity.dto.ImmutableCreatePostDTO
import p.plagodzinski.blogengine.entity.dto.ImmutableCreateReviewDTO
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = BlogEngineApplication)
class ReviewManagerTest extends Specification {

    @Autowired
    private ReviewManager reviewManager

    @Autowired
    private PostsManager postsManager

    @Shared
    def createPostDto = ImmutableCreatePostDTO.builder().title("Test title").content("Test Content").build()

    def buildCreateReviewDTO(long postId) {
        return ImmutableCreateReviewDTO
                .builder()
                .title("Test review")
                .content("Test content")
                .rating(1.0D)
                .postId(postId)
                .build()
    }

    def "Add review to existing post"() {
        given: "We have already registered post"
        def createPostResponse = postsManager.createNewPost(createPostDto)
        when: "Add new review"
        def createReviewDTO = buildCreateReviewDTO(createPostResponse.id)
        def createReviewResponse = reviewManager.createNewReview(createReviewDTO)
        then: "ReviewId is not null and getPostInfo return one review"
        Objects.nonNull(createReviewResponse.id)
        postsManager.getPostInfo(createPostResponse.id).getPostReviews().size() == 1
    }

    def "Remove review from existing post"() {
        given: "We have already registered post with one review"
        def createPostResponse = postsManager.createNewPost(createPostDto)
        def createReviewDTO = buildCreateReviewDTO(createPostResponse.id)
        def reviewId = reviewManager.createNewReview(createReviewDTO).id
        when: "Remove review"
        reviewManager.removeReviewIfExists(reviewId)
        then: "No exception will be throw"
        noExceptionThrown()
    }

    def "Remove not existing review"() {
        given: "We have non existing review id"
        def noExistingReviewId = 1234
        when: "Remove not existing review"
        reviewManager.removeReviewIfExists(noExistingReviewId)
        then: "Exception will be thrown"
        thrown(NotFoundReviewException)
    }

}
