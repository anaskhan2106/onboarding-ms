package com.mapper;

import com.domain.DriverProfile;
import com.domain.DriverProfile.DriverProfileBuilder;
import com.domain.OnboardingVerificationStatus;
import com.dto.OnboardingVerificationStatusDto;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-23T00:41:14+0530",
    comments = "version: 1.4.1.Final, compiler: javac, environment: Java 11.0.19 (Azul Systems, Inc.)"
)
@Component
public class MapperClassImpl extends MapperClass {

    @Override
    public OnboardingVerificationStatus documentStoreDtoToDocumentStore(OnboardingVerificationStatusDto documentStoreDto) {
        if ( documentStoreDto == null ) {
            return null;
        }

        OnboardingVerificationStatus onboardingVerificationStatus = new OnboardingVerificationStatus();

        onboardingVerificationStatus.setDriverProfile( onboardingVerificationStatusDtoToDriverProfile( documentStoreDto ) );
        onboardingVerificationStatus.setId( documentStoreDto.getId() );
        onboardingVerificationStatus.setType( documentStoreDto.getType() );
        onboardingVerificationStatus.setConductedDate( documentStoreDto.getConductedDate() );
        onboardingVerificationStatus.setConductedBy( documentStoreDto.getConductedBy() );
        onboardingVerificationStatus.setComments( documentStoreDto.getComments() );

        return onboardingVerificationStatus;
    }

    protected DriverProfile onboardingVerificationStatusDtoToDriverProfile(OnboardingVerificationStatusDto onboardingVerificationStatusDto) {
        if ( onboardingVerificationStatusDto == null ) {
            return null;
        }

        DriverProfileBuilder driverProfile = DriverProfile.builder();

        driverProfile.userId( onboardingVerificationStatusDto.getUser_id() );

        return driverProfile.build();
    }
}
