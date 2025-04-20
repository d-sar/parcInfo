package ma.enset.infoparc;

import ma.enset.infoparc.entities.Computer;
import ma.enset.infoparc.repository.ComputerRepository;
import ma.enset.infoparc.security.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;



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
//
//                List<Computer> computers = computerRepository.findAll();
//                computers.forEach(System.out::println);


        }
        //InMemomy Authentication:
         @Bean
        PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
        }
        //jdbc Authentication
       // @Bean
        CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager) {
            PasswordEncoder passwordEncoder = passwordEncoder();
            return args -> {
                if (!jdbcUserDetailsManager.userExists("user1")) {
                    jdbcUserDetailsManager.createUser(
                            User.withUsername("user1")
                                    .password(passwordEncoder.encode("1234"))
                                    .roles("USER")
                                    .build());
                }

                if (!jdbcUserDetailsManager.userExists("admin")) {
                    jdbcUserDetailsManager.createUser(
                            User.withUsername("admin")
                                    .password(passwordEncoder.encode("1234"))
                                    .roles("USER")
                                    .build());
                }
            };
        }
        //userDetails Service kandiro biha okantfiwha
   // @Bean
    CommandLineRunner commandLineRunnerUserDetails(AccountService accountService) {
        return args -> {
            accountService.addNewRole("USER");
            accountService.addNewRole("ADMIN");
            accountService.addNewUser("user3Details","1234","user1@gmail.com","1234");
            accountService.addNewUser("user4Details","1234","user2@gmail.com","1234");
            accountService.addRoleToUser("user3Details","USER");
            accountService.addRoleToUser("user4Details","ADMIN");
        };
    }

}

