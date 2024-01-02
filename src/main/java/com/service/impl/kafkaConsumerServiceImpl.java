package com.service.impl;

import com.domain.DriverProfile;
import com.dto.DriverProfileDto;
import com.dto.LoginDto;
import com.dto.OnboardingVerificationStatusDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.processor.ProcessorChain;
import com.repository.DriverProfileRepo;
import com.service.kafkaConsumerService;
import com.dto.LoginMesage;
import com.validator.ProfileInformationValidator;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.requests.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
@Slf4j
@Service
public class kafkaConsumerServiceImpl implements kafkaConsumerService {

    @Autowired
    private DriverProfileRepo driverProfileRepo;

    @Autowired
    private ProcessorChain processorChain;

    @Value( "${topicName}" )
    String topicName;

    /**
     * On message.
     *
     * @param payload this json string has String JSON which contains the nature of operation and other event related data
     */
    @KafkaListener(topics = "canopus.index.dev",
            containerFactory = "kafkaListenerContainerFactory")
    @Override
    public void onMessage(String payload) {
        try{
            log.info( "Received message : {} from topic : {} ", payload, "on boarding-process");
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> payloadMap = objectMapper.readValue(payload, Map.class);
            String eventName=payloadMap.get("ename").toString();
            String userId=payloadMap.get("userID").toString();
            if(eventName.equalsIgnoreCase("StartOnBoarding")){
                processorChain.invokeProcessors(new ArrayList<>(), userId);
            }
        }
        catch(JsonProcessingException e){
            log.error("Error in processing message", e.getMessage());
        }

    }
}
