package it.unicas.project.dao;

import it.unicas.project.model.SensingElement;
import it.unicas.project.model.SensingElementOnChip;
import it.unicas.project.util.ConnectionFactory;
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
    Integer emptyIntegerForDirectMeasure = Integer.MAX_VALUE;
    Double emptyDoubleForDirectMeasure = Double.MAX_VALUE;

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

            if (!sensingElement.getrSense().isEmpty()) {
                statement.setString(2, sensingElement.getrSense());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }

            if (!sensingElement.getInGain().isEmpty()) {
                statement.setString(3, sensingElement.getInGain());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }

            if (!sensingElement.getOutGain().isEmpty()) {
                statement.setString(4, sensingElement.getOutGain());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }

            if (!sensingElement.getContacts().isEmpty()) {
                statement.setString(5, sensingElement.getContacts());
            } else {
                statement.setNull(5, Types.VARCHAR);
            }

            if (!sensingElement.getFrequency().equals(emptyDoubleForDirectMeasure)) {
                statement.setDouble(6, sensingElement.getFrequency());
            } else {
                statement.setNull(6, Types.DOUBLE);
            }

            if (!sensingElement.getHarmonic().isEmpty()) {
                statement.setString(7, sensingElement.getHarmonic());
            } else {
                statement.setNull(7, Types.VARCHAR);
            }

            if (!sensingElement.getDcBias().equals(emptyIntegerForDirectMeasure)) {
                statement.setInt(8, sensingElement.getDcBias());
            } else {
                statement.setNull(8, Types.INTEGER);
            }

            if (!sensingElement.getModeVI().isEmpty()) {
                statement.setString(9, sensingElement.getModeVI());
            } else {
                statement.setNull(9, Types.VARCHAR);
            }

            statement.setString(10, sensingElement.getMeasureTechnique());

            if (!sensingElement.getMeasureType().isEmpty()) {
                statement.setString(11, sensingElement.getMeasureType());
            } else {
                statement.setNull(11, Types.VARCHAR);
            }

            statement.setInt(12, sensingElement.getFilter());

            if (!sensingElement.getPhaseShiftMode().isEmpty()) {
                statement.setString(13, sensingElement.getPhaseShiftMode());
            } else {
                statement.setNull(13, Types.VARCHAR);
            }

            if (!sensingElement.getPhaseShift().equals(emptyDoubleForDirectMeasure)) {
                statement.setDouble(14, sensingElement.getPhaseShift());
            } else {
                statement.setNull(14, Types.DOUBLE);
            }

            if (!sensingElement.getIq().isEmpty()) {
                statement.setString(15, sensingElement.getIq());
            } else {
                statement.setNull(15, Types.VARCHAR);
            }

            statement.setDouble(16, sensingElement.getConversionRate());
            statement.setString(17, sensingElement.getInPortADC());
            statement.setInt(18, sensingElement.getnData());

            if (!sensingElement.getName().isEmpty()) {
                statement.setString(19, sensingElement.getName());
            } else {
                statement.setNull(19, Types.VARCHAR);
            }

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
                    "rSense = ?, inGain = ?, outGain = ?, contacts = ?, " +
                    "frequency = ?, harmonic = ?, DCBias = ?, modeVI = ?, " +
                    "measureTechnique = ?, measureType = ?, filter = ?, "+
                    "phaseShiftMode = ?, PhaseShift = ?, IQ = ?, " +
                    "conversionRate = ?, inPortADC = ?, nData = ?, " +
                    "name = ?, rangeMin = ?, raneMax = ?, defaultAlarmThreshold = ?, " +
                    "multiplier = ?, measureUnit = ? " +
                    "WHERE idSPSensingElement = ? ;";

            PreparedStatement statement = connection.prepareStatement(sql);


            if (!sensingElement.getrSense().isEmpty()) {
                statement.setString(1, sensingElement.getrSense());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }

            if (!sensingElement.getInGain().isEmpty()) {
                statement.setString(2, sensingElement.getInGain());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }

            if (!sensingElement.getOutGain().isEmpty()) {
                statement.setString(3, sensingElement.getOutGain());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }

            if (!sensingElement.getContacts().isEmpty()) {
                statement.setString(4, sensingElement.getContacts());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }

            if (sensingElement.getFrequency() != 0.0) {
                statement.setDouble(5, sensingElement.getFrequency());
            } else {
                statement.setNull(5, Types.DOUBLE);
            }

            if (!sensingElement.getHarmonic().isEmpty()) {
                statement.setString(6, sensingElement.getHarmonic());
            } else {
                statement.setNull(6, Types.VARCHAR);
            }

            if (sensingElement.getDcBias() != 0) {
                statement.setInt(7, sensingElement.getDcBias());
            } else {
                statement.setNull(7, Types.INTEGER);
            }

            if (!sensingElement.getModeVI().isEmpty()) {
                statement.setString(8, sensingElement.getModeVI());
            } else {
                statement.setNull(8, Types.VARCHAR);
            }

            statement.setString(9, sensingElement.getMeasureTechnique());

            if (!sensingElement.getMeasureType().isEmpty()) {
                statement.setString(10, sensingElement.getMeasureType());
            } else {
                statement.setNull(10, Types.VARCHAR);
            }

            statement.setInt(11, sensingElement.getFilter());

            if (!sensingElement.getPhaseShiftMode().isEmpty()) {
                statement.setString(12, sensingElement.getPhaseShiftMode());
            } else {
                statement.setNull(12, Types.VARCHAR);
            }

            if (sensingElement.getPhaseShift() != 0) {
                statement.setDouble(13, sensingElement.getPhaseShift());
            } else {
                statement.setNull(13, Types.DOUBLE);
            }

            if (!sensingElement.getIq().isEmpty()) {
                statement.setString(14, sensingElement.getIq());
            } else {
                statement.setNull(14, Types.VARCHAR);
            }

            statement.setDouble(15, sensingElement.getConversionRate());
            statement.setString(16, sensingElement.getInPortADC());
            statement.setInt(17, sensingElement.getnData());

            if (!sensingElement.getName().isEmpty()) {
                statement.setString(18, sensingElement.getName());
            } else {
                statement.setNull(18, Types.VARCHAR);
            }

            statement.setDouble(19, sensingElement.getRangeMin());
            statement.setDouble(20, sensingElement.getRangeMax());
            statement.setDouble(21, sensingElement.getDefaultAlarmThreshold());
            statement.setInt(22, sensingElement.getMultiplier());
            statement.setString(23, sensingElement.getMeasureUnit());
            statement.setString(24, sensingElement.getId());
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
    @Override
    public Iterable<SensingElement> fetchAll() {

        List<SensingElement> sensingElements = new ArrayList<>();

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM SPSensingElement";
            Statement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                String sqlCalibrationSelect = "SELECT SPCalibration_idSPCalibration WHERE SPSensingElement_idSPSensingElement = '" +
                        resultSet.getString("idSPSensingElement") + "';";

                Statement statement1 = connection.prepareStatement(sqlCalibrationSelect);
                ResultSet resultSet1 = statement1.executeQuery(sqlCalibrationSelect);

                sensingElements.add(new SensingElement(resultSet.getString("idSPSensingElement"),
                        resultSet.getString("rSense"),
                        resultSet.getString("inGain"),
                        resultSet.getString("outGain"),
                        resultSet.getString("contacts"),
                        resultSet.getDouble("frequency"),
                        resultSet.getString("harmonic"),
                        resultSet.getInt("DCBias"),
                        resultSet.getString("modeVI"),
                        resultSet.getString("measureTechnique"),
                        resultSet.getString("measureType"),
                        resultSet.getInt("filter"),
                        resultSet.getString("phaseShiftMode"),
                        resultSet.getDouble("phaseShift"),
                        resultSet.getString("IQ"),
                        resultSet.getDouble("conversionRate"),
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
