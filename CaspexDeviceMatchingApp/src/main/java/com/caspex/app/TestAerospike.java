package com.caspex.app;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.aerospike.client.Record;

public class TestAerospike {
    public static void main(String[] args) {
        AerospikeClient client = new AerospikeClient("localhost", 3000);
        Key key = new Key("test", "device", "device1");
        
        // Insert a record
        client.put(null, key, new Bin("osName", "Windows"), new Bin("hitCount", 1));
        
        // Retrieve the record
        Record record = client.get(null, key);
        System.out.println("OS Name: " + record.getString("osName"));
        System.out.println("Hit Count: " + record.getInt("hitCount"));

        client.close();
    }
}