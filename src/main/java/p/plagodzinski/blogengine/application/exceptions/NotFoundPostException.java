package p.plagodzinski.blogengine.application.exceptions;

public class NotFoundPostException extends RuntimeException {
    private static final long serialVersionUID = -8757766143726941546L;

    private final long postId;

    public NotFoundPostException(final long postId) {
        this.postId = postId;
    }

    public long getPostId() {
        return postId;
    }
}
