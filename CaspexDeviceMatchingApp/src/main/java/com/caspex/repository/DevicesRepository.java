package com.caspex.repository;

import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

import com.caspex.model.Device;

@Repository
public interface DevicesRepository extends AerospikeRepository<Device, String> {
	
	
}
