package br.com.famis.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class MesaDto {

    private Long id;

    private Integer numero;

    private String colaboradorNome;

    private List<ProdutoDto> produtos;

    public void adicionarProduto(ProdutoDto produtoDto) {
        this.produtos.add(produtoDto);
    }

    public void removerProduto(ProdutoDto produtoDto) {
        this.produtos.remove(produtoDto);
    }

    public MesaDto(Integer numero, String colaboradorNome) {
        this.numero = numero;
        this.colaboradorNome = colaboradorNome;
    }
}
