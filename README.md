# CaspexDeviceRepository
CaspexDeviceRepository

1)Match Device:

EndPoint:
http://localhost:8080/devices/matchDevice
Method: Post

RequestBody:

"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.66 Safari/537.36"

Response

{
    "deviceId": "Windows10.0Chrome85.0.4183.121",
    "hitCount": 3,
    "osName": "Windows",
    "osVersion": "10.0",
    "browserName": "Chrome",
    "browserVersion": "85.0.4183.121",
    "key": {
        "namespace": "test",
        "setName": "device",
        "digest": "h0OixcfzZKgpN/aNJ+H0SAo2gao=",
        "userKey": {
            "type": 3,
            "object": "Windows10.0Chrome85.0.4183.121"
        }
    },
    "bins": [
        {
            "name": "hitCount",
            "value": {
                "type": 1,
                "object": 3
            }
        },
        {
            "name": "osName",s
            "value": {
                "type": 3,
                "object": "Windows"
            }
        },
        {
            "name": "osVersion",
            "value": {
                "type": 3,
                "object": "10.0"
            }
        },
        {
            "name": "browserName",
            "value": {
                "type": 3,
                "object": "Chrome"
            }
        },
        {
            "name": "browserVersion",
            "value": {
                "type": 3,
                "object": "85.0.4183.121"
            }
        }
    ]
}




2)Get a Device by ID: 
EndPoint:
http://localhost:8080/devices/Windows10.0Chrome85.0.4183.121
Method: GET
Response:
{
    "deviceId": "Windows10.0Chrome85.0.4183.121",
    "hitCount": 3,
    "osName": "Windows",
    "osVersion": "10.0",
    "browserName": "Chrome",
    "browserVersion": "85.0.4183.121",
    "key": {
        "namespace": "test",
        "setName": "device",
        "digest": "h0OixcfzZKgpN/aNJ+H0SAo2gao=",
        "userKey": {
            "type": 3,
            "object": "Windows10.0Chrome85.0.4183.121"
        }
    },
    "bins": [
        {
            "name": "hitCount",
            "value": {
                "type": 1,
                "object": 3
            }
        },
        {
            "name": "osName",
            "value": {
                "type": 3,
                "object": "Windows"
            }
        },
        {
            "name": "osVersion",
            "value": {
                "type": 3,
                "object": "10.0"
            }
        },
        {
            "name": "browserName",
            "value": {
                "type": 3,
                "object": "Chrome"
            }
        },
        {
            "name": "browserVersion",
            "value": {
                "type": 3,
                "object": "85.0.4183.121"
            }
        }
    ]
}


3)Delete a Device/s: 
EndPoint:
http://localhost:8080/devices/Windows10.0Chrome85.0.4183.121
Method: DELETE
Response
  Success

4)Get all Devices for a given OS name:
EndPoint:
http://localhost:8080/devices/os/Windows
Method: GET
Response
 [
    {
        "deviceId": "Windows10.0Chrome87.0.4280.66",
        "hitCount": 1,
        "osName": "Windows",
        "osVersion": "10.0",
        "browserName": "Chrome",
        "browserVersion": "87.0.4280.66"
    }
]

SetUp Details:

1)Docker Download and Installation:
Download and install Docker from below URL
https://docs.docker.com/get-started/get-docker/

2)Pull AeroSpike image from Docker
Reference:
https://aerospike.com/docs/server/operations/install/docker-desktop
Follow below commands to connect AeroSpike via Docker.
1)docker pull aerospike/aerospike-server-enterprise
2)docker run -d --name aerospike -p 3000-3002:3000-3002 aerospike/aerospike-server-enterprise
3)docker inspect -f "{{ .NetworkSettings.IPAddress }}" aerospike
4)docker run -it aerospike/aerospike-tools aql -h 172.17.0.2


Running the Application:

Right click on project CaspexDeviceMatchingApp Run as  Spring Boot Application.

Testing the Apllication:

Download postman tool and test the below Rest API by following first added ends points and request.

1)Match Device
2)Get a Device by ID
3)Delete a Device/s
4)Get all Devices for a given OS name


Note: Get all Devices for a given OS name: getting Unsupported Server Feature using 
      Aerospike as the database for this API. Have done this API using MYSQL database.

Setup: Install MySQL WorkBench

Reference: https://www.youtube.com/watch?app=desktop&v=BxdSUGBs0gM


Error Log for to get devices by OS name:

com.aerospike.client.AerospikeException: Error 16,1,30000,0,5,BB9020011AC4202 127.0.0.1 3000: Unsupported Server Feature
	at com.aerospike.client.command.MultiCommand.parseGroup(MultiCommand.java:242) ~[aerospike-client-5.0.0.jar:na]
	at com.aerospike.client.command.MultiCommand.parseResult(MultiCommand.java:219) ~[aerospike-client-5.0.0.jar:na]
	at com.aerospike.client.command.SyncCommand.executeCommand(SyncCommand.java:103) ~[aerospike-client-5.0.0.jar:na]
	at com.aerospike.client.command.SyncCommand.execute(SyncCommand.java:64) ~[aerospike-client-5.0.0.jar:na]
	at com.aerospike.client.command.MultiCommand.executeAndValidate(MultiCommand.java:98) ~[aerospike-client-5.0.0.jar:na]
	at com.aerospike.client.query.QueryExecutor$QueryThread.run(QueryExecutor.java:134) ~[aerospike-client-5.0.0.jar:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:840) ~[na:na]

