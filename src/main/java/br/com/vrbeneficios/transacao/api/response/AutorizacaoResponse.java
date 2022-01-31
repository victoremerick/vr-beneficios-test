package br.com.vrbeneficios.transacao.api.response;

public enum AutorizacaoResponse {
    OK("OK"),
    SALDO_INSUFICIENTE("SALDO_INSUFICIENTE"),
    SENHA_INVALIDA("SENHA_INVALIDA"),
    CARTAO_INEXISTENTE("CARTAO_INEXISTENTE");

    private final String response;

    AutorizacaoResponse(String valor){
        this.response = valor;
    }

    public String getResponse() {
        return response;
    }

    // OR
    @Override
    public String toString() {
        return String.valueOf(response);
    }
}
