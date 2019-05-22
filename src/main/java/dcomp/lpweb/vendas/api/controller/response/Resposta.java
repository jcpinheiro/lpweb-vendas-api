package dcomp.lpweb.vendas.api.controller.response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Resposta<T> {

    private T dados;
    private List<Erro> erros = new ArrayList<>();

    private Resposta() { }

    public static Resposta comDadosDe(Object dados) {
        Resposta resposta = new Resposta<>();
        resposta.setDados(dados );
        return resposta;
    }

    public static Resposta com(List<Erro> erros) {
        Resposta resposta = new Resposta<>();
        resposta.setErros(erros );
        return resposta;
    }

    public static Resposta com(Erro erro) {
        Resposta resposta = new Resposta<>();
        resposta.setErros(Arrays.asList(erro) );
        return resposta;
    }

    public void setErros(List<Erro> erros) {
        this.erros = erros;
    }

    public T getDados() {
        return dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }

    public List<Erro> getErros() {
        return erros;
    }
}
