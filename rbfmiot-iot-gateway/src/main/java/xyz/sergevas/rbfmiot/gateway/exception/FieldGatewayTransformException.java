package xyz.sergevas.rbfmiot.gateway.exception;

public class FieldGatewayTransformException extends RuntimeException {

	private static final long serialVersionUID = -5508758103120506550L;
	
	private static final String ERROR_MSG = "Unable to transform a source! ";
	private Object source;

	public FieldGatewayTransformException() {
		super();
	}

	public FieldGatewayTransformException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Object source) {
		super(ERROR_MSG + message, cause, enableSuppression, writableStackTrace);
		this.source = source;
	}

	public FieldGatewayTransformException(String message, Throwable cause, Object source) {
		super(ERROR_MSG + message, cause);
		this.source = source;
	}

	public FieldGatewayTransformException(String message, Object source) {
		super(ERROR_MSG + message);
		this.source = source;
	}

	public FieldGatewayTransformException(Throwable cause, Object source) {
		super(ERROR_MSG, cause);
		this.source = source;
	}
	
	public FieldGatewayTransformException(String source) {
		this.source = source;
	}
	
 	public Object getSource() {
		return this.source;
	}
}
