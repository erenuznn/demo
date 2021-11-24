package com.youtubedemo.demo.device;

import org.json.JSONArray;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@Configuration
public class deviceConfig {

         @Bean
    CommandLineRunner commandLineRunner(
            deviceRepository repository){
        return args -> {


                try {
                    String text = new String(Files.readAllBytes(Paths.get("C:\\Users\\ERENUZUN\\Desktop\\demo\\src\\main\\java\\com\\youtubedemo\\demo\\device\\devices.json")), StandardCharsets.UTF_8);

                    JSONArray arr = new JSONArray(text);

                    for (int i = 0; i < arr.length(); i++) {
                        device device = new device();
                        String model = arr.getJSONObject(i).getString("model");
                        String brand = arr.getJSONObject(i).getString("brand");
                        String os = arr.getJSONObject(i).getString("os");
                        String osVersion = arr.getJSONObject(i).getString("osVersion");
                        device.setModel(model);
                        device.setBrand(brand);
                        device.setOs(os);
                        device.setOsVersion(osVersion);
                        System.out.println(model + brand + os + osVersion);


                        Optional<device> deviceValidation = repository.findIfExists(brand,model,osVersion);
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
                        if (!valid_os.equals("Android") && !valid_os.equals("iOS")){
                            throw new IllegalStateException("İşletim sistemi sadece Android veya ios olabilir.");
                        }

                        String valid_osVersion = device.getOsVersion();
                        if (valid_osVersion == null || valid_osVersion.length() == 0){
                            throw new IllegalStateException("İşletim sistemi versiyonu boş olamaz.");
                        }

                        repository.save(device);
                    }

                } catch (Exception ex) {
                    System.out.println(ex.toString());




                }

            };

    }
    }


