package p.plagodzinski.blogengine.application;

import org.mapstruct.Mapper;
import p.plagodzinski.blogengine.entity.PostReview;
import p.plagodzinski.blogengine.entity.dto.CreateReviewResponseDTO;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
  CreateReviewResponseDTO mapToCreateReviewResponseDto(PostReview postReview);
}
