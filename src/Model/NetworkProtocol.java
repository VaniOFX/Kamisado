package Model;

import java.io.Serializable;

public class NetworkProtocol implements Serializable{

	private int type;
	private Object message;
	
	public static final int STATE = 0;
	public static final int LEFTTORIGHT = 1;
	public static final int RIGHTTOLEFT = 2;
	public static final int BOARD = 3;
	
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
