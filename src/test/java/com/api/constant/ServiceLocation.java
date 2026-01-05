package com.api.constant;

public enum ServiceLocation {

	SERVICE_LOCATION_A(1);

	int code;

	private ServiceLocation(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}
	
	
}
