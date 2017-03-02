/**
 * Unit test with the Camel Test Kit testing Spring XML routes.
 * Test the Hello World example of integration kits, which is moving a file.
 */

package springfirsttest;

import org.apache.camel.Exchange;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;



public class SpringFirstTest extends CamelSpringTestSupport {

    public void setUp() throws Exception {
        // delete directories so we have a clean start
        deleteDirectory("target/inboxSpringTest");
        deleteDirectory("target/outboxSpringTest");
        super.setUp();
    }

    @Override
    protected AbstractXmlApplicationContext createApplicationContext() {
        return new ClassPathXmlApplicationContext("camelinaction/springFirstTest.xml");
    }

    @Test
    public void testMoveFile() throws Exception {
        // create a new file in the inbox folder with the name hello.txt and containing Hello World as body
        template.sendBodyAndHeader("file://target/inboxSpringTest", "Hello Kati", Exchange.FILE_NAME, "hellokati.txt");

        // wait a while to let the file be moved
        Thread.sleep(2000);

        // test the file was moved
        File target = new File("target/outboxSpringTest/hellokati.txt");
        assertTrue("File should have been moved", target.exists());

        // test that its content is correct as well
        String content = context.getTypeConverter().convertTo(String.class, target);
        assertEquals("Hello Kati", content);
    }

}

