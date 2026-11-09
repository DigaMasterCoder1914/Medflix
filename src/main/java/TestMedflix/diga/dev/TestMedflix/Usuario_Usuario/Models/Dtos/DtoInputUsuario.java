package TestMedflix.diga.dev.TestMedflix.Usuario_Usuario.Models.Dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DtoInputUsuario(

        @NotNull
        @Email(message = "O email não esta no formato correto!")
        String email,

@NotNull
@NotBlank(message = "Senha inválida")
String senha) {


}
