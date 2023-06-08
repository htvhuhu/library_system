package mpp.exception;

import java.io.Serializable;

public class LoginException extends Exception implements Serializable {

	private static final long serialVersionUID = -8800684738974055447L;

	public LoginException(String msg) {
        super(msg);
    }
}
