package lk.helloshoe.hsplbackend.service.IMPL;

import lk.helloshoe.hsplbackend.conversion.ConversionData;
import lk.helloshoe.hsplbackend.dto.ItemDTO;
import lk.helloshoe.hsplbackend.entity.GenderEntity;
import lk.helloshoe.hsplbackend.entity.ItemEntity;
import lk.helloshoe.hsplbackend.entity.OccasionEntity;
import lk.helloshoe.hsplbackend.entity.VarietyEntity;
import lk.helloshoe.hsplbackend.exception.NotFoundException;
import lk.helloshoe.hsplbackend.repository.GenderServiceDao;
import lk.helloshoe.hsplbackend.repository.ItemServiceDao;
import lk.helloshoe.hsplbackend.repository.OccasionServiceDao;
import lk.helloshoe.hsplbackend.repository.VarietyServiceDao;
import lk.helloshoe.hsplbackend.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceIMPL implements InventoryService {
    private final ItemServiceDao itemServiceDao;
    private final ConversionData conversionData;
    private final GenderServiceDao genderServiceDao;
    private final OccasionServiceDao occasionServiceDao;
    private final VarietyServiceDao varietyServiceDao;
    @Override
    public void saveItem(ItemDTO itemDTO) {
        ItemEntity itemEntity = conversionData.toItemEntity(itemDTO);
        itemEntity.setItemCode(generateItemCode(itemDTO));
        Optional<GenderEntity> genderEntity = genderServiceDao.findById(itemDTO.getGenderCode());
        if (genderEntity.isPresent()){
            GenderEntity genderEntity1 = genderEntity.get();
            itemEntity.setGenderEntity(genderEntity1);
        };
        Optional<OccasionEntity> occasionEntity = occasionServiceDao.findById(itemDTO.getOccasionCode());
        if (occasionEntity.isPresent()){
            OccasionEntity occasionEntity1 = occasionEntity.get();
            itemEntity.setOccasionEntity(occasionEntity1);
        }
        Optional<VarietyEntity> varietyEntity = varietyServiceDao.findById(itemDTO.getVarietyCode());
        if (varietyEntity.isPresent()){
            VarietyEntity varietyEntity1 = varietyEntity.get();
            itemEntity.setVarietyEntity(varietyEntity1);
        }
        itemServiceDao.save(itemEntity);
    }

    @Override
    public ItemDTO getItem(String id) {
        if(!itemServiceDao.existsById(id)){throw new NotFoundException("Item not found.");}
        return conversionData.toItemDTO(itemServiceDao.findById(id));
    }

    @Override
    public void deleteItem(String id) {
        if(!itemServiceDao.existsById(id)){throw new NotFoundException("Item not found.");}
        itemServiceDao.deleteById(id);
    }

    @Override
    public void updateItem(String id, String itemDesc, String pic) {
        if(!itemServiceDao.existsById(id)){throw new NotFoundException("Item not found.");}
        ItemEntity itemEntity = new ItemEntity();
        itemEntity.setItemCode(id);
        itemEntity.setItemDesc(itemDesc);
        itemEntity.setPic(pic);
        itemServiceDao.save(itemEntity);
    }

    private String generateItemCode(ItemDTO itemDTO) {

        StringBuilder prefixBuilder = new StringBuilder();

        if (!itemDTO.getVarietyCode().equals("-1")) {
            prefixBuilder.append(itemDTO.getVarietyCode());
        }
        if (!itemDTO.getOccasionCode().equals("-1")) {
            prefixBuilder.append(itemDTO.getOccasionCode());
        }
        if (!itemDTO.getGenderCode().equals("-1")) {
            prefixBuilder.append(itemDTO.getGenderCode());
        }

        String prefix = prefixBuilder.toString();

        String lastItemCodeStartingWithPrefix =
                itemServiceDao.findLastItemCodeStartingWithPrefix(
                        prefix
                );

        return (lastItemCodeStartingWithPrefix != null)
                ? String.format("%s%05d", prefix, Integer.parseInt(lastItemCodeStartingWithPrefix.replace(prefix, "")) + 1)
                : prefix + "00001";
    }
}
