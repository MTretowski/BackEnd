package backend.Services;

import backend.DTOs.MeasurmentDTO;
import backend.Entities.Measurment;
import backend.Entities.Trip;
import backend.Repositories.MeasurmentRepository;
import backend.Repositories.TripRepository;
import backend.Repositories.VehicleRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
                    measurment.getDate(),
                    measurment.getLeftFuelTank(),
                    measurment.getRightFuelTank(),
                    calculateLeftFuelTankAmount(measurment),
                    calculateRightFuelTankAmount(measurment),
                    measurment.isManualMeasurment(),
                    measurment.isReturnToFull(),
                    measurment.isMeasuredAmount(),
                    measurment.getVehicleId(),
                    vehicleRepository.findById(measurment.getVehicleId()).getPlateNumbers(),
                    getStartedTrip(measurment.getId()),
                    getEndedTrip(measurment.getId())
                    ));
        }

        return measurmentDTOS;
    }

    private String getStartedTrip(long id) {
        Trip trip = tripRepository.findByStartingMeasurmentId(id);
        if (trip == null) {
            return "";
        } else {
            return trip.getBusinnesTripNumber();
        }
    }

    private String getEndedTrip(long id) {
        Trip trip = tripRepository.findByEndingMeasurmentId(id);
        if (trip == null) {
            return "";
        } else {
            return trip.getBusinnesTripNumber();
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
    public HttpStatus addMeasurment(Measurment measurment) {
        if (measurmentRepository.findByVehicleIdAndDate(measurment.getVehicleId(), measurment.getDate()) != null) {
            return HttpStatus.CONFLICT;
        } else {
            measurmentRepository.save(measurment);
            return HttpStatus.OK;
        }
    }

    @Override
    public HttpStatus updateMeasurment(Measurment measurment) {
        if (measurmentRepository.findById(measurment.getId()) == null) {
            return HttpStatus.CONFLICT;
        } else {
            measurmentRepository.save(measurment);
            return HttpStatus.OK;
        }
    }

    @Override
    public HttpStatus deleteMeasurment(long id) {
        if (tripRepository.findByStartingMeasurmentId(id) != null || tripRepository.findByEndingMeasurmentId(id) != null) {
            return HttpStatus.CONFLICT;
        } else {
            measurmentRepository.deleteById(id);
            return HttpStatus.OK;
        }
    }
}
