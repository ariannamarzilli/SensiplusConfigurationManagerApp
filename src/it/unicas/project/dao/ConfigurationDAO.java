package it.unicas.project.dao;

import it.unicas.project.model.Configuration;
<<<<<<< HEAD
import it.unicas.project.util.ConnectionFactory;

import java.sql.*;
=======

>>>>>>> 1bba8cf022dd3829bba439921b8280859eabd3a6
import java.util.ArrayList;
import java.util.List;

public class ConfigurationDAO implements CrudDAO<Configuration> {

<<<<<<< HEAD
    private static ConfigurationDAO uniqueInstanceOfConfigurationDAO = null;

    /**
     * Returns the unique instance of sensingElementDAO.
     * @return
     */
    public static ConfigurationDAO getInstance() {
=======
    /*
    private static ConfigurationDAO uniqueInstanceOfConfigurationDAO = null;

    /**
     * Returns the unique instance of configurationDAO.
     * @return
     */

    /*
    public static SensingElementDAO getInstance() {
>>>>>>> 1bba8cf022dd3829bba439921b8280859eabd3a6
        if (uniqueInstanceOfConfigurationDAO == null) {
            uniqueInstanceOfConfigurationDAO = new ConfigurationDAO();
            return uniqueInstanceOfConfigurationDAO;
        } else {
            return uniqueInstanceOfConfigurationDAO;
        }
    }

<<<<<<< HEAD
    /**
     * Inserts in the db a sensingElement passed in.
     * @param configuration The sensingElement to be added.
     */
    @Override
    public void create(Configuration configuration) {

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO SPConfiguration (" +
                    "driver, " +
                    "hostController, " +
                    "apiOwner, " +
                    "mcu, " +
                    "protocol, " +
                    "addressingType, " +
                    "idCluster, " +
                    ")" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?)";


            PreparedStatement statement = connection.prepareStatement(sql);

            if (!configuration.getDriver().isEmpty()) {
                statement.setString(1, configuration.getDriver());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }

            if (!configuration.getHostController().isEmpty()) {
                statement.setString(2, configuration.getHostController());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }

            if (!configuration.getApiOwner().isEmpty()) {
                statement.setString(3, configuration.getApiOwner());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }

            if (!configuration.getMcu().isEmpty()) {
                statement.setString(4, configuration.getMcu());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }

            if (!configuration.getProtocol().isEmpty()) {
                statement.setString(5, configuration.getProtocol());
            } else {
                statement.setNull(5, Types.VARCHAR);
            }

            if (!configuration.getAddressingType().isEmpty()) {
                statement.setString(6, configuration.getAddressingType());
            } else {
                statement.setNull(6, Types.VARCHAR);
            }

            statement.setString(7, configuration.getIdCluster());

            statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes from the db the sensingElement passed in.
     * @param configuration The sensingElement to be deleted.
     */
    @Override
    public void delete(Configuration configuration) {

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM SPConfiguraion WHERE idSPConfiguraion = '" + configuration.getId() + "';";
            Statement statement = connection.prepareStatement(sql);
            statement.executeUpdate(sql);

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Configuration configuration) {

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "UPDATE SPConfiguration SET " +
                    "driver = ?, " +
                    "hostController = ?, " +
                    "apiOwner = ?, " +
                    "mcu = ?, " +
                    "protocol = ?, " +
                    "addressingType = ?, " +
                    "idCluster = ? " +
                    " WHERE idSPConfiguration = ? ;";

            PreparedStatement statement = connection.prepareStatement(sql);


            if (!configuration.getDriver().isEmpty()) {
                statement.setString(1, configuration.getDriver());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }

            if (!configuration.getHostController().isEmpty()) {
                statement.setString(2, configuration.getHostController());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }

            if (!configuration.getApiOwner().isEmpty()) {
                statement.setString(3, configuration.getApiOwner());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }

            if (!configuration.getMcu().isEmpty()) {
                statement.setString(4, configuration.getMcu());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }

            if (configuration.getProtocol().isEmpty()) {
                statement.setString(5, configuration.getProtocol());
            } else {
                statement.setNull(5, Types.DOUBLE);
            }

            if (!configuration.getAddressingType().isEmpty()) {
                statement.setString(6, configuration.getAddressingType());
            } else {
                statement.setNull(6, Types.VARCHAR);
            }

            statement.setString(7, configuration.getIdCluster());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Fetches all the sensingElements.
     * @return All the sensingElements.
     */
=======
    */

    @Override
    public void create(Configuration configuration) {}

    @Override
    public void delete(Configuration configuration) {}

    @Override
    public void update(Configuration configuration) {}

>>>>>>> 1bba8cf022dd3829bba439921b8280859eabd3a6
    @Override
    public List<Configuration> fetchAll() {

        List<Configuration> configurations = new ArrayList<>();
<<<<<<< HEAD

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM SPConfiguration";
            Statement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                configurations.add(new Configuration(resultSet.getString("driver"),
                        resultSet.getString("hostController"),
                        resultSet.getString("apiOwner"),
                        resultSet.getString("mcu"),
                        resultSet.getString("protocol"),
                        resultSet.getString("addressingType"),
                        resultSet.getString("idCluster"),
                        resultSet.getInt("idSPConfiguration")));
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return configurations;
    }



=======
        return configurations;
    }

>>>>>>> 1bba8cf022dd3829bba439921b8280859eabd3a6
}