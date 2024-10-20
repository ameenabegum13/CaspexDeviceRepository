package com.caspex.model.mysql;

import jakarta.persistence.*;

@Entity
@Table(name = "device")
public class MySQLDevice {

	@Id
	@Column(name="device_id")
	private String deviceId;

	@Column(name = "hit_count")
	private int hitCount;

	@Column(name = "os_name")
	private String osName;

	@Column(name = "os_version")
	private String osVersion;

	@Column(name = "browser_name")
	private String browserName;

	@Column(name = "browser_version")
	private String browserVersion;

	// Default constructor
	public MySQLDevice() {
	}

	// Constructor with parameters
	public MySQLDevice(int hitCount, String osName, String osVersion, String browserName, String browserVersion) {
		this.hitCount = hitCount;
		this.osName = osName;
		this.osVersion = osVersion;
		this.browserName = browserName;
		this.browserVersion = browserVersion;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public int getHitCount() {
		return hitCount;
	}

	public void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	public String getOsName() {
		return osName;
	}

	public void setOsName(String osName) {
		this.osName = osName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getBrowserName() {
		return browserName;
	}

	public void setBrowserName(String browserName) {
		this.browserName = browserName;
	}

	public String getBrowserVersion() {
		return browserVersion;
	}

	public void setBrowserVersion(String browserVersion) {
		this.browserVersion = browserVersion;
	}

	@Override
	public String toString() {
		return "MySQLDevice [deviceId=" + deviceId + ", hitCount=" + hitCount + ", osName=" + osName + ", osVersion="
				+ osVersion + ", browserName=" + browserName + ", browserVersion=" + browserVersion + "]";
	}

}
