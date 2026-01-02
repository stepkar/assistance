package com.madmagezz.assistant.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@Service
public class DeviceServiceImpl implements DeviceService{

    @Override
    public Mono<Map<String, Object>> getTempChartData(){
        return Mono.fromCallable(this::prepareTempData);
    }

    private Map<String, Object> prepareTempData(){
        double temp = getTemperature();

        double [] temps = {temp, temp - 1.0, temp - 2.0, temp - 0.5};
        String [] labels = {"Now", "1 min ago", "2 min ago", "3 min ago"};

        Map<String, Object> chartData = new HashMap<>();
        chartData.put("labels", labels);
        chartData.put("data", temps);
        chartData.put("type", "line");
        chartData.put("title", "Pi Temperature");

        return chartData;
    }

    private double getTemperature(){
        try{
            Process process = Runtime.getRuntime().exec("vcgencmd measure_temp");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = bufferedReader.readLine();
            System.out.println(line);
            return Double.parseDouble(line.replace("temp=", "").replace("'C", ""));
        }catch (Exception e){
            return 999.99;
        }
    }
}
