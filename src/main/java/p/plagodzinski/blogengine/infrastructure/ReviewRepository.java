package p.plagodzinski.blogengine.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p.plagodzinski.blogengine.entity.PostReview;

@Repository
public interface ReviewRepository extends JpaRepository<PostReview, Long> {
}
