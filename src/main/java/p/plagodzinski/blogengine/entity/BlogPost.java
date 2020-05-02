package p.plagodzinski.blogengine.entity;

import org.mapstruct.Named;
import p.plagodzinski.blogengine.entity.dto.ChangePostDTO;
import p.plagodzinski.blogengine.entity.dto.CreatePostDTO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "BlogPost")
public class BlogPost {

  private static final double NO_REVIEW_RATING = 0.0;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "POST_ID")
  private Long id;

  @Version
  private Long version;

  @NotNull
  @Size(min = 1, max = 50)
  @Column(name = "TITLE", length = 50, nullable = false)
  private String title;

  @NotNull
  @Size(min = 1, max = 255)
  @Column(name = "CONTENT", nullable = false)
  private String content;

  @NotNull
  @Enumerated(EnumType.STRING)
  private PostStatus status;

  @OneToMany(
          mappedBy = "blogPost",
          cascade = CascadeType.ALL,
          orphanRemoval = true
  )
  private final List<PostReview> postReviews = new ArrayList<>();


  @Transient
  private double averageRating; // We only need to simply mapping by mapstruct


  public Long getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public PostStatus getStatus() {
    return status;
  }

  public List<PostReview> getPostReviews() {
    return postReviews;
  }

  public double getAverageRating() {
    return averageRating;
  }

  public void fill(final CreatePostDTO createPostDTO) {
    title = createPostDTO.getTitle();
    content = createPostDTO.getContent();
    status = PostStatus.ACTIVE;
  }

  public void update(final ChangePostDTO changePostDTO) {
    status = changePostDTO.getStatus();
  }

  @Named("calculateAverageRatingFromReviews")
  public double calculateAverageRatingFromReviews() {
    return postReviews
            .stream()
            .mapToDouble(PostReview::getRating)
            .average()
            .orElse(NO_REVIEW_RATING);
  }

}
