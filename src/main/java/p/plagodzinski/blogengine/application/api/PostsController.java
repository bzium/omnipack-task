package p.plagodzinski.blogengine.application.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import p.plagodzinski.blogengine.application.PostsManager;
import p.plagodzinski.blogengine.entity.dto.ChangePostDTO;
import p.plagodzinski.blogengine.entity.dto.CreatePostDTO;
import p.plagodzinski.blogengine.entity.dto.CreatePostResponseDTO;
import p.plagodzinski.blogengine.entity.dto.PostDTO;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/api")
@Validated
public class PostsController {

  private final PostsManager postsManager;

  public PostsController(final PostsManager postsManager) {
    this.postsManager = postsManager;
  }

  @PostMapping(
          value = "/posts",
          produces = MediaType.APPLICATION_JSON_VALUE,
          consumes = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.CREATED)
  public CreatePostResponseDTO pubicNewPost(@Valid @RequestBody final CreatePostDTO createPostDTO) {
    return postsManager.createNewPost(createPostDTO);
  }

  @PatchMapping(
          value = "/posts",
          consumes = MediaType.APPLICATION_JSON_VALUE,
          produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public PostDTO changePost(@Valid @RequestBody final ChangePostDTO changePostDTO) {
    return postsManager.changePost(changePostDTO);
  }

  @GetMapping(value = "/posts/{postId}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  @ResponseStatus(HttpStatus.OK)
  public PostDTO getInfoAboutPost(@NotNull @PathVariable("postId") final long postId) {
    return postsManager.getPostInfo(postId);
  }
}
