package it.unicas.project.dao;

import it.unicas.project.model.Configuration;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationDAO implements CrudDAO<Configuration> {

    /*
    private static ConfigurationDAO uniqueInstanceOfConfigurationDAO = null;

    /**
     * Returns the unique instance of configurationDAO.
     * @return
     */

    /*
    public static SensingElementDAO getInstance() {
        if (uniqueInstanceOfConfigurationDAO == null) {
            uniqueInstanceOfConfigurationDAO = new ConfigurationDAO();
            return uniqueInstanceOfConfigurationDAO;
        } else {
            return uniqueInstanceOfConfigurationDAO;
        }
    }

    */

    @Override
    public void create(Configuration configuration) {}

    @Override
    public void delete(Configuration configuration) {}

    @Override
    public void update(Configuration configuration) {}

    @Override
    public List<Configuration> fetchAll() {

        List<Configuration> configurations = new ArrayList<>();
        return configurations;
    }

}
