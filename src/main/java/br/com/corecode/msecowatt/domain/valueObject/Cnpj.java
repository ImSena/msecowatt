package br.com.corecode.msecowatt.domain.valueObject;

import br.com.corecode.msecowatt.domain.exceptions.InvalidDomainValueException;

public record Cnpj (String value) {
    public Cnpj(String value) {
        if(!isValid(value)){
            throw new InvalidDomainValueException("Invalid CNPJ");
        }

        this.value = value.replaceAll("\\D", "");
    }

    private boolean isValid(String cnpj) {
        String cleanCnpj = cnpj.replaceAll("\\D", "");
        return cleanCnpj.length() == 14;
    }

    public String getValue() {
        return value;
    }
}
