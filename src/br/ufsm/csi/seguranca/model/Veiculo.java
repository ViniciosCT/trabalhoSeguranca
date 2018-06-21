package br.ufsm.csi.seguranca.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Veiculo")
public class Veiculo {

    private Long id;
    private String placa;
    private Cliente cliente;
    private Date dataEntrada;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_veiculo")
    @SequenceGenerator(name = "seq_veiculo", sequenceName = "seq_veiculo")
    @Column(name="id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(name = "placa", length = 50)
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Column(name = "data")
    public Date getDataEntrada() {
        return dataEntrada;
    }

    public void setDataEntrada(Date dataEntrada) {
        this.dataEntrada = dataEntrada;
    }
}
