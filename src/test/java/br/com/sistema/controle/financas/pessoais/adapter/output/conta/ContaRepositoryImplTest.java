package br.com.sistema.controle.financas.pessoais.adapter.output.conta;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJdbcTest
@ActiveProfiles("test")
class ContaRepositoryImplTest {

    @Test
    void criarConta() {
    }

    @Test
    void obterContasPorUsuario() {
    }

    @Test
    void editarConta() {
    }

    @Test
    void excluirConta() {
    }
}