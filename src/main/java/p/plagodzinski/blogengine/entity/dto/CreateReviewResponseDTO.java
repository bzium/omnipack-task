package p.plagodzinski.blogengine.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = CreateReviewResponseDTO.class)
@JsonDeserialize(as = CreateReviewResponseDTO.class)
public interface CreateReviewResponseDTO {
    long getId();
}
