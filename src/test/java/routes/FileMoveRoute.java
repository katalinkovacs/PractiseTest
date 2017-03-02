package routes;
import org.apache.camel.builder.RouteBuilder;

public class FileMoveRoute extends RouteBuilder {

        @Override
        public void configure() throws Exception {
            from("file://target/inboxfirsttest").to("file://target/outboxfirsttest");
        }
}


