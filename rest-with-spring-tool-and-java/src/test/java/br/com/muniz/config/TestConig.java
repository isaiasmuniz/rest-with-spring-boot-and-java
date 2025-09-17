package br.com.muniz.config;

public interface TestConig {
    //caso erro mudar para 8080
    int SERVER_PORT = 8888;

    String HEADER_PARAM_AUTHORIZATION = "Authorization";
    String HEADER_PARAM_ORIGIN = "Origin";


    String ORIGIN_MUNIZ = "http://localhost:8080"; //talvez n funcione, tentar um dominio que realmente exista
    String ORIGIN_ESTACIO = "https://sia.estacio.br";
    String ORIGIN_UDEMY = "https://www.udemy.com";

}
