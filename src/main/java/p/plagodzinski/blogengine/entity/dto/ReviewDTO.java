package p.plagodzinski.blogengine.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ReviewDTO.class)
@JsonDeserialize(as = ReviewDTO.class)
public interface ReviewDTO {
    long getId();

    String getTitle();

    String getContent();

    double getRating();
}
