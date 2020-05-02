package p.plagodzinski.blogengine.entity.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CreateReviewDTO {

    @NotNull
    private final long postId;

    @NotNull
    @NotBlank
    private final String title;

    @NotNull
    @NotBlank
    private final String content;

    @NotNull
    private final double rating;

    public CreateReviewDTO(
            @NotNull final long postId,
            @NotNull @NotBlank final String title,
            @NotNull @NotBlank final String content,
            @NotNull final double rating) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.rating = rating;
    }

    public long getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public double getRating() {
        return rating;
    }
}
