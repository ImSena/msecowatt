package br.com.corecode.msecowatt.infrastructure.persistence.mongo.mapper;

import br.com.corecode.msecowatt.domain.entity.Role;
import br.com.corecode.msecowatt.infrastructure.persistence.mongo.document.RoleDocument;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
public class RoleMongoMapper {

    public RoleDocument toDocument(Role role) {
        RoleDocument roleDocument = new RoleDocument();
        roleDocument.setId(role.getId());
        roleDocument.setName(role.getName());
        return roleDocument;
    }

    public Role toDomain(RoleDocument document){
        return Role.restore(document.getId(), document.getName());
    }

}
