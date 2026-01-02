package com.madmagezz.assistant.controller;

import com.madmagezz.assistant.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/pi")
public class DeviceController {

    private DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService service){
        this.deviceService = service;
    }

    @GetMapping("/temp")
    public Mono<Map<String, Object>> getPiTemp(){
        return deviceService.getTempChartData();
    }
}
