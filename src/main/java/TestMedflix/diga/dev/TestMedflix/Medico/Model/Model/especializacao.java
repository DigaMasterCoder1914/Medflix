package TestMedflix.diga.dev.TestMedflix.Medico.Model.Model;

public enum especializacao {

    PEDIATRA("pediatra"),
    OFTALMOLOGISTA("oftalmologista"),
    NAO_RELATADO("Não relatado");

    private String especialidade;

    especializacao(String especialidade) {

        this.especialidade = especialidade;

    }

    public static especializacao defineEspecialidade(String texto) {


        for (especializacao especializacao : especializacao.values()) {

            if (especializacao.especialidade.equals(texto)) {

                return especializacao;


            }


        }
        throw new RuntimeException("Especialidade Invalida!");
    }


}











