package br.com.estudos.estoque;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositorioProdutos extends JpaRepository<Produto, Long> {

}