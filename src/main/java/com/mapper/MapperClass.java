package com.mapper;

import com.domain.OnboardingVerificationStatus;
import com.dto.OnboardingVerificationStatusDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;



@Mapper(componentModel = "spring")
public abstract class MapperClass {

    @Mappings({
            @Mapping(source = "user_id", target = "driverProfile.userId")})
    public abstract OnboardingVerificationStatus documentStoreDtoToDocumentStore(OnboardingVerificationStatusDto documentStoreDto);
}

