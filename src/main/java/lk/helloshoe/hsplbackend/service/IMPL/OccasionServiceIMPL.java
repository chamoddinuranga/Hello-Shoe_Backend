package lk.helloshoe.hsplbackend.service.IMPL;

import lk.helloshoe.hsplbackend.conversion.ConversionData;
import lk.helloshoe.hsplbackend.dto.OccasionDTO;
import lk.helloshoe.hsplbackend.exception.DuplicateException;
import lk.helloshoe.hsplbackend.exception.NotFoundException;
import lk.helloshoe.hsplbackend.repository.OccasionServiceDao;
import lk.helloshoe.hsplbackend.service.OccasionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OccasionServiceIMPL implements OccasionService {

    private final OccasionServiceDao occasionServiceDao;
    private final ConversionData conversionData;


    @Override
    public void saveOccasion(OccasionDTO occasionDTO) {
        if (occasionServiceDao.existsById(occasionDTO.getOccasionCode())) throw new DuplicateException("Occasion Id Duplicate");
        occasionServiceDao.save(conversionData.toOccasionEntity(occasionDTO));
    }

    @Override
    public List<OccasionDTO> getAllOccasion() {
        return conversionData.convertToOccasionDTO(occasionServiceDao.findAll());
    }

    @Override
    public void deleteOccasion(String id) {
        if (!occasionServiceDao.existsById(id)) throw new NotFoundException("Occasion Not Found");
        occasionServiceDao.deleteById(id);
    }

    @Override
    public void updateOccasion(String id, OccasionDTO occasionDTO) {
        if (!occasionServiceDao.existsById(id)) throw new NotFoundException("Occasion Not Found");
        occasionServiceDao.save(conversionData.toOccasionEntity(occasionDTO));
    }
}
