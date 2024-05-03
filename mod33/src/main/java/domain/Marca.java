package main.java.domain;

import main.java.dao.generic.Persistente;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tb_marca")
public class Marca implements Persistente {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "marca_seq")
    @SequenceGenerator(name = "marca_seq", sequenceName = "marca_produto", initialValue = 10,
            allocationSize = 1)
    private Long id;

    @Column(length = 50, nullable = false)
    private String nome;

    @OneToMany(mappedBy = "marca")
    private List<Carro> carro;


    public Marca(String nome, List<Carro> carro) {
        this.nome = nome;
        this.carro = carro;
    }

    @Deprecated
    public Marca() {
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
