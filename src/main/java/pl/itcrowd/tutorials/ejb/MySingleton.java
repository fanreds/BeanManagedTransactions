package pl.itcrowd.tutorials.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.*;
import javax.transaction.TransactionSynchronizationRegistry;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: kleks
 * Date: 1/17/13
 * Time: 10:34 AM
 * To change this template use File | Settings | File Templates.
 */
@Startup
@Singleton
public class MySingleton {

    private static final Logger LOGGER = Logger.getLogger(MySingleton.class.getCanonicalName());

    @EJB
    private BlogPostAuthor blogPostAuthor;

    @EJB
    private TransactionBeanTester transactionBeanTester;



    public MySingleton()
    {

    }
    @Resource
    TransactionSynchronizationRegistry txReg;

    @TransactionAttribute(TransactionAttributeType.NEVER)
    @PostConstruct
    public void onCreate()
    {
        LOGGER.info("Transaction= " + txReg.getTransactionKey());
        blogPostAuthor.createNewBlogPost();
        blogPostAuthor.createNewBlogPost();
        blogPostAuthor.createNewBlogPost();

        transactionBeanTester.exec();


    }

    @PreDestroy
    public void preDestroy()
    {

    }
}