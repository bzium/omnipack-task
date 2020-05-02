import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import p.plagodzinski.blogengine.BlogEngineApplication
import p.plagodzinski.blogengine.application.PostsManager
import p.plagodzinski.blogengine.application.exceptions.NotFoundPostException
import p.plagodzinski.blogengine.entity.PostStatus
import p.plagodzinski.blogengine.entity.dto.ChangePostDTO
import p.plagodzinski.blogengine.entity.dto.CreatePostDTO
import spock.lang.Shared
import spock.lang.Specification

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = BlogEngineApplication)
class PostsManagerTest extends Specification {

    @Autowired
    private PostsManager postsManager

    @Shared
    def createPostDTO = new CreatePostDTO("Test title", "TestContent")

    @Shared
    def noExistingPostId = 1234

    @Shared
    def archivedStatus = PostStatus.ARCHIVED

    def "Register new post"() {
        given: "Register new post"
        def createPostResponse = postsManager.createNewPost(createPostDTO)
        expect: "Returned post id its not null"
        Objects.nonNull(createPostResponse.getId())
    }

    def "Check if getPostInfo get correct data for newly created post"() {
        given: "Add new post"
        def createPostResponse = postsManager.createNewPost(createPostDTO)
        when: "Get info about post"
        def postInfo = postsManager.getPostInfo(createPostResponse.id)
        then: "Check if return data is correct"
        createPostDTO.content == postInfo.content
        createPostDTO.title == postInfo.title
        postInfo.status == PostStatus.ACTIVE
        postInfo.postReviews.size() == 0
        postInfo.averageRating == 0.0D
    }

    def "Method getPostInfo throws exception then post not exists"() {
        when: "Get info about post"
        postsManager.getPostInfo(noExistingPostId)
        then: "Exception will be thrown"
        thrown(NotFoundPostException)
    }

    def "Change status of existing post"() {
        given: "Add new post"
        def createPostResponse = postsManager.createNewPost(createPostDTO)
        when: "Change status of post to archived"
        def changePostDTO = new ChangePostDTO(createPostResponse.id, archivedStatus)
        def changedPost = postsManager.changePost(changePostDTO)
        then: "Correct status is set"
        changedPost.status == archivedStatus
    }

    def "Method changePost throw exception when post not exists"() {
        given: "Prepare request to change post"
        def changePostDTO = new ChangePostDTO(noExistingPostId, archivedStatus)
        when: "Change status of post to archived"
        postsManager.changePost(changePostDTO)
        then: "Exception will be thrown"
        thrown(NotFoundPostException)
    }
}