package com.exemplo.curso.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.curso.model.Usuario;
import com.exemplo.curso.repository.UsuarioRepository;

/**
 *
 * A sample greetings controller to return greeting text
 */
@RestController
public class GreetingsController {
	/**
	 *
	 * @param name the name to greet
	 * @return greeting text
	 */

	@Autowired
	private UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/usuario/{nome}/idade/{idade}", method = RequestMethod.GET)
	@ResponseStatus(HttpStatus.OK)
	public String salvarUsuario(@PathVariable String nome, @PathVariable int idade) {

		Usuario usuario = new Usuario();

		usuario.setNome(nome);

		usuario.setIdade(idade);

		usuarioRepository.save(usuario);

		return "Salvo o usuario " + nome;

	}

	@GetMapping(value = "mostrartodos")
	@ResponseBody // retorna os dados para o corpo da resposta
	public ResponseEntity<List<Usuario>> listaUsuario() {

		List<Usuario> usuario = usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK); // retorna lista em Json

	}

	@DeleteMapping(value = "delete")
	@ResponseBody
	public ResponseEntity<String> delete(@RequestParam Long id) {

		usuarioRepository.deleteById(id);

		return new ResponseEntity<String>("Usuario " + id + " deletado", HttpStatus.OK);

	}

}
