package com.caspex.config;


import java.util.Collection;
import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.config.AbstractAerospikeDataConfiguration;
import org.springframework.data.aerospike.config.AerospikeDataSettings;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Host;
import com.aerospike.client.policy.ClientPolicy;

@Configuration
public class AerospikeConfig extends AbstractAerospikeDataConfiguration{

    @Bean
    public AerospikeClient aerospikeClient() {
        ClientPolicy clientPolicy = new ClientPolicy();
        
        Host[] hosts = { new Host("localhost", 3000) }; 

        return new AerospikeClient(clientPolicy, hosts);
    }
    
    @Override
    protected Collection<Host> getHosts() {
        return Collections.singleton(new Host("localhost", 3000));
    }

    @Override
    protected String nameSpace() {
        return "test";
    }
    
    @Bean
    public AerospikeDataSettings aerospikeDataSettings() {
      return AerospikeDataSettings.builder().scansEnabled(true).sendKey(true).build();
    }


    
}
