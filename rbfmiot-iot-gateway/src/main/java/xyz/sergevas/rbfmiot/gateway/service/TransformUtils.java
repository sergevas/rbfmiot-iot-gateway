package xyz.sergevas.rbfmiot.gateway.service;


import static xyz.sergevas.rbfmiot.gateway.IConstants.CAMEL_MQTT_SUBSCRIBE_TOPIC_HEADER_NAME;
import static xyz.sergevas.rbfmiot.gateway.IConstants.DELIMETER;
import static xyz.sergevas.rbfmiot.gateway.IConstants.DEVICE_TOPIC_PART_ENV;
import static xyz.sergevas.rbfmiot.gateway.IConstants.EMPTY_STR;
import static xyz.sergevas.rbfmiot.gateway.IConstants.LENGTH_OF_DEVICE_ID_PREFIX;
import static xyz.sergevas.rbfmiot.gateway.IConstants.LOG;

import java.util.Optional;
import java.util.function.Function;

import org.apache.camel.Header;

public class TransformUtils {
	
	private static final String CLASS_NAME = TransformUtils.class.getName();
	
	public String getDeviceIdFromTopicName(@Header(CAMEL_MQTT_SUBSCRIBE_TOPIC_HEADER_NAME) String topicName) {
		LOG.debug(CLASS_NAME + "getDeviceIdFromTopicName() start...");
		String deviceId = Optional.ofNullable(topicName).map(new Function<String, String>() {
			@Override
			public String apply(String aTopicHeader) {
				String deviceId = null;
				int topicHeaderLength = aTopicHeader.length();
				if (topicHeaderLength > LENGTH_OF_DEVICE_ID_PREFIX) {
					int startIdx = aTopicHeader.indexOf(DEVICE_TOPIC_PART_ENV + DELIMETER) + LENGTH_OF_DEVICE_ID_PREFIX;
					int endIdx = aTopicHeader.indexOf(DELIMETER, startIdx);
					if (endIdx != -1) {
					    deviceId = aTopicHeader.substring(startIdx, endIdx);
					}
				}
				return deviceId;
			};
		}).orElse(EMPTY_STR);
		LOG.debug("deviceId={} " + CLASS_NAME + "getDeviceIdFromTopicName() complete...", deviceId);
		return deviceId;
	}
}
