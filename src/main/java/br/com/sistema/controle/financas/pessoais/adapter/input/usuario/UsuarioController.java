package br.com.sistema.controle.financas.pessoais.adapter.input.usuario;

import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.request.UsuarioRequest;
import br.com.sistema.controle.financas.pessoais.adapter.input.usuario.dto.response.UsuarioResponse;
import br.com.sistema.controle.financas.pessoais.domain.entity.usuario.UsuarioEntity;
import br.com.sistema.controle.financas.pessoais.port.input.usuario.IUsuario;
import br.com.sistema.controle.financas.pessoais.utils.Constantes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/v1/usuario")
public class UsuarioController implements IUsuarioController{

    @Autowired
    IUsuario iUsuario;
    @PostMapping("/criar-usuario")
    public ResponseEntity criaUsuario(@RequestBody UsuarioRequest usuario) {
        UsuarioResponse response = iUsuario.criarUsuario(usuario);
        return ResponseEntity.status(HttpStatus.OK).body(Constantes.CadastroRealizadoUsuario + response);
    }
}
