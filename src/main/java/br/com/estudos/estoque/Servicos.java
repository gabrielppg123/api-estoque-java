package br.com.estudos.estoque;

import java.util.List;

public class Servicos {
    public int calcularQuantidadeTotal(List<Produto> listaProdutos) {
        return listaProdutos.stream()
                .mapToInt(Produto::getEstoque)
                .sum();
    }

    public double calcularValorTotalEmEstoque(List<Produto> listaProdutos) {
        return listaProdutos.stream()
                .mapToDouble(p -> p.getPreco() * p.getEstoque())
                .sum();
    }
}
