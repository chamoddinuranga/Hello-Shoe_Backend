package lk.helloshoe.hsplbackend.service;

import lk.helloshoe.hsplbackend.dto.SupplierDTO;

import java.util.List;

public interface SupplierService {
    void saveSupplier(SupplierDTO supplierDTO);

    SupplierDTO getSupplier(String id);

    List<SupplierDTO> getAllSuppliers();

    void deleteSupplier(String id);

    void updateSupplier(String id,SupplierDTO supplierDTO);
}
