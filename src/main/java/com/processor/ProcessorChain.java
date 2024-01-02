package com.processor;

import com.domain.OnboardingVerificationStatus;
import com.dto.OnboardingVerificationStatusDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mapper.MapperClass;
import com.repository.OnBoardingVerificationStatusRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationAwareOrderComparator;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Component
public class ProcessorChain {

    @Autowired
    List<OnboardingProcessor> onBoardingProcessors;

    @Autowired
    OnBoardingVerificationStatusRepo onBoardingVerificationStatusRepo;

    @Autowired
    MapperClass mapperClass;

    @PostConstruct
    public void initializeOnBoardingProcessorsChain(){
        onBoardingProcessors.sort(AnnotationAwareOrderComparator.INSTANCE);
    }

    public void invokeProcessors(List<OnboardingVerificationStatusDto> onBoardingVerificationStatusDtos, String userId){
        log.info("Chain Started -> invokeFileProcessingChain() - Invoked!");
        List<OnboardingVerificationStatus> onBoardingVerificationStatusList = new ArrayList<>();
        onBoardingProcessors.forEach(fileProcessor -> {
            try {
                fileProcessor.handleProcessorRequest(onBoardingVerificationStatusDtos, userId);
            } catch( JsonProcessingException e ) {
                e.printStackTrace();
            }
        } );
        onBoardingVerificationStatusDtos.forEach(onBoardingVerificationStatusDto -> {
            onBoardingVerificationStatusList.add(mapperClass.documentStoreDtoToDocumentStore(onBoardingVerificationStatusDto));
        });
        onBoardingVerificationStatusRepo.saveAllAndFlush(onBoardingVerificationStatusList);
        log.info("Chain execution completed! {}", onBoardingVerificationStatusDtos);
    }
}
