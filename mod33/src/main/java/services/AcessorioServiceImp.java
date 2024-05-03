package main.java.services;

import main.java.dao.generic.GenericDao;
import main.java.domain.Acessorio;
import main.java.services.generic.GenericServiceImp;

public class AcessorioServiceImp extends GenericServiceImp<Acessorio, Long>
        implements AcessorioService {

    public AcessorioServiceImp(GenericDao<Acessorio, Long> dao) {
        super(dao);
    }
}