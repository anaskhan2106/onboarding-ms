package com.service;

import com.dto.DriverProfileDto;
import com.dto.LoginDto;
import com.dto.LoginMesage;

public interface kafkaConsumerService {
    void onMessage(String payload);
}
