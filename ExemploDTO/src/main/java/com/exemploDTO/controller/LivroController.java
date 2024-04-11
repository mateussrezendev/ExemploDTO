package com.exemploDTO.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import jakarta.validation.Valid;

private final LivroService livroService;

@Autowired
public LivroControler (LivroService livroService) {
this.livroService = livroService;
}

// Busca livro por id

@GetMapping("/(id)")
public ResponseEntity<Livro> buscarLivroId(CrPathvariable Long id) {
Livro livro = livroService.buscaPorId(id);
if (livro != null) {

return ResponseEntity.ok(livro);
} else {
return ResponseEntity.notFound().build();

}

}

// Busca todos os livros

@GetMapping

public ResponseEntity<List<Livro>> buscaTodosLivros() {
List<Livro> livro = livroService.buscaTodos() ;
return ResponseEntity.ok(livro);

}

//Inserindo o Livro
@PostMapping
public ResponseEntity<LivroDTO> criar(@RequestBody @Valid Livro livro) {
LivroDTO salvarLivro = livroService.salvar(livro);
return ResponseEntity.status(HttpStatus.CREATED) .body (salvarLivro);
}

//Alterando o Livro
@PutMapping("/(id)")
public ResponseEntity<LivroDTO> alterarLivro(@PathVariable Long id, @RequestBody @Valid Livro livro) {
LivroDTO alterarLivroDTO = livroService.atualizar(id, livro);
if (alterarLivroDTO != null) {
return ResponseEntity.ok(alterarLivroDTO);
} else {
return ResponseEntity.notFound().build();
}
}

//Peletando o Livro
@DeleteMapping ("/(id)")
public ResponseEntity<Livro> deletarLivro(@PathVariable Long id) {
boolean apagar = livroService.deletar(id);
if (apagar) {
return ResponseEntity.status (HttpStatus.NO_CONTENT) .build();
} else {
return ResponseEntity.notFound() .build();
}
}
}
