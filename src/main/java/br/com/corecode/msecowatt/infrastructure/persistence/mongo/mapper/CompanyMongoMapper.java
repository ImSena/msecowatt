package br.com.corecode.msecowatt.infrastructure.persistence.mongo.mapper;

import br.com.corecode.msecowatt.domain.entity.Company;
import br.com.corecode.msecowatt.domain.valueObject.Cnpj;
import br.com.corecode.msecowatt.domain.valueObject.Location;
import br.com.corecode.msecowatt.infrastructure.persistence.mongo.document.CompanyDocument;
import org.springframework.stereotype.Component;

@Component
public class CompanyMongoMapper {

    public CompanyDocument toDocument(Company entity) {
        return new CompanyDocument(
                entity.getId(),
                entity.getName(),
                entity.getCnpj().getValue(),
                entity.getPhoneNumber(),
                entity.getLocation().street(),
                entity.getLocation().city(),
                entity.getLocation().state(),
                entity.getCreatedAt()
        );
    }

    public Company toDomain(CompanyDocument document) {
        return Company.restore(
                document.getId(),
                document.getName(),
                new Cnpj(document.getCnpj()),
                new Location(
                        document.getStreet(),
                        document.getCity(),
                        document.getState()
                ),
                document.getPhoneNumber(),
                document.getCreatedAt()
        );
    }

}
