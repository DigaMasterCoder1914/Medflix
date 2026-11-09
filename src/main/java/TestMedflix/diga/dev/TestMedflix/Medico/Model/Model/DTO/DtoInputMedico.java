package TestMedflix.diga.dev.TestMedflix.Medico.Model.Model.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoInputMedico(

         @NotNull
         @NotBlank
         String nome,

        @NotNull
        boolean Cirurgiao,

        @NotBlank
        String especialidade

) {
}
