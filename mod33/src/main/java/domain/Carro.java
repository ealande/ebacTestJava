package main.java.domain;

import main.java.dao.generic.Persistente;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_carro")
public class Carro implements Persistente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "carro_seq")
    @SequenceGenerator(name = "carro_seq", sequenceName = "sq_carro", initialValue = 10,
            allocationSize = 1)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @ManyToOne
    @JoinColumn(name = "id_marca_fk",
            foreignKey = @ForeignKey(name = "fk_marca_carro"),
            referencedColumnName = "id", nullable = false
    )
    private Marca marca;

    @OneToMany(mappedBy = "carro")
    private List<Acessorio> acessorios;

    public Carro(String nome, Marca marca, List<Acessorio> acessorios) {
        this.nome = nome;
        this.marca = marca;
        this.acessorios = acessorios;
    }

    @Deprecated
    public Carro() {

    }

    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
}
