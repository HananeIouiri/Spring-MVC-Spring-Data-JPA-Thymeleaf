package ma.enset.hospital;

import ma.enset.hospital.entities.Patient;
import ma.enset.hospital.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;


import java.util.Date;

@SpringBootApplication
public class HospitalApplication {
	@Autowired
	private PatientRepository patientRepository;

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}
	/*
	public void run(String... args) throws  Exception{
		patientRepository.save(new Patient(null,"IOUIRI","Hanane",new Date(), false , 500));
		patientRepository.save(new Patient(null,"iBRAHIMI","Hayat",new Date(), false , 501));
		patientRepository.save(new Patient(null,"JALAL","Karim",new Date(), true , 220));
	}
}*/

	CommandLineRunner start(PatientRepository patientRepository) {
		return args -> {
			patientRepository.save(Patient.builder().nom("IBRAHIMI").prenom("Hayat").dateNaissance(new Date()).malade(true).score(10).build());
			patientRepository.save(Patient.builder().nom("Jalal").prenom("Hanane").dateNaissance(new Date()).malade(false).score(123).build());
			patientRepository.save(Patient.builder().nom("Hossni").prenom("Salma").dateNaissance(new Date()).malade(true).score(90).build());

		};
	}
		@Bean
		CommandLineRunner commandLineRunner(JdbcUserDetailsManager jdbcUserDetailsManager, PasswordEncoder passwordEncoder) {
			return args -> {
				if (!jdbcUserDetailsManager.userExists("user11")) {
					UserDetails user1 = User.withUsername("user11")
							.password(passwordEncoder.encode("1234"))
							.roles("USER")
							.build();
					jdbcUserDetailsManager.createUser(user1);
				}

				if (!jdbcUserDetailsManager.userExists("user22")) {
					UserDetails user2 = User.withUsername("user22")
							.password(passwordEncoder.encode("1234"))
							.roles("USER")
							.build();
					jdbcUserDetailsManager.createUser(user2);
				}

				if (!jdbcUserDetailsManager.userExists("admin2")) {
					UserDetails admin = User.withUsername("admin2")
							.password(passwordEncoder.encode("1234"))
							.roles("USER", "ADMIN")
							.build();
					jdbcUserDetailsManager.createUser(admin);
				}
			};
		}

	}

