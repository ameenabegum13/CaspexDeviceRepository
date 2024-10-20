package com.caspex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.caspex.model.Device;
import com.caspex.model.mysql.MySQLDevice;
import com.caspex.repository.DeviceRepository;
import com.caspex.repository.mysql.MySqlDeviceRepository;

import net.sf.uadetector.ReadableUserAgent;
import net.sf.uadetector.UserAgentStringParser;
import net.sf.uadetector.service.UADetectorServiceFactory;

@Service
public class DeviceService {

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private MySqlDeviceRepository mySqlDeviceRepository;

	public static UserAgentStringParser parser = UADetectorServiceFactory.getResourceModuleParser();

	public Device matchDevice(String userAgentString) {
		if (userAgentString == null)
			return null;
		ReadableUserAgent agent = parser.parse(userAgentString);
		String browserName = agent.getName();
		String browserVersion = agent.getVersionNumber().toVersionString();
		net.sf.uadetector.OperatingSystem os = agent.getOperatingSystem();
		String osName = os.getName();
		String osVersion = os.getVersionNumber().toVersionString();

		// Check if the device exists
		Device device = deviceRepository.findByKey(osName + osVersion + browserName + browserVersion);

		if (device != null) {
			// Increment hit count
			device.setHitCount(device.getHitCount() + 1);
		} else {
			// Create a new device
			device = new Device();
			device.setDeviceId(osName + osVersion + browserName + browserVersion);
			device.setOsName(osName);
			device.setOsVersion(osVersion);
			device.setBrowserName(browserName);
			device.setBrowserVersion(browserVersion);
			device.setHitCount(1);
		}

		deviceRepository.save(device);
		return device;
	}

	public Device getDeviceById(String deviceId) {
		if (deviceId == null)
			return null;
		return deviceRepository.findByKey(deviceId);
	}

	public String deleteDevice(String deviceId) {
		if (deviceId == null)
			return null;
		return deviceRepository.deleteByID(deviceId);
	}

	public List<MySQLDevice> getDevicesByOsName(String osName) {
		System.out.println("device" + osName);
		return mySqlDeviceRepository.findByOsName(osName);
	}

}
