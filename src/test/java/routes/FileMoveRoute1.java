package routes;
import org.apache.camel.builder.RouteBuilder;

public class FileMoveRoute1 extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("file://target/Test1/inbox").to("file://target/Test1/outbox");
        }
}


