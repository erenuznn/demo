package com.youtubedemo.demo.device;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class deviceService {

    private final deviceRepository deviceRepository;

    @Autowired
    public deviceService(com.youtubedemo.demo.device.deviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }


    public void addNewDevice(device device) {
        Optional<device> deviceValidation = deviceRepository
                .findIfExists(device.getBrand(),device.getModel(),device.getOsVersion()); // Aynı Brand,Model ve osVersion'a sahip mi diye kontrol edilir.

        if (deviceValidation.isPresent()){
            throw new IllegalStateException("Aynı marka, model ve işletim sistemi sürümüne sahip bir cihaz zaten var.");
        }

        String valid_Brand = device.getBrand();
        if (valid_Brand == null || valid_Brand.length() == 0){
            throw new IllegalStateException("Marka adı boş olamaz.");
        }

        String valid_Model = device.getModel();
        if (valid_Model == null || valid_Model.length() == 0){
            throw new IllegalStateException("Model adı boş olamaz.");
        }

        String valid_os = device.getOs();
        if (valid_os == null || valid_os.length() == 0){
            throw new IllegalStateException("İşletim sistemi adı boş olamaz.");
        }
        if (!valid_os.equals("Android") && !valid_os.equals("ios")){
            throw new IllegalStateException("İşletim sistemi sadece Android veya ios olabilir.");
        }

        String valid_osVersion = device.getOsVersion();
        if (valid_osVersion == null || valid_osVersion.length() == 0){
            throw new IllegalStateException("İşletim sistemi versiyonu boş olamaz.");
        }

        deviceRepository.save(device); //Database'e kayıt yapılır.
        System.out.println(device.getId());  //Database'e kayıt yapıldıktan sonra ID değeri return edilir.
    }

    public void deleteDevice(int deviceId) {
       boolean exists = deviceRepository.existsById(deviceId);
       if (!exists){
           new IllegalStateException("Bu ID numarasına sahip cihaz bulunamadı.");
       }

       deviceRepository.deleteById(deviceId);
    }


    public List<device> getByEverything(String brand, String model, String os, String osVersion){
        return deviceRepository.findByEverything(brand,model,os,osVersion);

    }
}
