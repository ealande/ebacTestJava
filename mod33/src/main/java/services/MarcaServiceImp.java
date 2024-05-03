package main.java.services;

import main.java.dao.generic.GenericDao;
import main.java.domain.Marca;
import main.java.services.generic.GenericServiceImp;

public class MarcaServiceImp extends GenericServiceImp<Marca, Long> {
    public MarcaServiceImp(GenericDao<Marca, Long> dao) {
        super(dao);
    }
}
