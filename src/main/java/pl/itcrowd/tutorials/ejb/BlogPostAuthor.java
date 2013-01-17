package pl.itcrowd.tutorials.ejb;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionSynchronizationRegistry;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: kleks
 * Date: 1/17/13
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
@Stateless
public class BlogPostAuthor {

    private static final Logger LOGGER = Logger.getLogger(BlogPostAuthor.class.getCanonicalName());

    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private TransactionSynchronizationRegistry txReg;

    //    @Schedule(hour = "*", minute = "*", second = "0/5")
    public void createNewBlogPost() {
        LOGGER.info("createNewBlogPost");
        LOGGER.info("Transaction= " + txReg.getTransactionKey());
        entityManager.persist(new BlogPost(UUID.randomUUID().toString()));
    }

    public BlogPost readPosts() {
        LOGGER.info("readPosts");
        LOGGER.info("Transaction= " + txReg.getTransactionKey());
        final List<BlogPost> resultList = entityManager.createQuery(
                "select p from BlogPost p order by id desc")
                .setMaxResults(10)
                .getResultList();
        for (BlogPost post : resultList) {
            LOGGER.info(post.toString());
        }
        return resultList.isEmpty() ? null : resultList.get(0);
    }

    public void updatePosts(BlogPost result) {
        LOGGER.info("updatePosts");
        LOGGER.info("Transaction= " + txReg.getTransactionKey());

        entityManager.createQuery(
                "update BlogPost set topic='costam' where id=:idoiu  ")
                .setParameter("idoiu",result.getId())
                .executeUpdate();

    }
}

