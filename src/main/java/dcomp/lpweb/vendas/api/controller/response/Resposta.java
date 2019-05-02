package dcomp.lpweb.vendas.api.controller.response;

import java.util.ArrayList;
import java.util.List;

public class Resposta<T> {

    private T dados;
    private List<String> erros = new ArrayList<>();


    public T getDados() {
        return dados;
    }

    public void setDados(T dados) {
        this.dados = dados;
    }

    public List<String> getErros() {
        return erros;
    }

    public void setErros(List<String> erros) {
        this.erros = erros;
    }
}
