package br.com.guilherme.gastos.config;

public abstract class SecurityConstants {

    public static final String AUTH_LOGIN_URL = "/api/authenticate";

    // JWT token defaults
    public static final String TOKEN_HEADER = "Authorization";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String TOKEN_TYPE = "JWT";
    public static final String TOKEN_ISSUER = "gastos-api";
    public static final String TOKEN_AUDIENCE = "gastos-app";

}

