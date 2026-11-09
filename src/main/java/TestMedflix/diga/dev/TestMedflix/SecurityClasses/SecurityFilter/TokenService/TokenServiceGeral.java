package TestMedflix.diga.dev.TestMedflix.SecurityClasses.SecurityFilter.TokenService;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceGeral {

    @Value("${api.security.token.secret}")
private String Value;

public String  GerarToken(String Subject){


    //Adicionar o tempo depois
    try {

        Algorithm algorithm = Algorithm.HMAC256(Value);

        return JWT.create().
                withSubject(Subject).
                withIssuer("Medflix")
                .sign(algorithm
                );

        //Mecher Depois
    }catch ( RuntimeException ex){

        throw ex;
    }
}

public String PegarSubject(String Token){

   try{

       Algorithm algorithm = Algorithm.HMAC256(Value);
       return JWT.require(algorithm).
               withIssuer("Medflix").
               build().verify(Token)
               .getSubject();

   }catch ( RuntimeException ex){

       return "Erro ao Validar Token";

   }



}


}
