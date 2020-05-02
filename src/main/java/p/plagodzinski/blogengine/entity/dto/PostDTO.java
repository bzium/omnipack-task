package p.plagodzinski.blogengine.entity.dto;

import p.plagodzinski.blogengine.entity.PostStatus;

import java.util.List;

public class PostDTO {

    private String title;

    private String content;

    private PostStatus status;

    private double averageRating;

    private List<ReviewDTO> postReviews;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(final String content) {
        this.content = content;
    }

    public PostStatus getStatus() {
        return status;
    }

    public void setStatus(final PostStatus status) {
        this.status = status;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(final double averageRating) {
        this.averageRating = averageRating;
    }

    public List<ReviewDTO> getPostReviews() {
        return postReviews;
    }

    public void setPostReviews(final List<ReviewDTO> postReviews) {
        this.postReviews = postReviews;
    }
}
