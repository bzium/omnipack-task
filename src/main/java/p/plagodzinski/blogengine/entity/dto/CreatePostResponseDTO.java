package p.plagodzinski.blogengine.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = CreatePostResponseDTO.class)
@JsonDeserialize(as = CreatePostResponseDTO.class)
public interface CreatePostResponseDTO {
    long getId();
}
