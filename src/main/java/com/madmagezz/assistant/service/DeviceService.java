package com.madmagezz.assistant.service;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Map;


public interface DeviceService {

    Mono<Map<String, Object>> getTempChartData();
}
