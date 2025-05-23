package ma.enset.infoparc.web;

import ma.enset.infoparc.entities.Computer;
import ma.enset.infoparc.service.ComputerService;
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
public class ComputerController {
   @Autowired
   private  ComputerService computerService;

   @GetMapping("/user/computers")
   public String computers(Model model,
                       @RequestParam(defaultValue = "") String keyword,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(defaultValue = "5") int size){
      Page<Computer> computerPage = computerService.getComputers(keyword, page, size);
      model.addAttribute("listcomputers", computerPage.getContent());
      model.addAttribute("currentPage",page);
      model.addAttribute("totalPages",computerPage.getTotalPages());
      model.addAttribute("keyword", keyword);

      return "computers";

   }
   @GetMapping("/admin/deleteComputer")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   public String deleteComputer(@RequestParam Long id ,@RequestParam int page,@RequestParam String keyword){
      computerService.deleteComputer(id);
      return "redirect:/user/computers?page="+page+"&keyword="+keyword;
   }

   @GetMapping("/admin/formComputer")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   public String showformComputer(Model model){
      model.addAttribute("computer", new Computer());
      return "formComputer";
   }
   @PostMapping("/admin/saveComputer")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   public String saveComputer(@ModelAttribute Computer computer){
     computerService.saveComputer(computer);
     return "redirect:/user/computers";
   }
   @GetMapping("/admin/editComputer")
   @PreAuthorize("hasRole('ROLE_ADMIN')")
   public String editComputer(@RequestParam Long id ,Model model){
      Computer computer = computerService.getComputerById(id);
      model.addAttribute("computer",computer);
      return "editComputer";
   }



}
