package backend.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class VehicleDTO {
    private long id;
    private String plateNumbers;
    private String brandAndModel;
    private Double leftTankConverter;
    private Double rightTankConverter;
    private Double leftTankCapacity;
    private Double rightTankCapacity;
    private boolean active;
}
