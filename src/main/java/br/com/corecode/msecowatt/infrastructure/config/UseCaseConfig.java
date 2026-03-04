package br.com.corecode.msecowatt.infrastructure.config;

import br.com.corecode.msecowatt.application.useCases.auth.AuthenticateUserUseCase;
import br.com.corecode.msecowatt.application.useCases.company.*;
import br.com.corecode.msecowatt.application.useCases.energyReading.*;
import br.com.corecode.msecowatt.application.useCases.user.CreateUserUseCase;
import br.com.corecode.msecowatt.domain.repository.CompanyRepository;
import br.com.corecode.msecowatt.domain.repository.EnergyReadingRepository;
import br.com.corecode.msecowatt.domain.repository.RoleRepository;
import br.com.corecode.msecowatt.domain.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;

@Configuration
public class UseCaseConfig {

    @Bean
    public CreateCompanyUseCase createCompanyUseCase(CompanyRepository repository) {
        return new CreateCompanyUseCase(repository);
    }

    @Bean
    public GetCompanyUseCase getCompanyUseCase(CompanyRepository repository) {
        return new GetCompanyUseCase(repository);
    }

    @Bean
    public ListCompaniesUseCase listCompanyUseCase(CompanyRepository repository) {
        return new ListCompaniesUseCase(repository);
    }

    @Bean
    public DeleteCompanyUseCase deleteCompanyUseCase(CompanyRepository repository) {
        return new DeleteCompanyUseCase(repository);
    }

    @Bean
    public GetCompanyByCnpjUseCase getCompanyByCnpjUseCase(CompanyRepository repository) {
        return new GetCompanyByCnpjUseCase(repository);
    }

    @Bean
    public CreateEnergyReadingUseCase createEnergyReadingUseCase(
            EnergyReadingRepository energyRepository,
            CompanyRepository companyRepository
    ) {
        return new CreateEnergyReadingUseCase(energyRepository, companyRepository);
    }

    @Bean
    public GetEnergyReadingUseCase getEnergyReadingUseCase(EnergyReadingRepository repository) {
        return new GetEnergyReadingUseCase(repository);
    }

    @Bean
    public GetEnergyReadingByCompanyUseCase getEnergyReadingByCompanyUseCase(
            EnergyReadingRepository energyRepository,
            CompanyRepository companyRepository
    ) {
        return new GetEnergyReadingByCompanyUseCase(energyRepository, companyRepository);
    }

    @Bean
    public GetEnergyReadingByPeriodUseCase getEnergyReadingByPeriodUseCase(
            EnergyReadingRepository energyRepository,
            CompanyRepository companyRepository
    ) {
        return new GetEnergyReadingByPeriodUseCase(energyRepository, companyRepository);
    }

    @Bean
    public ListEnergyReadingUseCase listEnergyReadingUseCase(
            EnergyReadingRepository repository
    ) {
        return new ListEnergyReadingUseCase(repository);
    }

    @Bean
    public DeleteEnergyReading deleteEnergyReading(EnergyReadingRepository repository) {
        return new DeleteEnergyReading(repository);
    }

    @Bean
    public AuthenticateUserUseCase authenticateUserUseCase(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtEncoder jwtEncoder) {
        return new AuthenticateUserUseCase(userRepository, bCryptPasswordEncoder, jwtEncoder);
    }

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        return new CreateUserUseCase(userRepository, roleRepository, bCryptPasswordEncoder);
    }
}
