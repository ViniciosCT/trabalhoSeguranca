package br.ufsm.csi.seguranca.model;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by cpol on 29/05/2017.@localhost
 */
@Entity
@Table(name = "Funcionario")
public class Funcionario {

    private Long id;
    private String nome;
    private String login;
    private byte[] senha;
    private Collection<Log> logs;
    private Veiculo veiculo;

    @OneToOne
    @JoinColumn(name = "id_veiculo")
    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }

    @OneToMany(mappedBy = "funcionario")
    public Collection<Log> getLogs() {
        return logs;
    }

    public void setLogs(Collection<Log> logs) {
        this.logs = logs;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_funcionario")
    @SequenceGenerator(name = "seq_funcionario", sequenceName = "seq_funcionario")
    @Column(name = "id")
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

    @Column(name = "login", length = 100, unique = true)
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Column(name = "senha", length = 50)
    public byte[] getSenha() {
        return senha;
    }

    public void setSenha(byte[] senha) {
        this.senha = senha;
    }
}
