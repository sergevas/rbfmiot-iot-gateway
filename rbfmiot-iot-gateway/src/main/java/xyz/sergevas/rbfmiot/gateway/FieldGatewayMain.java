package xyz.sergevas.rbfmiot.gateway;

import static xyz.sergevas.rbfmiot.gateway.IConstants.LOG;

import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.main.Main;

public class FieldGatewayMain {

    public static void main(String... args) throws Exception {
    	LOG.info("Field Gateway initialisation start...");
        Main main = new Main();
        main.bind("properties", new PropertiesComponent("classpath:field-gateway.properties"));
        main.addRouteBuilder(new FieldGatewayRoutes());
        main.run(args);
        LOG.info("Field Gateway initialisation complete...");
        try {
        	LOG.info("Run Field Gateway ...");
        	main.run();
        } catch (Exception e) {
        	LOG.error("Unable to run Field Gateway!", e);
        }
    }
}

