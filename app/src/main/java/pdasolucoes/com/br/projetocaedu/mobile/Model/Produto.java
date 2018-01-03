package pdasolucoes.com.br.projetocaedu.mobile.Model;

import java.io.Serializable;

/**
 * Created by PDA on 26/09/2017.
 */

public class Produto implements Serializable {
    private String barra;
    private String codProduto;
    private String descricao;
    private float preco;
    private String cor;
    private int qtde;
    private int rep;
    private String tamanho;
    private int idade;
    private String tipo_produto;
    private String prop;
    private float margem;
    private int vendas;
    private int estoqueLoja;
    private String statusLoja;
    private String statusRegional;

    public String getStatusLoja() {
        return statusLoja;
    }

    public void setStatusLoja(String statusLoja) {
        this.statusLoja = statusLoja;
    }

    public String getStatusRegional() {
        return statusRegional;
    }

    public void setStatusRegional(String statusRegional) {
        this.statusRegional = statusRegional;
    }

    public String getBarra() {
        return barra;
    }

    public void setBarra(String barra) {
        this.barra = barra;
    }

    public String getCodProduto() {
        return codProduto;
    }

    public void setCodProduto(String codProduto) {
        this.codProduto = codProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public int getRep() {
        return rep;
    }

    public void setRep(int rep) {
        this.rep = rep;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public String getTipo_produto() {
        return tipo_produto;
    }

    public void setTipo_produto(String tipo_produto) {
        this.tipo_produto = tipo_produto;
    }

    public String getProp() {
        return prop;
    }

    public void setProp(String prop) {
        this.prop = prop;
    }

    public float getMargem() {
        return margem;
    }

    public void setMargem(float margem) {
        this.margem = margem;
    }

    public int getVendas() {
        return vendas;
    }

    public void setVendas(int vendas) {
        this.vendas = vendas;
    }

    public int getEstoqueLoja() {
        return estoqueLoja;
    }

    public void setEstoqueLoja(int estoqueLoja) {
        this.estoqueLoja = estoqueLoja;
    }
}
