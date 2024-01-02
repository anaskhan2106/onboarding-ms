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
@Order( 3 )
public class TrackingDeviceProcessor implements OnboardingProcessor{

    @Autowired
    private OnBoardingVerificationStatusRepo onBoardingVerificationStatusRepo;

    @Override
    public void handleProcessorRequest(List<OnboardingVerificationStatusDto> onboardingVerificationStatusDtos, String userId) throws JsonProcessingException {
        Random generator=new Random();
        OnboardingVerificationStatusDto onboardingVerificationStatusDto = new OnboardingVerificationStatusDto();
        onboardingVerificationStatusDto.setUser_id(userId);
        onboardingVerificationStatusDto.setId(generator.nextInt());
        onboardingVerificationStatusDto.setCompleted(false);
        onboardingVerificationStatusDto.setType("TRACKING_DEVICE");
        onboardingVerificationStatusDtos.add(onboardingVerificationStatusDto);
    }
}
