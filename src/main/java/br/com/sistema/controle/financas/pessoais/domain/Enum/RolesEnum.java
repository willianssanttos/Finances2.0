package br.com.sistema.controle.financas.pessoais.domain.Enum;

public enum RolesEnum {

    ROLE_CLIENTE("ROLE_CLIENTE"),
    ROLE_MODERADOR("ROLE_MODERADOR"),
    ROLE_ADMINISTRADOR("ROLE_ADMINISTRADOR");

    private String RolesEnum;
    RolesEnum(String rolesEnum) {
        RolesEnum = rolesEnum;
    }

    public String getRolesEnum() {
        return RolesEnum;
    }
}
