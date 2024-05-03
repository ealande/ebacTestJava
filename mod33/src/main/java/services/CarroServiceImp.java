package main.java.services;

import main.java.dao.generic.GenericDaoImp;
import main.java.domain.Carro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class CarroServiceImp extends GenericDaoImp<Carro, Long> {
    protected CarroServiceImp() {
        super();
    }
}
