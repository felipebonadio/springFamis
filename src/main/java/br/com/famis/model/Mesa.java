package br.com.famis.model;

import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Entity
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Integer numero;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "colaborador_id")
    private Colaborador colaborador;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name = "produto_id")
    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(Produto produto) {
        this.produtos.add(produto);
    }

    public void removerProduto(Produto produto) {
        this.produtos.remove(produto);
    }

    public Mesa(Integer numero, Colaborador colaborador) {
        this.numero = numero;
        this.colaborador = colaborador;
    }
}
