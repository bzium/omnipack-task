package p.plagodzinski.blogengine.entity;

import p.plagodzinski.blogengine.entity.dto.CreateReviewDTO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "PostReview")
public class PostReview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REVIEW_ID")
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

    @DecimalMin(value = "0.0")
    @DecimalMax(value = "10.0")
    @Column(name = "RATING", nullable = false)
    private double rating;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "POST_ID")
    private BlogPost blogPost;

    public long getId() {
        return id;
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

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void fill(final CreateReviewDTO createReviewDTO, final BlogPost blogPost) {
        this.blogPost = blogPost;
        content = createReviewDTO.getContent();
        title = createReviewDTO.getTitle();
        rating = createReviewDTO.getRating();
    }
}
