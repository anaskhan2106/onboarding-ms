package com.processor;

import com.dto.OnboardingVerificationStatusDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.repository.OnBoardingVerificationStatusRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
@Order( 1 )
public class DocumentsCollectionProcessor implements OnboardingProcessor{

    @Autowired
    private OnBoardingVerificationStatusRepo onBoardingVerificationStatusRepo;

    @Override
    public void handleProcessorRequest(List<OnboardingVerificationStatusDto> onboardingVerificationStatusDtos, String userId) throws JsonProcessingException {
        Random generator=new Random();
        OnboardingVerificationStatusDto onboardingVerificationStatusDto = new OnboardingVerificationStatusDto();
        onboardingVerificationStatusDto.setId(generator.nextInt());
        onboardingVerificationStatusDto.setUser_id(userId);
        onboardingVerificationStatusDto.setCompleted(false);
        onboardingVerificationStatusDto.setType("DOCUMENT");
        onboardingVerificationStatusDtos.add(onboardingVerificationStatusDto);
    }
}
