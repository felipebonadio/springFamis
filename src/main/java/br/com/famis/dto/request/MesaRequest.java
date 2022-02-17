package br.com.famis.dto.request;

import br.com.famis.model.Colaborador;
import lombok.Data;

@Data
public class MesaRequest {
    Integer numero;

    Colaborador colaborador;

    public MesaRequest(int numero, Colaborador colaborador) {
        this.numero = numero;
        this.colaborador = colaborador;
    } 
    
    public MesaRequest(){
        
    }
}