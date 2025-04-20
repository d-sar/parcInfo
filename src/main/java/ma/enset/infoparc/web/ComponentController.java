package ma.enset.infoparc.web;

import ma.enset.infoparc.entities.Component;
import ma.enset.infoparc.entities.Computer;
import ma.enset.infoparc.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ComponentController {
    @Autowired
    private ComponentService componentService;

    @GetMapping("/user/components")
    public String components(Model model,
                             @RequestParam(defaultValue = "") String keyword,
                             @RequestParam(defaultValue = "0") int page,
                             @RequestParam(defaultValue = "5") int size) {
        Page<Component> componentPage = componentService.getComponents(keyword, page, size);
        model.addAttribute("listcomponents", componentPage.getContent());
        model.addAttribute("currentPage",page);
        model.addAttribute("totalPages",componentPage.getTotalPages());
        model.addAttribute("keyword", keyword);
        return "components";
    }
    @GetMapping("/admin/deleteComponent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteComponent(@RequestParam Long id ,@RequestParam int page,@RequestParam String keyword){
        componentService.deleteComponent(id);
        return "redirect:/user/components?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/formComponent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String showformComponent(Model model){
        model.addAttribute("component", new Component());
        return "formComponent";
    }
    @PostMapping("/admin/saveComponent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveComponent(@ModelAttribute Component component){
        componentService.saveComponent(component);
        return "redirect:/user/components";
    }
    @GetMapping("/admin/editComponent")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String editComponent(@RequestParam Long id ,Model model){
        Component component = componentService.getComponentById(id);
        model.addAttribute("component",component);
        return "editComponent";
    }
}
