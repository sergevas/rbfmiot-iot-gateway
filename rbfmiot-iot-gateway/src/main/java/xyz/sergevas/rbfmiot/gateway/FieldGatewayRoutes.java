package xyz.sergevas.rbfmiot.gateway;

import static xyz.sergevas.rbfmiot.gateway.IConstants.CLEAN_SESSION_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.CLIENT_ID_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.FIELD_GATEWAY_INIT_ENDPOINT;
import static xyz.sergevas.rbfmiot.gateway.IConstants.FIELD_GATEWAY_INIT_PRODUCER_ENDPOINT;
import static xyz.sergevas.rbfmiot.gateway.IConstants.FIELD_GATEWAY_TELEMETRY_SUBSCRIBER_ENDPOINT;
import static xyz.sergevas.rbfmiot.gateway.IConstants.KEEP_ALIVE_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.LOG;
import static xyz.sergevas.rbfmiot.gateway.IConstants.MQTT_HOST_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.MQTT_QOS_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.PASSWORD_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.PUBLISH_TOPIC_NAME_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.SUBSCRIBE_TOPIC_NAMES_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.USER_NAME_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.WILL_MESSAGE_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.WILL_QOS_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.WILL_RETAIN_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.WILL_TOPIC_PROP_NAME;

import org.apache.camel.builder.RouteBuilder;

public class FieldGatewayRoutes extends RouteBuilder {
	
	private static final String CLASS_NAME = FieldGatewayRoutes.class.getName();
	
    public void configure() {
    	LOG.debug("{}.configure() start...", CLASS_NAME);
        
    	from("direct:" + FIELD_GATEWAY_INIT_ENDPOINT)
    	    .routeId(FIELD_GATEWAY_INIT_ENDPOINT)
    	    .startupOrder(3)
            .to("mqtt:" + FIELD_GATEWAY_INIT_PRODUCER_ENDPOINT + "?" + this.buildInitializationUrl());
        
        from("mqtt:" + FIELD_GATEWAY_TELEMETRY_SUBSCRIBER_ENDPOINT + "?" + this.buildSubscriberTelemetryEndpoint())
            .routeId(FIELD_GATEWAY_TELEMETRY_SUBSCRIBER_ENDPOINT)
            .startupOrder(10)
            .to("log:" + LOG.getName() + "?level=DEBUG&showAll=true");
        LOG.debug("{}.configure() complete...", CLASS_NAME);
    }
    
    private String buildSubscriberTelemetryEndpoint() {
		String urlStr = null;
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder
		    .append(MQTT_HOST_PROP_NAME + "=" + "tcp://{{MQTT_SERVICE_HOST}}:{{MQTT_SERVICE_PORT}}")
	        .append("&" + CLIENT_ID_PROP_NAME + "=" + "{{MQTT_SERVICE_CLIENT_ID}}")
	        .append("&" + USER_NAME_PROP_NAME + "=" + "{{MQTT_SERVICE_USERNAME}}")
	        .append("&" + PASSWORD_PROP_NAME + "=" + "{{MQTT_SERVICE_PASSWORD}}")
	        .append("&" + KEEP_ALIVE_PROP_NAME + "=" + "{{MQTT_SERVICE_KEEP_ALIVE}}")
	        .append("&" + SUBSCRIBE_TOPIC_NAMES_PROP_NAME + "=" + "{{MQTT_SERVICE_TELEMETRY_SUBSCRIBE_TOPIC_NAMES}}")
	        .append("&" + MQTT_QOS_PROP_NAME + "=" + "{{MQTT_SERVICE_QUALITY_OF_SERVICE}}");
		urlStr = urlBuilder.toString();
		LOG.info("{} Have got subscriber Telemetry MQTT URL: {}", CLASS_NAME, urlStr);
		return urlStr;
	}

	public String buildInitializationUrl() {
    	String urlStr = null;
    	StringBuilder urlBuilder = new StringBuilder();
    	urlBuilder
    	    .append(MQTT_HOST_PROP_NAME + "=" + "tcp://{{MQTT_SERVICE_HOST}}:{{MQTT_SERVICE_PORT}}")
    	    .append("&" + CLIENT_ID_PROP_NAME + "=" + "{{MQTT_SERVICE_CLIENT_ID}}")
    	    .append("&" + USER_NAME_PROP_NAME + "=" + "{{MQTT_SERVICE_USERNAME}}")
    	    .append("&" + PASSWORD_PROP_NAME + "=" + "{{MQTT_SERVICE_PASSWORD}}")
    	    .append("&" + KEEP_ALIVE_PROP_NAME + "=" + "{{MQTT_SERVICE_KEEP_ALIVE}}")
    	    .append("&" + PUBLISH_TOPIC_NAME_PROP_NAME + "=" + "{{MQTT_SERVICE_WILL_TOPIC}}")
    	    .append("&" + CLEAN_SESSION_PROP_NAME + "=" + "{{MQTT_SERVICE_CLEAN_SESSION}}")
    	    .append("&" + WILL_TOPIC_PROP_NAME + "=" + "{{MQTT_SERVICE_WILL_TOPIC}}")
    	    .append("&" + WILL_MESSAGE_PROP_NAME + "=" + "{{MQTT_SERVICE_WILL_MESSAGE}}")
    	    .append("&" + WILL_QOS_PROP_NAME + "=" + "{{MQTT_SERVICE_WILL_QOS}}")
    	    .append("&" + WILL_RETAIN_PROP_NAME + "=" + "{{MQTT_SERVICE_WILL_RETAIN}}");
    	urlStr = urlBuilder.toString();
    	LOG.info("Have got initialization MQTT URL: {}", urlStr);
    	return urlStr;
    }
}
