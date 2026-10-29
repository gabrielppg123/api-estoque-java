package br.com.estudos.estoque;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Optional;

@RestController
public class ControladorProdutos {

    private final Servicos servicos = new Servicos();

    private final RepositorioProdutos repositorioProdutos;

    public ControladorProdutos(RepositorioProdutos repositorioProdutos) {
        this.repositorioProdutos = repositorioProdutos;
    }

    @GetMapping("/produtos")
    public List<Produto> listarProdutos() {
        return repositorioProdutos.findAll();
    }

    @GetMapping("/valorTotalEstoque")
    public double calcularValorTotalEmEstoque() {
        return servicos.calcularValorTotalEmEstoque(listarProdutos());
    }


    @PostMapping("/produtos")
    public ResponseEntity<Produto> cadastrarProduto(
            @RequestBody Produto produto) {

        Produto novoProduto = new Produto(
                produto.getNome(),
                produto.getPreco(),
                produto.getTipo(),
                produto.getEstoque()
        );

        Produto produtoSalvo = repositorioProdutos.save(novoProduto);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(produtoSalvo);
    }


    @PutMapping("/produtos/{id}")
    public ResponseEntity<Produto> atualizarProduto(
            @PathVariable Long id,
            @RequestBody Produto dadosAtualizados) {

        Optional<Produto> resultado = repositorioProdutos.findById(id);

        if (resultado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Produto produtoExistente = resultado.get();

        produtoExistente.setNome(dadosAtualizados.getNome());
        produtoExistente.setPreco(dadosAtualizados.getPreco());
        produtoExistente.setTipo(dadosAtualizados.getTipo());
        produtoExistente.setEstoque(dadosAtualizados.getEstoque());

        Produto produtoSalvo = repositorioProdutos.save(produtoExistente);

        return ResponseEntity.ok(produtoSalvo);
    }

}