package lk.helloshoe.hsplbackend.service.IMPL;

import lk.helloshoe.hsplbackend.Enum.Level;
import lk.helloshoe.hsplbackend.conversion.ConversionData;
import lk.helloshoe.hsplbackend.dto.CustomerDTO;
import lk.helloshoe.hsplbackend.entity.CustomerEntity;
import lk.helloshoe.hsplbackend.exception.NotFoundException;
import lk.helloshoe.hsplbackend.repository.CustomerServiceDao;
import lk.helloshoe.hsplbackend.service.CustomerService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor
public class CustomerServiceIMPL implements CustomerService {

    final private ConversionData conversionData;

    final private CustomerServiceDao customerServiceDao;
    @Override
    public void saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setCustomerId(getNextCustomerId());
        customerDTO.setLevel(Level.NEW);
        customerDTO.setTotalPoint(0);
        CustomerEntity customerEntity = conversionData.convertToCustomerEntity(Optional.of(customerDTO));
        customerServiceDao.save(customerEntity);
    }

    @Override
    public CustomerDTO getCustomer(String id) {
        if (!customerServiceDao.existsById(id)) throw new NotFoundException("Customer Not Found");
        return conversionData.convertToCustomerDTO(customerServiceDao.findById(id));
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        return conversionData.getCustomerDTOList(customerServiceDao.findAll());
    }

    @Override
    public void deleteCustomer(String id) {
        if (!customerServiceDao.existsById(id)) throw new NotFoundException("Customer Not Found");
        customerServiceDao.deleteById(id);
    }

    @Override
    public void updateCustomer(String id,CustomerDTO customerDTO) {
        Optional<CustomerEntity> customerEntity = customerServiceDao.findById(id);
        if (customerEntity.isEmpty()) throw new NotFoundException("Customer Not Found");
        customerEntity.get().setCustomerName(customerDTO.getCustomerName());
        customerEntity.get().setGender(customerDTO.getGender());
        customerEntity.get().setJoinDate(customerDTO.getJoinDate());
        customerEntity.get().setDob(customerDTO.getDob());
        customerEntity.get().setAddress1(customerDTO.getAddress1());
        customerEntity.get().setAddress2(customerDTO.getAddress2());
        customerEntity.get().setAddress3(customerDTO.getAddress3());
        customerEntity.get().setAddress4(customerDTO.getAddress4());
        customerEntity.get().setPostalCode(customerDTO.getPostalCode());
        customerEntity.get().setContactNo(customerDTO.getContactNo());
        customerEntity.get().setEmail(customerDTO.getEmail());
        customerEntity.get().setRecentPurchasedDate(customerDTO.getRecentPurchasedDate());
    }

    private String getNextCustomerId() {
        CustomerEntity firstByOrderByCustomerIdDesc = customerServiceDao.findFirstByOrderByCustomerIdDesc();
        return (firstByOrderByCustomerIdDesc != null)
                ? String.format("Cust-%03d",
                        Integer.parseInt(firstByOrderByCustomerIdDesc.getCustomerId().
                                replace("Cust-", "")) + 1)
                : "Cust-001";
    }
}
