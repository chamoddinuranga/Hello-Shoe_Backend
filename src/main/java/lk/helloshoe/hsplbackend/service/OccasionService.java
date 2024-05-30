package lk.helloshoe.hsplbackend.service;

import lk.helloshoe.hsplbackend.dto.OccasionDTO;

import java.util.List;


public interface OccasionService {
    void saveOccasion(OccasionDTO occasionDTO);

    List<OccasionDTO> getAllOccasion();

    void deleteOccasion(String id);

    void updateOccasion(String id, OccasionDTO occasionDTO);
}
