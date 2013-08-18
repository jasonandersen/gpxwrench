package gpxwrench.core.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 * Base class for integration tests. This class will handle configuration to
 * bootstrap the Spring application context and configure the Spring testing
 * utilities.
 * @author Jason Andersen andersen.jason@gmail.com
 * @since  Jun 12, 2013
 */
@IntegrationTest
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public abstract class AbstractIntegrationTest {

}
