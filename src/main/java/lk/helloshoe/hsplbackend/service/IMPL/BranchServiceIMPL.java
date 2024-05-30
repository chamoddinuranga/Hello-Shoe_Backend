package lk.helloshoe.hsplbackend.service.IMPL;

import lk.helloshoe.hsplbackend.Util.UtilMatters;
import lk.helloshoe.hsplbackend.conversion.ConversionData;
import lk.helloshoe.hsplbackend.dto.BranchDTO;
import lk.helloshoe.hsplbackend.exception.InvalidException;
import lk.helloshoe.hsplbackend.repository.BranchServiceDao;
import lk.helloshoe.hsplbackend.service.BranchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BranchServiceIMPL implements BranchService {

    final private ConversionData conversionData;

    final private BranchServiceDao branchServiceDao;
    @Override
    public void saveBranch(BranchDTO branchDTO){
        if (!branchDTO.getProductCode().equals(UtilMatters.productActivationCode())) throw new InvalidException("Invalid Product Code");
        branchDTO.setBranchId(UtilMatters.generateId());
        branchServiceDao.save(conversionData.toBranchEntity(branchDTO));
    }
}
