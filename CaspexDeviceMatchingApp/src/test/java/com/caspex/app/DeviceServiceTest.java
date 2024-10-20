package com.caspex.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.caspex.model.Device;
import com.caspex.model.mysql.MySQLDevice;
import com.caspex.repository.DeviceRepository;
import com.caspex.repository.mysql.MySqlDeviceRepository;
import com.caspex.service.DeviceService;

public class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private MySqlDeviceRepository mySqlDeviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testMatchDeviceExistingDevice() {
        String userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36";
        
        // Set up expected behavior
        Device existingDevice = new Device();
        existingDevice.setDeviceId("Windows10Chrome85.0.4183.121");
        existingDevice.setHitCount(1);
        
        when(deviceRepository.findByKey(anyString())).thenReturn(existingDevice);
        
        // Call the method
        Device result = deviceService.matchDevice(userAgentString);
        
        // Verify the results
        assertNotNull(result);
        assertEquals(2, result.getHitCount()); // Since we're incrementing hit count
        verify(deviceRepository, times(1)).save(existingDevice); // Ensure save was called
    }

    @Test
    public void testMatchDeviceNewDevice() {
        String userAgentString = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36";
        
        // Set up expected behavior
        when(deviceRepository.findByKey(anyString())).thenReturn(null); // New device scenario
        
        // Call the method
        Device result = deviceService.matchDevice(userAgentString);
        
        // Verify the results
        assertNotNull(result);
        assertEquals("Windows", result.getOsName()); // Check OS name
        assertEquals(1, result.getHitCount()); // Hit count should be 1 for new device
        verify(deviceRepository, times(1)).save(result); // Ensure save was called
    }

    @Test
    public void testDeleteDevice() {
        String deviceId = "deviceId123";
        
        // Mock the behavior
        when(deviceRepository.deleteByID(deviceId)).thenReturn("Device deleted");
        
        // Call the method
        String result = deviceService.deleteDevice(deviceId);
        
        // Verify the results
        assertEquals("Device deleted", result);
        verify(deviceRepository, times(1)).deleteByID(deviceId); // Ensure delete was called
    }
    
    @Test
    public void testGetDevicesByOsName() {
        // Given
        String osName = "Windows";
        
        // Create a list of MySQLDevice objects to return
        MySQLDevice device1 = new MySQLDevice();
        device1.setDeviceId("iOS14.0Mobile Safari14.0"); // Set deviceId for MySQLDevice
        device1.setOsName(osName);
        device1.setHitCount(5);

        MySQLDevice device2 = new MySQLDevice();
        device2.setDeviceId("Windows10.0Chrome87.0.4280.66"); // Set deviceId for MySQLDevice
        device2.setOsName(osName);
        device2.setHitCount(3);

        List<MySQLDevice> expectedDevices = Arrays.asList(device1, device2);

        // When
        when(mySqlDeviceRepository.findByOsName(osName)).thenReturn(expectedDevices);

        // Call the service method
        List<MySQLDevice> result = deviceService.getDevicesByOsName(osName);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(device1, result.get(0));
        assertEquals(device2, result.get(1));

        // Verify that the repository method was called
        verify(mySqlDeviceRepository, times(1)).findByOsName(osName);
    }
}
