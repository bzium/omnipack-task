package p.plagodzinski.blogengine.application;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import p.plagodzinski.blogengine.entity.BlogPost;
import p.plagodzinski.blogengine.entity.dto.CreatePostResponseDTO;
import p.plagodzinski.blogengine.entity.dto.PostDTO;

@Mapper(componentModel = "spring")
public interface PostsMapper {
    CreatePostResponseDTO mapToCreatePostResponse(BlogPost blogPost);

    @Mapping(
            source = "averageRating",
            target = "averageRating",
            qualifiedByName = "calculateAverageRatingFromReviews")
    PostDTO mapToGetPostInfoDTO(BlogPost blogPost);
}
