package com.dto;

import com.domain.DocumentStore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DriverProfileDto {

    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String address;

    private String isActive;

    private String isDocumentsVerified;

    private String isReadyForRide;

    private Calendar createdTs;

    private String country;

    private String password;

}

