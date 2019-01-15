package com.guilherme.lojavirtual.services.validation;


import com.guilherme.lojavirtual.domain.enums.TipoCliente;
import com.guilherme.lojavirtual.dto.ClienteNewDTO;
import com.guilherme.lojavirtual.resources.exceptionHandler.FieldError;
import com.guilherme.lojavirtual.services.validation.utils.BR;
import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Override
    public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {             
        List<FieldError> errors = new ArrayList<>();

        if (clienteNewDTO.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidCPF(clienteNewDTO.getCpfOuCnpj())) {
            errors.add(new FieldError("cpfOuCnpj", "CPF inválido"));
        }

        if (clienteNewDTO.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidCNPJ(clienteNewDTO.getCpfOuCnpj())) {
            errors.add(new FieldError("cpfOuCnpj", "CNPJ inválido"));
        }

        for (FieldError fieldError : errors) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldError.getMessage()).addPropertyNode(fieldError.getField()).addConstraintViolation();
        }
        return errors.isEmpty();
    }

}
