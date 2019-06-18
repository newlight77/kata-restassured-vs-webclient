package io.wesquad.wetrot.model;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Trot {
    private UUID uid;
    private String name;
    private int currentBatteryLevel;
    private int batteryCapacity;
    private String localization;
    private String brand;
    private int wearLevel;
    private int wearLife;
}
