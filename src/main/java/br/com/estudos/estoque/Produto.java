package br.com.estudos.estoque;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private double preco;
    private String tipo;
    private int estoque;


    // Construtor
    public Produto(String nome, double preco, String tipo, int estoque) {
        this.nome = nome;
        this.preco = preco;
        this.tipo = tipo;
        this.estoque = estoque;
    }

    public Produto() {
    }



public String getNome() {
    return nome;
}

public void setNome(String nome) {
    this.nome = nome;
}

public double getPreco() {
    return preco;
}

public void setPreco(double preco) {
    this.preco = preco;
}

public String getTipo() {
    return tipo;
}

public void setTipo(String tipo) {
    this.tipo = tipo;
}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getEstoque() {
 return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    @Override
    public String toString() {
        return "Estudos.Produto{" +
                "nome='" + nome + '\'' +
                ", preço=" + preco +
                ", tipo='" + tipo + '\'' +
                ", estoque='" + estoque + '\'' +
                '}';
    }

}