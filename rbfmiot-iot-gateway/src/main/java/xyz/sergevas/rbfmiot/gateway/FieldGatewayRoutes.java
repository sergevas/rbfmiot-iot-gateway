package xyz.sergevas.rbfmiot.gateway;

import org.apache.camel.builder.RouteBuilder;

public class FieldGatewayRoutes extends RouteBuilder {
	
    public void configure() {

        from("file:src/data?noop=true")
            .choice()
                .when(xpath("/person/city = 'London'"))
                    .log("UK message")
                    .to("file:target/messages/uk")
                .otherwise()
                    .log("Other message")
                    .to("file:target/messages/others");
    }
}
