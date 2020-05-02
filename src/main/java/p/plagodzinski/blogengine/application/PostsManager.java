package p.plagodzinski.blogengine.application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import p.plagodzinski.blogengine.application.exceptions.NotFoundPostException;
import p.plagodzinski.blogengine.entity.BlogPost;
import p.plagodzinski.blogengine.entity.dto.ChangePostDTO;
import p.plagodzinski.blogengine.entity.dto.CreatePostDTO;
import p.plagodzinski.blogengine.entity.dto.CreatePostResponseDTO;
import p.plagodzinski.blogengine.entity.dto.PostDTO;
import p.plagodzinski.blogengine.infrastructure.BlogPostsRepository;

import javax.transaction.Transactional;

@Service
public class PostsManager {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostsManager.class);

    private final BlogPostsRepository blogPostsRepository;
    private final PostsMapper postsMapper;

    public PostsManager(
            final BlogPostsRepository blogPostsRepository, final PostsMapper postsMapper) {
        this.blogPostsRepository = blogPostsRepository;
        this.postsMapper = postsMapper;
    }

    @Transactional
    public CreatePostResponseDTO createNewPost(final CreatePostDTO createPostDTO) {
        var blogPost = new BlogPost();
        blogPost.fill(createPostDTO);
        blogPost = blogPostsRepository.save(blogPost);

        LOGGER.info("Create new post with id {}", blogPost.getId());

        return postsMapper.mapToCreatePostResponse(blogPost);
    }

    @Transactional(rollbackOn = NotFoundPostException.class)
    public PostDTO getPostInfo(final long postId) {
        return blogPostsRepository
                .findById(postId)
                .map(postsMapper::mapToGetPostInfoDTO)
                .orElseThrow(() -> new NotFoundPostException(postId));
    }

    @Transactional(rollbackOn = NotFoundPostException.class)
    public PostDTO changePost(final ChangePostDTO changePostDTO) {
        return blogPostsRepository
                .findById(changePostDTO.getId())
                .map(
                        blogPost -> {
                            LOGGER.info(
                                    "Change status of post {} to {}", blogPost.getId(), changePostDTO.getStatus());
                            blogPost.update(changePostDTO);
                            return blogPostsRepository.save(blogPost);
                        })
                .map(postsMapper::mapToGetPostInfoDTO)
                .orElseThrow(() -> new NotFoundPostException(changePostDTO.getId()));
    }
}
