package lk.helloshoe.hsplbackend.service;

import lk.helloshoe.hsplbackend.dto.GenderDTO;

import java.util.List;

public interface GenderService {
    void saveGender(GenderDTO genderDTO);

    List<GenderDTO> getAllGenders();

    void deleteGender(String id);

    void updateGender(String id, GenderDTO genderDTO);
}
