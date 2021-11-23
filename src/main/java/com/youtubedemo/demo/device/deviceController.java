package com.youtubedemo.demo.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "api/v1/device")
public class deviceController {


    private final deviceService deviceService;

    @Autowired
    public deviceController(com.youtubedemo.demo.device.deviceService deviceService) {
        this.deviceService =  deviceService;
    }

    @PostMapping
    public void registerNewDevice(@RequestBody device device){
        deviceService.addNewDevice(device);
    }

    @DeleteMapping(path = "{deviceId}")
    public void deleteDevice(@PathVariable("deviceId") int deviceId){
        deviceService.deleteDevice(deviceId);
    }

    @GetMapping
    public List<device> getDevices(@RequestParam(required = false, name = "brand") String brand,
                                       @RequestParam(required = false, name = "model") String model,
                                       @RequestParam(required = false, name = "os") String os,
                                       @RequestParam(required = false, name = "osVersion") String osVersion
    ){

        return deviceService.getByEverything(brand,model,os,osVersion);

    }





}
