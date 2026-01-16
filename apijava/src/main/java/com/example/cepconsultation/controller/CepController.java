package com.example.cepconsultation.controller;

import com.example.cepconsultation.model.Endereco;
import com.example.cepconsultation.service.ConsultaCepService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/cep")
@Tag(name = "CEP API", description = "API for Brazilian CEP consultation")
public class CepController {

    private final ConsultaCepService consultaCepService;

    public CepController(ConsultaCepService consultaCepService) {
        this.consultaCepService = consultaCepService;
    }

    @GetMapping("/{cep}")
    @Operation(summary = "Consult CEP", description = "Returns address information for the given CEP")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "CEP found successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Endereco.class))),
            @ApiResponse(responseCode = "400", description = "Invalid CEP format",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "CEP not found",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error",
                    content = @Content)
    })
    public ResponseEntity<?> consultarCep(
            @Parameter(description = "CEP to consult (8 digits)", required = true, example = "01001000")
            @PathVariable String cep) {
        try {
            Endereco endereco = consultaCepService.buscaEndereco(cep);
            return ResponseEntity.ok(endereco);
        } catch (RuntimeException e) {
            // Retorna mensagem de erro mais informativa
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("erro", e.getMessage());
            
            // Diferencia entre CEP inválido e CEP não encontrado
            if (e.getMessage().contains("inválido")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
            } else if (e.getMessage().contains("não encontrado")) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
            } else {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
            }
        }
    }
}