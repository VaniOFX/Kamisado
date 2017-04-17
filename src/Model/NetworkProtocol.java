package Model;

import java.io.Serializable;

public class NetworkProtocol implements Serializable{

	private int type;
	private Object message;
	
	public NetworkProtocol(int type){
		this.type = type;
	}
	
	public NetworkProtocol(int type, Object message) {
		this.type = type;
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public Object getMessage() {
		return message;
	}
}
