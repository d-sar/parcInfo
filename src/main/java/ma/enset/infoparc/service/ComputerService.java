package ma.enset.infoparc.service;

import ma.enset.infoparc.entities.Computer;
import ma.enset.infoparc.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ComputerService {
    @Autowired
    private ComputerRepository computerRepository;

    public Page<Computer> getComputers(String keyword,int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        if(keyword != null && !keyword.isEmpty()){
            return computerRepository.findByNameContains(keyword, pageable);
        }
        return computerRepository.findAll(pageable);
    }

    public void deleteComputer(Long id) {
        computerRepository.deleteById(id);
    }
     public void saveComputer(Computer computer) {
         computerRepository.save(computer);
     }

}
