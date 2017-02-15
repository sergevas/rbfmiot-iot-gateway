package xyz.sergevas.rbfmiot.gateway;

import static xyz.sergevas.rbfmiot.gateway.IConstants.CAMEL_MQTT_SUBSCRIBE_TOPIC_HEADER_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.CLEAN_SESSION_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.CLIENT_ID_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.DEVICE_TOPIC_PART_STATUS;
import static xyz.sergevas.rbfmiot.gateway.IConstants.DIRECT_FIELD_GATEWAY_DEVICE_REGISTER_SEND_ENDPOINT;
import static xyz.sergevas.rbfmiot.gateway.IConstants.DIRECT_FIELD_GATEWAY_INIT_RECEIVE_ENDPOINT;
import static xyz.sergevas.rbfmiot.gateway.IConstants.DIRECT_FIELD_GATEWAY_TELEMETRY_SEND_ENDPOINT;
import static xyz.sergevas.rbfmiot.gateway.IConstants.KEEP_ALIVE_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.LOG;
import static xyz.sergevas.rbfmiot.gateway.IConstants.MQTT_FIELD_GATEWAY_TELEMETRY_RECIEVE_ENDPOINT;
import static xyz.sergevas.rbfmiot.gateway.IConstants.MQTT_HOST_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.MQTT_QOS_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.PASSWORD_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.PUBLISH_TOPIC_NAME_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.SUBSCRIBE_TOPIC_NAMES_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.*;
import static xyz.sergevas.rbfmiot.gateway.IConstants.WILL_MESSAGE_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.WILL_QOS_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.WILL_RETAIN_PROP_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.WILL_TOPIC_PROP_NAME;

import static org.apache.camel.LoggingLevel.DEBUG;
import org.apache.camel.builder.RouteBuilder;

public class FieldGatewayRoutes extends RouteBuilder {
	
	private static final String CLASS_NAME = FieldGatewayRoutes.class.getName();
	
    public void configure() {
    	LOG.debug("{}.configure() start...", CLASS_NAME);
        
    	from("direct:" + DIRECT_FIELD_GATEWAY_INIT_RECEIVE_ENDPOINT)
    	    .routeId(DIRECT_FIELD_GATEWAY_INIT_RECEIVE_ENDPOINT)
    	    .startupOrder(3)
            .to("mqtt:" + MQTT_FIELD_GATEWAY_INIT_SEND_ENDPOINT + "?" + this.buildMqttFieldGwInitSendUrl());
        
        from("mqtt:" + MQTT_FIELD_GATEWAY_TELEMETRY_RECIEVE_ENDPOINT + "?" + this.buildMqttFieldGwTelemetryReceiveEndpoint())
            .routeId(MQTT_FIELD_GATEWAY_TELEMETRY_RECIEVE_ENDPOINT)
            .startupOrder(20)
            .to("log:" + LOG_NAME + "?level=DEBUG&showAll=true")
            .choice()
              .when(header(CAMEL_MQTT_SUBSCRIBE_TOPIC_HEADER_NAME).endsWith(DEVICE_TOPIC_PART_STATUS))
                .to("direct:" + DIRECT_FIELD_GATEWAY_DEVICE_REGISTER_SEND_ENDPOINT)
              .when(header(CAMEL_MQTT_SUBSCRIBE_TOPIC_HEADER_NAME).in(DEVICE_TOPIC_PART_PRESSURE, DEVICE_TOPIC_PART_HUMIDITY, DEVICE_TOPIC_PART_TEMPERATURE))
                .to("direct:" + DIRECT_FIELD_GATEWAY_TELEMETRY_SEND_ENDPOINT);
        
        from("direct:" + DIRECT_FIELD_GATEWAY_DEVICE_REGISTER_SEND_ENDPOINT)
            .routeId(DIRECT_FIELD_GATEWAY_DEVICE_REGISTER_SEND_ENDPOINT)
            .startupOrder(18)
            .log(DEBUG, LOG_NAME, "Device registrationOrDerigestration start...")
            .choice()
              .when(bodyAs(String.class).isEqualTo(FIELD_GATEWAY_STATUS_ON))
                .log(DEBUG, LOG_NAME, "Device registration start...")
                .bean(TRANSFORM_UTILS, "getDeviceIdFromTopicName")
//                .bean(HONO_MESSAGE_HANDLER, "createRegistrationMessage")
                .to("log:" + LOG_NAME + "?level=DEBUG&showAll=true&multiline");
        
        from("direct:" + DIRECT_FIELD_GATEWAY_TELEMETRY_SEND_ENDPOINT)
            .routeId(DIRECT_FIELD_GATEWAY_TELEMETRY_SEND_ENDPOINT)
            .startupOrder(19)
            .to("log:" + LOG_NAME + "?level=DEBUG&showAll=true");
        LOG.debug("{}.configure() complete...", CLASS_NAME);
    }
    
    private String buildMqttFieldGwTelemetryReceiveEndpoint() {
		String urlStr = null;
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder
		    .append(MQTT_HOST_PROP_NAME + "=" + "tcp://{{MQTT_SERVICE_HOST}}:{{MQTT_SERVICE_PORT}}")
	        .append("&" + CLIENT_ID_PROP_NAME + "=" + "{{MQTT_SERVICE_TELEMETRY_CLIENT_ID}}")
	        .append("&" + USER_NAME_PROP_NAME + "=" + "{{MQTT_SERVICE_USERNAME}}")
	        .append("&" + PASSWORD_PROP_NAME + "=" + "{{MQTT_SERVICE_PASSWORD}}")
	        .append("&" + KEEP_ALIVE_PROP_NAME + "=" + "{{MQTT_SERVICE_KEEP_ALIVE}}")
	        .append("&" + SUBSCRIBE_TOPIC_NAMES_PROP_NAME + "=" + "{{MQTT_SERVICE_TELEMETRY_SUBSCRIBE_TOPIC_NAMES}}")
	        .append("&" + MQTT_QOS_PROP_NAME + "=" + "{{MQTT_SERVICE_QUALITY_OF_SERVICE}}");
		urlStr = urlBuilder.toString();
		LOG.info("{} Have got subscriber Telemetry MQTT URL: {}", CLASS_NAME, urlStr);
		return urlStr;
	}
    
	public String buildMqttFieldGwInitSendUrl() {
    	String urlStr = null;
    	StringBuilder urlBuilder = new StringBuilder();
    	urlBuilder
    	    .append(MQTT_HOST_PROP_NAME + "=" + "tcp://{{MQTT_SERVICE_HOST}}:{{MQTT_SERVICE_PORT}}")
    	    .append("&" + CLIENT_ID_PROP_NAME + "=" + "{{MQTT_SERVICE_STATUS_CLIENT_ID}}")
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
