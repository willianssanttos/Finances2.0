package br.com.sistema.controle.financas.pessoais.adapter.input.usuario;

import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.request.UsuarioRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response.UsuarioResponse;
import br.com.sistema.controle.financas.pessoais.port.input.usuario.IUsuario;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/usuario")
public class UsuarioController {

    @Autowired
    IUsuario iUsuario;
    @PostMapping("/criar-usuario")
    public ResponseEntity<String> criaConta(@RequestBody UsuarioRequest usuarioRequest) {
        UsuarioResponse response = iUsuario.criarUsuario(usuarioRequest);
        return ResponseEntity.ok(Constantes.MSG_CRIACAO_CONTA_SUCESSO);
    }
}
