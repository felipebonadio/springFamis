package br.com.famis.mapper;

import br.com.famis.dto.MesaDto;
import br.com.famis.dto.ProdutoDto;
import br.com.famis.model.Colaborador;
import br.com.famis.model.Mesa;
import br.com.famis.model.Produto;

import java.util.function.Function;

public class MesaMapper {

    public static MesaDto mesaToDto(Mesa mesa){
        return MesaDto.builder()
                .id(mesa.getId())
                .colaboradorNome(mesa.getColaborador().getNome())
                .numero(mesa.getNumero())
                .produtos(mesa.getProdutos().stream().map(produtoToDto()).toList())
                .build();
    }

    public static Function<Produto, ProdutoDto> produtoToDto(){
        return produto -> ProdutoDto.builder()
                .categoria(produto.getCategoria())
                .nome(produto.getNome())
                .valor(produto.getValor())
                .build();
    }

    public static Mesa dtoToMesa(MesaDto mesaDto){
        return Mesa.builder()
                .id(mesaDto.getId())
                .colaborador(new Colaborador(mesaDto.getColaboradorNome()))
                .produtos(mesaDto.getProdutos().stream().map(dtoToProduto()).toList())
                .build();
    }

    public static Function<ProdutoDto, Produto> dtoToProduto(){
        return produto -> Produto.builder()
                .categoria(produto.getCategoria())
                .nome(produto.getNome())
                .valor(produto.getValor())
                .build();
    }
}
