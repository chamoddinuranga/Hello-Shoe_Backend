package lk.helloshoe.hsplbackend.service.IMPL;

import lk.helloshoe.hsplbackend.conversion.ConversionData;
import lk.helloshoe.hsplbackend.dto.GenderDTO;
import lk.helloshoe.hsplbackend.exception.DuplicateException;
import lk.helloshoe.hsplbackend.exception.NotFoundException;
import lk.helloshoe.hsplbackend.repository.GenderServiceDao;
import lk.helloshoe.hsplbackend.service.GenderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class GenderServiceIMPL implements GenderService {

    private final GenderServiceDao genderServiceDao;
    private final ConversionData conversionData;
    @Override
    public void saveGender(GenderDTO genderDTO) {
        if (genderServiceDao.existsById(genderDTO.getGenderCode())) throw new DuplicateException("Gender Id Duplicate");
        genderServiceDao.save(conversionData.toGenderEntity(genderDTO));
    }

    @Override
    public List<GenderDTO> getAllGenders() {
        return conversionData.convertToGenderDTO(genderServiceDao.findAll());
    }

    @Override
    public void deleteGender(String id) {
        if (!genderServiceDao.existsById(id)) throw new NotFoundException("Gender Not Found");
       genderServiceDao.deleteById(id);
    }

    @Override
    public void updateGender(String id, GenderDTO genderDTO) {
        if (!genderServiceDao.existsById(id)) throw new NotFoundException("Gender Not Found");
        genderServiceDao.save(conversionData.toGenderEntity(genderDTO));
    }


}
