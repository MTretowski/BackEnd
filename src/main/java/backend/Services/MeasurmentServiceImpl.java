package backend.Services;

import backend.DTOs.ErrorMessageDTO;
import backend.DTOs.MeasurmentDTO;
import backend.Entities.Measurment;
import backend.Entities.Trip;
import backend.Repositories.MeasurmentRepository;
import backend.Repositories.TripRepository;
import backend.Repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MeasurmentServiceImpl implements MeasurmentService {

    private MeasurmentRepository measurmentRepository;
    private TripRepository tripRepository;
    private VehicleRepository vehicleRepository;

    @Autowired
    public MeasurmentServiceImpl(MeasurmentRepository measurmentRepository, TripRepository tripRepository, VehicleRepository vehicleRepository) {
        this.measurmentRepository = measurmentRepository;
        this.tripRepository = tripRepository;
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public List<MeasurmentDTO> findAll() {
        List<Measurment> measurmentsInDatabase = measurmentRepository.findAll();
        List<MeasurmentDTO> measurmentDTOS = new ArrayList<>(measurmentsInDatabase.size());

        for (Measurment measurment : measurmentsInDatabase) {
            measurmentDTOS.add(new MeasurmentDTO(
                    measurment.getId(),
                    new Date(measurment.getDate().getTime()),
                    measurment.getLeftFuelTank(),
                    measurment.getRightFuelTank(),
                    calculateLeftFuelTankAmount(measurment),
                    calculateRightFuelTankAmount(measurment),
                    measurment.isManualMeasurment(),
                    measurment.isReturnToFull(),
                    measurment.isMeasuredAmount(),
                    getMeasurmentWay(measurment),
                    measurment.getVehicleId(),
                    vehicleRepository.findById(measurment.getVehicleId()).getPlateNumbers(),
                    getStartedTrip(measurment.getId()),
                    getEndedTrip(measurment.getId())
            ));
        }

        return measurmentDTOS;
    }

    private String getMeasurmentWay(Measurment measurment) {
        if (measurment.isManualMeasurment()) {
            return "manualMeasurment";
        } else if (measurment.isReturnToFull()) {
            return "returnToFull";
        } else {
            return "measuredAmount";
        }
    }

    private String getStartedTrip(long id) {
        Trip trip = tripRepository.findByStartingMeasurmentId(id);
        if (trip == null) {
            return "";
        } else {
            return trip.getBusinessTripNumber();
        }
    }

    private String getEndedTrip(long id) {
        Trip trip = tripRepository.findByEndingMeasurmentId(id);
        if (trip == null) {
            return "";
        } else {
            return trip.getBusinessTripNumber();
        }
    }

    private Double calculateLeftFuelTankAmount(Measurment measurment) {
        if (measurment.isManualMeasurment()) {
            return measurment.getLeftFuelTank() * vehicleRepository.findById(measurment.getVehicleId()).getLeftTankConverter();
        } else if (measurment.isReturnToFull()) {
            return vehicleRepository.findById(measurment.getVehicleId()).getLeftTankCapacity() - measurment.getLeftFuelTank();
        } else if (measurment.isMeasuredAmount()) {
            return measurment.getLeftFuelTank();
        } else return 0.0;
    }

    private Double calculateRightFuelTankAmount(Measurment measurment) {
        if (measurment.isManualMeasurment()) {
            return measurment.getRightFuelTank() * vehicleRepository.findById(measurment.getVehicleId()).getRightTankConverter();
        } else if (measurment.isReturnToFull()) {
            return vehicleRepository.findById(measurment.getVehicleId()).getRightTankCapacity() - measurment.getRightFuelTank();
        } else if (measurment.isMeasuredAmount()) {
            return measurment.getRightFuelTank();
        } else return 0.0;
    }

    @Override
    public ErrorMessageDTO addMeasurment(Measurment measurment) {
        if (measurmentRepository.findByVehicleIdAndDate(measurment.getVehicleId(), measurment.getDate()) != null) {
            return new ErrorMessageDTO("W bazie został znaleziony taki pomiar.");
        } else {
            measurmentRepository.save(measurment);
            return null;
        }
    }

    @Override
    public ErrorMessageDTO updateMeasurment(Measurment measurment) {
        if (measurmentRepository.findById(measurment.getId()) == null) {
            return new ErrorMessageDTO("Nie odnaleziono pomiaru o podanym identyfikatorze - prawdopodobnie został usunięty. Odśwież aplikację i spróbuj ponownie.");

        } else {
            Measurment measurmentTemp = measurmentRepository.findByVehicleIdAndDate(measurment.getVehicleId(), measurment.getDate());
            if (measurmentTemp == null || measurmentTemp.getId() == measurment.getId()) {
                Trip startingTrip = tripRepository.findByStartingMeasurmentId(measurment.getId());
                Trip endingTrip = tripRepository.findByEndingMeasurmentId(measurment.getId());
                String message = "";
                if (startingTrip != null) {
                    message += "Ten pomiar jest przypisany do trasy " + startingTrip.getBusinessTripNumber() + " jako pomiar początkowy. Proszę najpierw usunąć to powiązanie.";
                }
                if (endingTrip != null) {
                    message += "Ten pomiar jest przypisany do trasy " + endingTrip.getBusinessTripNumber() + " jako pomiar końcowy. Proszę najpierw usunąć to powiązanie.";
                }
                if (message.equals("")) {
                    measurmentRepository.save(measurment);
                    return null;
                } else {
                    return new ErrorMessageDTO(message);
                }
            }
            else{
                return new ErrorMessageDTO("W bazie danych został znaleziony taki pomiar.");
            }
        }
    }

    @Override
    public ErrorMessageDTO deleteMeasurment(long id) {
        Trip startingTrip = tripRepository.findByStartingMeasurmentId(id);
        Trip endingTrip = tripRepository.findByEndingMeasurmentId(id);
        String message = "";
        if (startingTrip != null) {
            message += "Ten pomiar jest przypisany do trasy " + startingTrip.getBusinessTripNumber() + " jako pomiar początkowy. Proszę najpierw usunąć to powiązanie.";
        }
        if (endingTrip != null) {
            message += "Ten pomiar jest przypisany do trasy " + endingTrip.getBusinessTripNumber() + " jako pomiar końcowy. Proszę najpierw usunąć to powiązanie.";
        }
        if (message.equals("")) {
            measurmentRepository.deleteById(id);
            return null;
        } else {
            return new ErrorMessageDTO(message);
        }
    }
}
