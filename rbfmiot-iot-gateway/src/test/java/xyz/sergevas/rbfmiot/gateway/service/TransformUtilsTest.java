package xyz.sergevas.rbfmiot.gateway.service;

import org.junit.Test;

import junit.framework.TestCase;

public class TransformUtilsTest extends TestCase {

	@Test
	public void testGetDeviceIdFromTopicName() {
		TransformUtils transformUtils = new TransformUtils();
		String topicName = "env/5ccf7f2f1dc8/status";
		assertEquals("5ccf7f2f1dc8", transformUtils.getDeviceIdFromTopicName(topicName));
		assertEquals("", transformUtils.getDeviceIdFromTopicName(""));
		assertEquals("", transformUtils.getDeviceIdFromTopicName("env/5ccf7f2f1dc8"));
	}
}
