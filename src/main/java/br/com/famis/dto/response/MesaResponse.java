package br.com.famis.dto.response;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import br.com.famis.model.Mesa;
import br.com.famis.model.Produto;
import lombok.Data;

@Data
public class MesaResponse {

    private UUID id;

    private Integer numero;

    private String colaboradorNome;

    private List<Produto> produtos;

    public MesaResponse(Optional<Mesa> mesa) {
        this.id = mesa.get().getId();
        this.numero = mesa.get().getNumero();
        this.colaboradorNome = mesa.get().getColaborador().getNome() + " " + mesa.get().getColaborador().getSobrenome();
        this.produtos = mesa.get().getProdutos();
    }

    public MesaResponse() {
    }

    public static MesaResponse converterParaDto(Mesa mesa){
        MesaResponse dto = new MesaResponse();
        dto.setId(mesa.getId());
        dto.setNumero(mesa.getNumero());
        dto.setColaboradorNome(mesa.getColaborador().getNome() + " "+ mesa.getColaborador().getSobrenome());
        dto.setProdutos(mesa.getProdutos());
        return dto;
    }

}
