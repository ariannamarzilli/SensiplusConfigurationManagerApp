package it.unicas.project.dao;

import it.unicas.project.model.SensingElement;
import it.unicas.project.util.ConnectionFactory;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implements the CrudDAO interface.
 *
 * Utilizes the design pattern Singleton.
 */
public class SensingElementDAO implements CrudDAO<SensingElement> {

    private static SensingElementDAO uniqueInstanceOfSensingElementDAO = null;

    /**
     * Returns the unique instance of sensingElementDAO.
     * @return
     */
    public static SensingElementDAO getInstance() {
        if (uniqueInstanceOfSensingElementDAO == null) {
            uniqueInstanceOfSensingElementDAO = new SensingElementDAO();
            return uniqueInstanceOfSensingElementDAO;
        } else {
            return uniqueInstanceOfSensingElementDAO;
        }
    }

    /**
     * Inserts in the db a sensingElement passed in.
     * @param sensingElement The sensingElement to be added.
     */
    @Override
    public void create(SensingElement sensingElement) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "INSERT INTO SPSensingElement (" +
                    "idSPSensingElement, " +
                    "rSense, " +
                    "inGain, " +
                    "outGain, " +
                    "contacts, " +
                    "frequency, " +
                    "harmonic, " +
                    "DCBias, " +
                    "modeVI, " +
                    "measureTechnique, " +
                    "measureType, " +
                    "filter, " +
                    "phaseShiftMode, " +
                    "phaseShift, " +
                    "IQ, " +
                    "conversionRate, " +
                    "inPortADC, " +
                    "nData, " +
                    "name, " +
                    "rangeMin, " +
                    "raneMax, " +
                    "defaultAlarmThreshold, " +
                    "multiplier, " +
                    "measureUnit" +
                    ")" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, sensingElement.getId());
            statement.setInt(2, sensingElement.getrSense());
            statement.setInt(3, sensingElement.getInGain());
            statement.setInt(4, sensingElement.getOutGain());
            statement.setString(5, sensingElement.getContacts());
            statement.setInt(6, sensingElement.getFrequency());
            statement.setString(7, sensingElement.getHarmonic());
            statement.setInt(8, sensingElement.getDcBias());
            statement.setString(9, sensingElement.getModeVI());
            statement.setString(10, sensingElement.getMeasureTechnique());
            statement.setString(11, sensingElement.getMeasureType());
            statement.setInt(12, sensingElement.getFilter());
            statement.setString(13, sensingElement.getPhaseShiftMode());
            statement.setInt(14, sensingElement.getPhaseShift());
            statement.setString(15, sensingElement.getIq());
            statement.setInt(16, sensingElement.getConversionRate());
            statement.setString(17, sensingElement.getInPortADC());
            statement.setInt(18, sensingElement.getnData());
            statement.setString(19, sensingElement.getName());
            statement.setDouble(20, sensingElement.getRangeMin());
            statement.setDouble(21, sensingElement.getRangeMax());
            statement.setDouble(22, sensingElement.getDefaultAlarmThreshold());
            statement.setInt(23, sensingElement.getMultiplier());
            statement.setString(24, sensingElement.getMeasureUnit());
            statement.executeUpdate();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes from the db the sensingElement passed in.
     * @param sensingElement The sensingElement to be deleted.
     */
    @Override
    public void delete(SensingElement sensingElement) {

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "DELETE FROM SPSensingElement WHERE idSPSensingElement ='" + sensingElement.getId() + "';";
            Statement statement = connection.prepareStatement(sql);
            statement.executeUpdate(sql);

            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the sensingElement whith the same id of the sensingElement passed in.
     *
     * @param sensingElement The sensingElement to be updated.
     */
    @Override
    public void update(SensingElement sensingElement) {
        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "UPDATE SPSensingElement SET " +
                    " rSense = '" + sensingElement.getrSense() + "' " +
                    ", inGain = '" + sensingElement.getInGain() + "' " +
                    ", outGain = '" + sensingElement.getOutGain() + "' " +
                    ", contacts = '" + sensingElement.getContacts() + "' " +
                    ", frequency = '" + sensingElement.getFrequency() + "' " +
                    ", harmonic = '" + sensingElement.getHarmonic() + "' " +
                    ", DCBias = '" + sensingElement.getDcBias() + "' " +
                    ", modeVI = '" + sensingElement.getModeVI() + "' " +
                    ", measureTechnique = '" + sensingElement.getMeasureTechnique() + "' " +
                    ", measureType = '" + sensingElement.getMeasureType() + "' " +
                    ", filter = '" + sensingElement.getFilter() + "' " +
                    ", phaseShiftMode = '" + sensingElement.getPhaseShiftMode() + "' " +
                    ", PhaseShift = '" + sensingElement.getPhaseShift() + "' " +
                    ", IQ = '" + sensingElement.getIq() + "' " +
                    ", conversionRate = '" + sensingElement.getConversionRate() + "' " +
                    ", inPortADC = '" + sensingElement.getInPortADC() + "' " +
                    ", nData = '" + sensingElement.getnData() + "' " +
                    ", name = '" + sensingElement.getName() + "' " +
                    ", rangeMin = '" + sensingElement.getRangeMin() + "' " +
                    ", raneMax = '" + sensingElement.getRangeMax() + "' " +
                    ", defaultAlarmThreshold = '" + sensingElement.getDefaultAlarmThreshold() + "' " +
                    ", multiplier = '" + sensingElement.getMultiplier() + "' " +
                    ", measureUnit = '" + sensingElement.getMeasureUnit() + "' " +
                    " WHERE idSPSensingElement = '" + sensingElement.getId() + "';";

            Statement statement = connection.prepareStatement(sql);
            statement.executeUpdate(sql);
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
    @Override
    public Iterable<SensingElement> fetchAll() {

        List<SensingElement> sensingElements = new ArrayList<>();

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM SPSensingElement";
            Statement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                sensingElements.add(new SensingElement(resultSet.getString("idSPSensingElement"),
                        resultSet.getInt("rSense"),
                        resultSet.getInt("inGain"),
                        resultSet.getInt("outGain"),
                        resultSet.getString("contacts"),
                        resultSet.getInt("frequency"),
                        resultSet.getString("harmonic"),
                        resultSet.getInt("DCBias"),
                        resultSet.getString("modeVI"),
                        resultSet.getString("measureTechnique"),
                        resultSet.getString("measureType"),
                        resultSet.getInt("filter"),
                        resultSet.getString("phaseShiftMode"),
                        resultSet.getInt("phaseShift"),
                        resultSet.getString("IQ"),
                        resultSet.getInt("conversionRate"),
                        resultSet.getString("inPortADC"),
                        resultSet.getInt("nData"),
                        resultSet.getString("name"),
                        resultSet.getDouble("rangeMin"),
                        resultSet.getDouble("raneMax"),
                        resultSet.getDouble("defaultAlarmThreshold"),
                        resultSet.getInt("multiplier"),
                        resultSet.getString("measureUnit")));
            }
            resultSet.close();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sensingElements;
    }

}
