package br.com.corecode.msecowatt.infrastructure.rest.v1.company.dto;

public record CompanyResponse(
   String id,
   String name,
   String cnpj
) {}
