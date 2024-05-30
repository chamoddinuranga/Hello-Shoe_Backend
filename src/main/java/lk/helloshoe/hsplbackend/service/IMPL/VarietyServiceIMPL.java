package lk.helloshoe.hsplbackend.service.IMPL;

import lk.helloshoe.hsplbackend.conversion.ConversionData;
import lk.helloshoe.hsplbackend.dto.VarietyDTO;
import lk.helloshoe.hsplbackend.exception.DuplicateException;
import lk.helloshoe.hsplbackend.exception.NotFoundException;
import lk.helloshoe.hsplbackend.repository.VarietyServiceDao;
import lk.helloshoe.hsplbackend.service.VarietyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class VarietyServiceIMPL implements VarietyService {

    private final VarietyServiceDao varietyServiceDao;
    private final ConversionData conversionData;
    @Override
    public void saveVariety(VarietyDTO varietyDTO) {
        if (varietyServiceDao.existsById(varietyDTO.getVarietyCode())) throw new DuplicateException("Variety Id Duplicate");
        varietyServiceDao.save(conversionData.toVarietyEntity(varietyDTO));
    }

    @Override
    public List<VarietyDTO> getAllVariety() {
        return conversionData.convertToVarietyDTO(varietyServiceDao.findAll());
    }

    @Override
    public void deleteVariety(String id) {
        if (!varietyServiceDao.existsById(id)) throw new NotFoundException("Variety Not Found");
        varietyServiceDao.deleteById(id);
    }

    @Override
    public void updateVariety(String id, VarietyDTO varietyDTO) {
        if (!varietyServiceDao.existsById(id)) throw new NotFoundException("Variety Not Found");
        varietyServiceDao.save(conversionData.toVarietyEntity(varietyDTO));
    }
}
