package TestMedflix.diga.dev.TestMedflix.Paciente.MOdel.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoInputPaciente(


        @NotNull
        @NotBlank
        String nome,

        @NotNull
        @NotBlank
        String telefone,

        @NotNull
        @NotBlank
        String sobrenome,

        @NotNull
        String cpf) {
}
