package com.guilherme.lojavirtual.services.validation;

import com.guilherme.lojavirtual.domain.Cliente;
import com.guilherme.lojavirtual.dto.ClienteDTO;
import com.guilherme.lojavirtual.repositories.ClienteRepository;
import com.guilherme.lojavirtual.resources.exceptionHandler.FieldError;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
        List<FieldError> errors = new ArrayList<>();

        //Retorna o id presente na uri da requisição
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer id = Integer.parseInt(map.get("id"));
        Cliente cliente = clienteRepository.findByEmail(clienteDTO.getEmail());

        if (cliente != null && !cliente.getId().equals(id)) {
            errors.add(new FieldError("email", "Email já existente"));
        }

        for (FieldError fieldError : errors) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(fieldError.getMessage()).addPropertyNode(fieldError.getField()).addConstraintViolation();
        }
        return errors.isEmpty();
    }

}
