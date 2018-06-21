package br.ufsm.csi.seguranca.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "Cliente")
public class Cliente {

    private Long id;
    private String nome;
    private Collection<Veiculo> veiculos;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_cliente")
    @SequenceGenerator(name = "seq_cliente", sequenceName = "seq_cliente")
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "nome", length = 100)
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @OneToMany(mappedBy = "cliente")
    public Collection<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(Collection<Veiculo> veiculo) {
        this.veiculos = veiculo;
    }
}
