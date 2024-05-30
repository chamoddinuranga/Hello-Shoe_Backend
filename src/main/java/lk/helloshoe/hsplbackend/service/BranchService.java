package lk.helloshoe.hsplbackend.service;

import lk.helloshoe.hsplbackend.dto.BranchDTO;
import lk.helloshoe.hsplbackend.exception.InvalidException;

public interface BranchService {
    void saveBranch(BranchDTO branchDTO) throws InvalidException;
}
