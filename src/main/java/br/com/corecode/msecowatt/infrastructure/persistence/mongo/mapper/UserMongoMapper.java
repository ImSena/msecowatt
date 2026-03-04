package br.com.corecode.msecowatt.infrastructure.persistence.mongo.mapper;

import br.com.corecode.msecowatt.domain.entity.Role;
import br.com.corecode.msecowatt.domain.entity.User;
import br.com.corecode.msecowatt.infrastructure.persistence.mongo.document.UserDocument;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMongoMapper {

    public UserDocument toDocument(User user){
        UserDocument userDocument = new UserDocument();
        userDocument.setId(user.getId());
        userDocument.setUsername(user.getUsername());
        userDocument.setPassword(user.getPassword());
        Set<String> roleIds = user.getRoles()
                .stream()
                .map(Role::getId)
                .collect(Collectors.toSet());

        userDocument.setRoles(roleIds);

        return userDocument;
    }

    public User toDomain(UserDocument document){
        return User.restore(
                document.getId(),
                document.getUsername(),
                document.getPassword(),
                Set.of()
        );
    }
}
