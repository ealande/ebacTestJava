package main.java.dao;

import main.java.dao.generic.GenericDaoImp;
import main.java.domain.Carro;

public class CarroDaoImp extends GenericDaoImp<Carro, Long> implements CarroDao {
    public CarroDaoImp() {
        super();
    }
}
