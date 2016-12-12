package com.huang.TibetanLibrary.domain;

public class DialectDetial {
	private long ID;
	private long ddtimestamp;
	private String locationCode;
	private String locationDes;
	public long getID() {
		return ID;
	}
	public void setID(long iD) {
		ID = iD;
	}
	public long getDdtimestamp() {
		return ddtimestamp;
	}
	public void setDdtimestamp(long ddtimestamp) {
		this.ddtimestamp = ddtimestamp;
	}
	public String getLocationCode() {
		return locationCode;
	}
	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}
	public String getLocationDes() {
		return locationDes;
	}
	public void setLocationDes(String locationDes) {
		this.locationDes = locationDes;
	}
}
