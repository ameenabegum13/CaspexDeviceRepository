package com.caspex.repository;


import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Key;
import com.aerospike.client.Record;
import com.aerospike.client.query.IndexType;
import com.caspex.model.Device;
@Repository
public class DeviceRepository {

	private final AerospikeClient aerospikeClient;
	

    @Autowired
    public DeviceRepository(AerospikeClient aerospikeClient) {
        this.aerospikeClient = aerospikeClient;     
    }

    public Device findByKey(String deviceId) {
		Key key = new Key("test", "device", deviceId); 
        Record record = aerospikeClient.get(null, key);         
        return record != null ? convertRecordToDevice(record, deviceId) : null;
    }

   
    public void save(Device device) {    
    	System.out.println("device"+device);
        aerospikeClient.put(null, device.getKey(), device.getBins());
    }

    public String deleteByID(String deviceId) {
        Key key = new Key("test", "device", deviceId); 
        if(aerospikeClient.delete(null,key)) {
        	return "Success";
        }
        return "";
    }
  
    private Device convertRecordToDevice(Record record, String deviceId) {
        return new Device(
            deviceId,
            record.getInt("hitCount"),
            record.getString("osName"),
            record.getString("osVersion"),
            record.getString("browserName"),
            record.getString("browserVersion")
        );
    }
    
    
    
   /* public Device findByOsName(String osName) {
        Key key = new Key("test", "device", osName); 
        Record record = aerospikeClient.get(null, key);    
        aerospikeClient.get(null, key);  
        return record != null ? convertRecordToDevice(record, osName) : null;
    }*/
    
    
	@PostConstruct
	public void createIndex() {
		String namespace = "test"; // your namespace
		String setName = "device"; // your set name
		String binName = "osName"; // the bin name you want to index

		// Create the index
		aerospikeClient.createIndex(null, namespace, setName, "osNameIndex", binName, IndexType.STRING);
	}
  
	/*
	 * public List<Device> findByOsName(String osName) { List<Device> devices = new
	 * ArrayList<>();
	 * 
	 * // Create a statement for the query Statement statement = new Statement();
	 * statement.setNamespace("test"); // Namespace where your data is stored
	 * statement.setSetName("device"); // Set name where your records are
	 * statement.setFilter(Filter.equal("osName", osName)); // Use the index to
	 * filter by OS name
	 * 
	 * // Execute the query RecordSet recordSet = aerospikeClient.query(null,
	 * statement);
	 * 
	 * // Iterate through the results and convert to Device objects while
	 * (recordSet.next()) { Record record = recordSet.getRecord();
	 * 
	 * // Construct the device ID using a known format (assuming deviceId is
	 * generated as a string) String deviceId = record.getString("deviceId"); // Use
	 * the actual bin name for the ID Device device = convertRecordToDevice(record,
	 * deviceId); devices.add(device); } recordSet.close(); // Close the RecordSet
	 * to free up resources return devices; }
	 */
}
