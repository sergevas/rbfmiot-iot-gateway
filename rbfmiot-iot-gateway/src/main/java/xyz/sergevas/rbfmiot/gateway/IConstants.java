package xyz.sergevas.rbfmiot.gateway;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface IConstants {
	
	public static final Logger LOG = LogManager.getLogger("xyz.sergevas.rbfmiot");
	
	// MQTT
	public static final String MQTT_SERVICE_HOST = "MQTT_SERVICE_HOST";
	public static final String MQTT_SERVICE_PORT = "MQTT_SERVICE_PORT";
	public static final String MQTT_SERVICE_CLIENT_ID = "SERVICE_CLIENT_ID";
    public static final String MQTT_SERVICE_USERNAME = "MQTT_SERVICE_USERNAME";
    public static final String MQTT_SERVICE_PASSWORD = "MQTT_SERVICE_PASSWORD";
    public static final String MQTT_SERVICE_CONNECT_ATTEMPT_MAX = "MQTT_SERVICE_CONNECT_ATTEMPT_MAX";
    public static final String MQTT_SERVICE_RECONNECT_ATTEMPT_MAX = "MQTT_SERVICE_RECONNECT_ATTEMPT_MAX";
    public static final String MQTT_SERVICE_RECONNECT_DELAY = "MQTT_SERVICE_RECONNECT_DELAY";
    public static final String MQTT_SERVICE_RECONNECT_BACKOFF_MULTIPLIER = "MQTT_SERVICE_RECONNECT_BACKOFF_MULTIPLIER";
    public static final String MQTT_SERVICE_RECONNECT_DELAY_MAX = "MQTT_SERVICE_RECONNECT_DELAY_MAX";
    public static final String MQTT_SERVICE_QUALITY_OF_SERVICE = "MQTT_SERVICE_QUALITY_OF_SERVICE";
    public static final String MQTT_SERVICE_BY_DEFAULT_RETAIN = "MQTT_SERVICE_BY_DEFAULT_RETAIN";
    public static final String MQTT_SERVICE_CONNECT_WAIT_IN_SECONDS = "MQTT_SERVICE_CONNECT_WAIT_IN_SECONDS";
    public static final String MQTT_SERVICE_DISCONNECT_WAIT_IN_SECONDS = "MQTT_SERVICE_DISCONNECT_WAIT_IN_SECONDS";
    public static final String MQTT_SERVICE_SEND_WAIT_IN_SECONDS = "MQTT_SERVICE_SEND_WAIT_IN_SECONDS";
    public static final String MQTT_SERVICE_CLEAN_SESSION = "MQTT_SERVICE_CLEAN_SESSION";
    public static final String MQTT_SERVICE_SUBSCRIBE_TOPIC_NAMES = "MQTT_SERVICE_SUBSCRIBE_TOPIC_NAMES";
    public static final String MQTT_SERVICE_KEEP_ALIVE = "MQTT_SERVICE_KEEP_ALIVE";
    public static final String MQTT_SERVICE_WILL_TOPIC = "MQTT_SERVICE_WILL_TOPIC";
    public static final String MQTT_SERVICE_WILL_MESSAGE = "MQTT_SERVICE_WILL_MESSAGE";
    public static final String MQTT_SERVICE_WILL_QOS = "MQTT_SERVICE_WILL_QOS";
    public static final String MQTT_SERVICE_WILL_RETAIN = "MQTT_SERVICE_WILL_RETAIN";
	
    public static final String MQTT_HOST_PROP_NAME = "host";
    public static final String USER_NAME_PROP_NAME = "userName";
    public static final String PASSWORD_PROP_NAME = "password";
    public static final String CONNECT_ATTEMPTS_MAX_PROP_NAME = "connectAttemptsMax";
    public static final String RECONNECT_ATTEMPTS_MAX_PROP_NAME = "reconnectAttemptsMax";
    public static final String RECONNECT_DELAY_PROP_NAME = "reconnectDelay";
    public static final String RECONNECT_BACKOFF_MULTIPLIER_PROP_NAME = "reconnectBackOffMultiplier";
    public static final String RECONNECT_DELAY_MAX_PROP_NAME = "reconnectDelayMax";
    public static final String QUALITY_OF_SERVICE_PROP_NAME = "qualityOfService";
    public static final String SUBSCRIBE_TOPIC_NAMES_PROP_NAME = "subscribeTopicNames";
    public static final String PUBLISH_TOPIC_NAME_PROP_NAME = "publishTopicName";
    public static final String BY_DEFAULT_RETAIN_PROP_NAME = "byDefaultRetain";
	public static final String MQTT_RETAIN_PROPERTY_NAME_PROP_NAME = "mqttRetainPropertyName";
	public static final String CAMEL_MQTT_PUBLISH_TOPIC_PROP_NAME = "CamelMQTTPublishTopic";
	public static final String MQTT_QOS_PROP_NAME = "mqttQosPropertyName";
	public static final String CONNECT_WAIT_IN_SECONDS_PROP_NAME = "connectWaitInSeconds";
	public static final String DISCONNECT_WAIT_IN_SECONDS_PROP_NAME = "disconnectWaitInSeconds";
	public static final String SEND_WAIT_IN_SECONDS_PROP_NAME = "sendWaitInSeconds";
	public static final String CLIENT_ID_PROP_NAME = "clientId";
	public static final String CLEAN_SESSION_PROP_NAME = "cleanSession";
	public static final String KEEP_ALIVE_PROP_NAME = "keepAlive";
	public static final String WILL_TOPIC_PROP_NAME = "willTopic";
	public static final String WILL_MESSAGE_PROP_NAME = "willMessage";
	public static final String WILL_QOS_PROP_NAME = "willQos";
	public static final String WILL_RETAIN_PROP_NAME = "willRetain";
	
	public static final String FIELD_GATEWAY_INIT_ENDPOINT = "field_gw_init";
	public static final String FIELD_GATEWAY_INIT_PRODUCER_ENDPOINT = "field_gw_init_producer";
	public static final String FIELD_GATEWAY_TELEMETRY_SUBSCRIBER_ENDPOINT = "field_gw_telemetry_subscriber";
	
	public static final String FIELD_GATEWAY_STATUS_ON = "on";
}
