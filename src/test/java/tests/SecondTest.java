package tests;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.junit.Test;
import routes.FileMoveRoute2;

import java.io.File;


//The FirstTest class must extend the org.apache.camel.junit4.CamelTestSupport class to conveniently leverage the Camel Test Kit.
public class SecondTest extends CamelTestSupport {


    protected RouteBuilder createRouteBuilder() throws Exception {
        return new FileMoveRoute2();
    }

    //ensuring that the starting directory is empty - CamelTestSupport class has a method to delete a directory.
    public void setUp() throws Exception {
        // delete directories so we have a clean start
        deleteDirectory("target/Test2/inbox");
        deleteDirectory("target/Test2/outbox");
        super.setUp();
    }


    @Test
    public void testMoveFile() throws Exception {
        // create a new file in the inbox folder with the name hello.txt and containing Hello World as body
        template.sendBodyAndHeader("file://target/Test2/inbox", "Haliho Katus!", Exchange.FILE_NAME, "katus.txt");

        // wait a while to let the file be moved
        Thread.sleep(2000);

        // test the file was moved
        File target = new File("target/Test2/outbox/katus.txt");
        assertTrue("File should have been moved", target.exists());

        // test that its content is correct as well
        String content = context.getTypeConverter().convertTo(String.class, target);
        assertEquals("Haliho Katus!", content);
    }

    /*

    //The testMoveFile method in listing 6.1 could have been written as follows:

    @Test
    public void testMoveFile() throws Exception {
        template.sendBodyAndHeader("file://target/inbox", "Hello World", Exchange.FILE_NAME, "hello.txt");
        File target = new File("target/outbox/hello.txt");
        assertTrue("File not moved", target.exists());
        String content = context.getTypeConverter()
            .convertTo(String.class, target);
        assertEquals("Hello World", content);
}





*/
}
