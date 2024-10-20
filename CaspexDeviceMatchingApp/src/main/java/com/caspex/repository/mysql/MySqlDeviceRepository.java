package com.caspex.repository.mysql;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.caspex.model.mysql.MySQLDevice;

@Repository
public interface MySqlDeviceRepository extends JpaRepository<MySQLDevice, String> {
    List<MySQLDevice> findByOsName(String osName);
}