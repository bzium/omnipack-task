package p.plagodzinski.blogengine.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value.Immutable
@JsonSerialize(as = CreatePostDTO.class)
@JsonDeserialize(as = CreatePostDTO.class)
public interface CreatePostDTO {
    @NotNull
    @NotBlank
    String getTitle();

    @NotNull
    @NotBlank
    String getContent();
}
