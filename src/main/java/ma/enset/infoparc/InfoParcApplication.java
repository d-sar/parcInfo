package ma.enset.infoparc;

import ma.enset.infoparc.entities.Computer;
import ma.enset.infoparc.repository.ComputerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class InfoParcApplication implements CommandLineRunner {

    @Autowired
    private ComputerRepository computerRepository;
    public static void main(String[] args) {
        SpringApplication.run(InfoParcApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
                Computer computer = new Computer();
                computer.setName("Computer1");
                computer.setModel("modele1");
                computerRepository.save(computer);
                Computer computer1 = new Computer();
                computer1.setName("Computer2");
                computer1.setModel("modele2");
                computerRepository.save(computer1);
        Computer computer2 = new Computer();
        computer2.setName("Computer3");
        computer2.setModel("modele3");
        computerRepository.save(computer2);

                List<Computer> computers = computerRepository.findAll();
                computers.forEach(System.out::println);
                Computer c =computerRepository.findById(Long.valueOf(1)).get();
                System.out.println(c.getName());

        }
    }

