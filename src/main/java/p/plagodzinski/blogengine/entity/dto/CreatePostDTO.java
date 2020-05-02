package p.plagodzinski.blogengine.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreatePostDTO {
    @NotNull
    @NotBlank
    private final String title;

    @NotNull
    @NotBlank
    private final String content;

    public CreatePostDTO(
            @NotNull @NotBlank final String title, @NotNull @NotBlank final String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
