package br.com.famis.mapper;

import br.com.famis.dto.ProdutoDto;
import br.com.famis.model.Produto;

public class ProdutoMapper {

    public static ProdutoDto produtoToDto (Produto produto){
        return ProdutoDto.builder()
                .id(produto.getId())
                .categoria(produto.getCategoria())
                .nome(produto.getNome())
                .valor(produto.getValor())
                .build();
    }

    public static Produto dtoToProduto(ProdutoDto produtoDto){
        return Produto.builder()
                .id(produtoDto.getId())
                .nome(produtoDto.getNome())
                .categoria(produtoDto.getCategoria())
                .valor(produtoDto.getValor())
                .build();
    }
}
