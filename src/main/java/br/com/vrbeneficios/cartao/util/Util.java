package br.com.vrbeneficios.cartao.util;

import java.math.BigDecimal;

public class Util {

    public static String gerarNumeroCartao(){
        return numeroAleatorio4Digitos()+""
                +numeroAleatorio4Digitos()+""
                +numeroAleatorio4Digitos()+""
                +numeroAleatorio4Digitos();
    }

    public static String numeroAleatorio4Digitos(){
        int numero = numeroAleatorio();
        while(numero < 1000 || numero > 9999){
            numero = numeroAleatorio();
        }
        return numero+"";
    }

    private static int numeroAleatorio(){
        return BigDecimal.valueOf(Math.floor(Math.random()*10000)).intValue();
    }
}
