package ma.enset.infoparc.service;

import lombok.AllArgsConstructor;
import ma.enset.infoparc.entities.Component;
import ma.enset.infoparc.entities.Computer;
import ma.enset.infoparc.repository.ComponentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service @AllArgsConstructor
public class ComponentService {
    private ComponentRepository componentRepository;
    public Page<Component> getComponents(String keyword,int page,int size) {
        Pageable pageable = PageRequest.of(page, size);
        if(keyword != null && !keyword.isEmpty()){
            return componentRepository.findByNameContains(keyword, pageable);
        }
        return componentRepository.findAll(pageable);
    }
    public void deleteComponent(Long id) {
        componentRepository.deleteById(id);
    }
    public void saveComponent(Component component) {
        componentRepository.save(component);
    }
    public Component getComponentById(Long id) {
        return componentRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Component not found"));
    }
}
