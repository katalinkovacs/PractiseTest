package routes;
import org.apache.camel.builder.RouteBuilder;

public class FileMoveRoute2 extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("file://target/Test2/inbox").to("file://target/Test2/outbox");
    }
}


