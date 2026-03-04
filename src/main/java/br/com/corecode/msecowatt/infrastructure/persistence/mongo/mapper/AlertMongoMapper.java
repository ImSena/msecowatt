package br.com.corecode.msecowatt.infrastructure.persistence.mongo.mapper;

import br.com.corecode.msecowatt.domain.entity.Alert;
import br.com.corecode.msecowatt.infrastructure.persistence.mongo.document.AlertDocument;
import org.springframework.stereotype.Component;

@Component
public class AlertMongoMapper {

    public Alert toDomain(AlertDocument doc) {
        return new Alert(
                doc.getId(),
                doc.getCompanyId(),
                doc.getType(),
                doc.getMessage(),
                doc.isResolved(),
                doc.getCreatedAt()
        );
    }

    public AlertDocument toDocument(Alert alert) {
        AlertDocument doc = new AlertDocument();
        doc.setId(alert.getId());
        doc.setCompanyId(alert.getCompanyId());
        doc.setType(alert.getType());
        doc.setMessage(alert.getMessage());
        doc.setResolved(alert.isResolved());
        doc.setCreatedAt(alert.getCreatedAt());
        return doc;
    }
}
