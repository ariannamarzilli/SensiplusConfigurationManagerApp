package it.unicas.project.dao;

import it.unicas.project.model.SensingElement;
import it.unicas.project.util.ConnectionFactory;
import javafx.collections.ObservableList;

import java.sql.*;
import java.util.ArrayList;

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
            String sql = "INSERT INTO SensingElement (" +
                    "idSensingElement, " +
                    "rSense, " +
                    "inGain, " +
                    "outGain, " +
                    "contacts, " +
                    "frequency, " +
                    "Harmonic, " +
                    "DCBias, " +
                    "ModeVI, " +
                    "MeasureTechnique, " +
                    "Filter, " +
                    "PhaseShiftMode, " +
                    "PhaseShift, " +
                    "IQ, " +
                    "ConversionRate, " +
                    "InPortADC, " +
                    "NData, " +
                    "MEASURE_UNIT" +
                    ")" +
                    " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            statement.setInt(11, sensingElement.getFilter());
            statement.setString(12, sensingElement.getPhaseShiftMode());
            statement.setInt(13, sensingElement.getPhaseShift());
            statement.setString(14, sensingElement.getIq());
            statement.setInt(15, sensingElement.getConversionRate());
            statement.setString(16, sensingElement.getInPortADC());
            statement.setInt(17, sensingElement.getnData());
            statement.setString(18, sensingElement.getMeasureUnit());
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
            String sql = "DELETE FROM SensingElement WHERE idSensingElement ='" + sensingElement.getId() + "';";
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
            String sql = "UPDATE SensingElement SET " +
                    "idSensingElement = " + sensingElement.getId() +
                    ", rSense = " + sensingElement.getrSense() +
                    ", inGain = " + sensingElement.getInGain() +
                    ", outGain = " + sensingElement.getOutGain() +
                    ", contacts = " + sensingElement.getContacts() +
                    ", frequency = " + sensingElement.getFrequency() +
                    ", Harmonic = " + sensingElement.getHarmonic() +
                    ", DCBias = " + sensingElement.getDcBias() +
                    ", ModeVI = " + sensingElement.getModeVI() +
                    ", MeasureTechnique = " + sensingElement.getMeasureTechnique() +
                    ", Filter = " + sensingElement.getFilter() +
                    ", PhaseShiftMode = " + sensingElement.getPhaseShiftMode() +
                    ", PhaseShift = " + sensingElement.getPhaseShift() +
                    ", IQ = " + sensingElement.getIq() +
                    ", ConversionRate = " + sensingElement.getConversionRate() +
                    ", InPortADC = " + sensingElement.getInPortADC() +
                    ", NData = " + sensingElement.getnData() +
                    ", MEASURE_UNIT = " + sensingElement.getMeasureUnit() +
                    " WHERE idSensingElement = " + sensingElement.getId() + ";";

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
    public ObservableList<SensingElement> fetchAll() {

        ObservableList<SensingElement> sensingElements = null;

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM SensingElement";
            Statement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                sensingElements.add(new SensingElement(resultSet.getString("idSensingElement"),
                        resultSet.getInt("rSense"),
                        resultSet.getInt("inGain"),
                        resultSet.getInt("outGain"),
                        resultSet.getString("contacts"),
                        resultSet.getInt("frequency"),
                        resultSet.getString("harmonic"),
                        resultSet.getInt("dcBias"),
                        resultSet.getString("modeVI"),
                        resultSet.getString("measureTechnique"),
                        resultSet.getString("measureType"),
                        resultSet.getInt("filter"),
                        resultSet.getString("phaseShiftMode"),
                        resultSet.getInt("phaseShift"),
                        resultSet.getString("iq"),
                        resultSet.getInt("conversionRate"),
                        resultSet.getString("inPortADC"),
                        resultSet.getInt("nData"),
                        resultSet.getString("measure_unit")));
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
