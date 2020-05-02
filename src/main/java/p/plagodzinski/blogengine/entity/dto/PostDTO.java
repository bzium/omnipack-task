package p.plagodzinski.blogengine.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import p.plagodzinski.blogengine.entity.PostStatus;

import java.util.List;

@Value.Immutable
@JsonSerialize(as = PostDTO.class)
@JsonDeserialize(as = PostDTO.class)
public interface PostDTO {
    String getTitle();

    String getContent();

    PostStatus getStatus();

    double getAverageRating();

    List<ReviewDTO> getPostReviews();
}
