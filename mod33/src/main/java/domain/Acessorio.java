package main.java.domain;

import main.java.dao.generic.Persistente;

import javax.persistence.*;

@Entity
@Table(name = "tb_acessorio")
public class Acessorio implements Persistente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acessorio_seq")
    @SequenceGenerator(name = "acessorio_seq", sequenceName = "sq_acessorio", initialValue = 10,
            allocationSize = 1)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @Column(length = 100, nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_carro_fk",
            foreignKey = @ForeignKey(name = "fk_carro_acessorio"),
            referencedColumnName = "id", nullable = false
    )
    private Carro carro;


    public Acessorio(String nome, String descricao, Carro carro) {
        this.nome = nome;
        this.descricao = descricao;
        this.carro = carro;
    }

    @Deprecated
    public Acessorio() {
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
