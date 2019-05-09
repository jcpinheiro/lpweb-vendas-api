package dcomp.lpweb.vendas.api.model;

public enum TipoCliente {

    FISICA("Pessoa Física"),
    JURIDICA("Pessoa Jurídica");


    private String tipo;

    private TipoCliente(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

}
