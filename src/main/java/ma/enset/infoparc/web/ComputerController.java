package ma.enset.infoparc.web;

import ma.enset.infoparc.service.ComputerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ComputerController {
   @Autowired
   private ComputerService computerService;
}
