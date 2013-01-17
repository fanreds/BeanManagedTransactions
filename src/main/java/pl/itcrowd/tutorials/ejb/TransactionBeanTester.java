package pl.itcrowd.tutorials.ejb;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.transaction.TransactionSynchronizationRegistry;
import javax.transaction.UserTransaction;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: kleks
 * Date: 1/17/13
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class TransactionBeanTester {

    private static final Logger LOGGER = Logger.getLogger(TransactionBeanTester.class.getCanonicalName());

    @Resource
    private SessionContext sessionContext;

    @EJB
    private BlogPostAuthor blogPostAuthor;

    @Resource
    private TransactionSynchronizationRegistry txReg;


    public void exec() {
        BlogPost result;

        UserTransaction ut = sessionContext.getUserTransaction();
        blogPostAuthor.createNewBlogPost();
        blogPostAuthor.createNewBlogPost();
        blogPostAuthor.createNewBlogPost();

        try {
            ut.begin();

            LOGGER.info("BeanTransactionStatus= " + txReg.getTransactionStatus());

            try {
                result = blogPostAuthor.readPosts();
                if (false)
                    throw new BeanReaderException("Error");
                else if (result != null)
                    blogPostAuthor.updatePosts(result);
                blogPostAuthor.readPosts();
                ut.commit();
                LOGGER.info("BeanTransactionStatus= " + txReg.getTransactionStatus());
            } catch (BeanReaderException e) {

                LOGGER.info(e.getMessage());
                ut.rollback();
                LOGGER.info("BeanTransactionStatus= " + txReg.getTransactionStatus());
            }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }


    }
}
