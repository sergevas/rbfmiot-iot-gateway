package xyz.sergevas.rbfmiot.gateway.service;

import static xyz.sergevas.rbfmiot.gateway.IConstants.LOG;

import xyz.sergevas.rbfmiot.gateway.exception.FieldGatewayTransformException;

public class HonoMessageHandler {
	
	private static final String CLASS_NAME = HonoMessageHandler.class.getCanonicalName();

	public Object createRegistrationMessage(String deviceId) {
		LOG.debug(CLASS_NAME + ".createRegistrationMessage() start...");
		if (deviceId.isEmpty()) {
			String errMsg = String.format("Illegal device Id value [%s]", deviceId);
			LOG.error(errMsg);
			throw new FieldGatewayTransformException(errMsg, deviceId);			
		}
		
		LOG.debug(CLASS_NAME + ".createRegistrationMessage() complete...");
		return null;
	}

}
