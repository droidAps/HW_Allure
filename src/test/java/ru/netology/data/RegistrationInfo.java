package ru.netology.data;

import lombok.*;

@Data
@RequiredArgsConstructor
public class RegistrationInfo {
    private final String city;
    private final String userName;
    private final String phoneNumber;
}
