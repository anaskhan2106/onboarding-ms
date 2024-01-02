package com.repository;

import com.domain.OnboardingVerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface OnBoardingVerificationStatusRepo extends JpaRepository<OnboardingVerificationStatus,Integer>
{
    @Query( value = "INSERT INTO onboarding_verification_status () VALUES ", nativeQuery =
            true )
    Optional<OnboardingVerificationStatus> saveOnboardingVerificationStatus(@Param( "userId" ) String userId, @Param( "type" ) String type);

}
