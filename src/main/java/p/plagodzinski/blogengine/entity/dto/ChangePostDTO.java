package p.plagodzinski.blogengine.entity.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;
import p.plagodzinski.blogengine.entity.PostStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value.Immutable
@JsonSerialize(as = ChangePostDTO.class)
@JsonDeserialize(as = ChangePostDTO.class)
public interface ChangePostDTO {
    @NotNull
    long getId();

    @NotNull
    @NotBlank
    PostStatus getStatus();
}
