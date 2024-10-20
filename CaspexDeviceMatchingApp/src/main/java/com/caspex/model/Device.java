package com.caspex.model;

import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.aerospike.mapping.Field;
import org.springframework.data.annotation.Id;

import com.aerospike.client.Bin;
import com.aerospike.client.Key;

@Document(collection = "device")
public class Device {

	@Id
	private String deviceId; // String identifier for Aerospike

	@Field
	private int hitCount;

	@Field
	private String osName;

	@Field
	private String osVersion;

	@Field
	private String browserName;

	@Field
	private String browserVersion;

	// Default constructor
	public Device() {
	}

	// Constructor with parameters
	public Device(String deviceId, int hitCount, String osName, String osVersion, String browserName,
			String browserVersion) {
		this.deviceId = deviceId;
		this.hitCount = hitCount;
		this.osName = osName;
		this.osVersion = osVersion;
		this.browserName = browserName;
		this.browserVersion = browserVersion;
	}

	// Getters and Setters
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

	// Aerospike-specific methods for key and bins
	public Key getKey() {
		return new Key("test", "device", deviceId);
	}

	public Bin[] getBins() {
		return new Bin[] { new Bin("hitCount", hitCount), new Bin("osName", osName), new Bin("osVersion", osVersion),
				new Bin("browserName", browserName), new Bin("browserVersion", browserVersion) };
	}

	@Override
	public String toString() {
		return "Device [deviceId=" + deviceId + ", hitCount=" + hitCount + ", osName=" + osName + ", osVersion="
				+ osVersion + ", browserName=" + browserName + ", browserVersion=" + browserVersion + "]";
	}

}
