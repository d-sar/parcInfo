package ma.enset.infoparc.web;

import ma.enset.infoparc.entities.Computer;
import ma.enset.infoparc.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class ComputerController {
   @Autowired
   private  ComputerService computerService;

   @GetMapping("/computers")
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
   @GetMapping("/deleteComputer")
   public String deleteComputer(@RequestParam Long id ,@RequestParam int page,@RequestParam String keyword){
      computerService.deleteComputer(id);
      return "redirect:/computers?page="+page+"&keyword="+keyword;
   }

}
