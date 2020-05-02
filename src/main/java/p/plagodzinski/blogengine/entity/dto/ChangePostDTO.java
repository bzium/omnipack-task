package p.plagodzinski.blogengine.entity.dto;

import p.plagodzinski.blogengine.entity.PostStatus;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ChangePostDTO {

  @NotNull
  private final long id;

  @NotNull
  @NotBlank
  private final PostStatus status;

  public ChangePostDTO(@NotNull final long id, @NotNull @NotBlank final PostStatus status) {
    this.id = id;
    this.status = status;
  }

  public PostStatus getStatus() {
    return status;
  }

  public long getId() {
    return id;
  }
}
