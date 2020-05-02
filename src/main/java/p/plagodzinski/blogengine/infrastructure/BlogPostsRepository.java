package p.plagodzinski.blogengine.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import p.plagodzinski.blogengine.entity.BlogPost;

@Repository
public interface BlogPostsRepository extends JpaRepository<BlogPost, Long> {

}


