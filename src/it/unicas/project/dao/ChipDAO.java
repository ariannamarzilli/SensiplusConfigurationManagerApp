package it.unicas.project.dao;

import it.unicas.project.model.*;
import it.unicas.project.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChipDAO implements CrudDAO<SensingElementOnChip> {

    private static ChipDAO uniqueInstanceOfChipDAO = null;

    /**
     * Returns the unique instance of sensingElementDAO.
     * @return
     */
    public static ChipDAO getInstance() {
        if (uniqueInstanceOfChipDAO == null) {
            uniqueInstanceOfChipDAO = new ChipDAO();
            return uniqueInstanceOfChipDAO;
        } else {
            return uniqueInstanceOfChipDAO;
        }
    }

    /**
     * Inserts in the db a sensingElement passed in.
     * @param sensingElementOnChip The sensingElement to be added.
     */
    @Override
    public void create(SensingElementOnChip sensingElementOnChip) {

        try {
            Connection connection = ConnectionFactory.getConnection();

            String sqlSPChipInsert = "INSERT INTO SPChip (idSPChip, SPFamily_idSPFamily) VALUES (?, ?)";

            String sqlSPChipSelect = "SELECT idSPChip FROM SPChip WHERE '" + sensingElementOnChip.getChip().getId() + "';";


            List<Calibration> calibrations = sensingElementOnChip.getCalibrationList();

            //String sqlIdSPPOrtSelect = "SELECT idSPPort FROM SPPort WHERE name = ?;";

            String sqlSPSensingElementOnChipInsert = "INSERT INTO SPSensingElementOnChip (" +
                    "SPChip_idSPChip, " +
                    "m, " +
                    "n, " +
                    "SPSensingElementOnFamily_idSPSensingElementOnFamily, " +
                    "SPCalibration_idSPCalibration)" +
                    " VALUES (?, ?, ?, ?, ?)";


            String sqlIdSPCalibrationSelect = "SELECT idSPCalibration FROM SPCalibration WHERE name = ?;";

            String sqlIdSensingElementOnFamilySelect = "SELECT idSPSensingElementOnFamily FROM SPSensingElementOnFamily" +
                   " WHERE SPSensingElement_idSPSensingElement = ?;";

            String sqlSPFamilyIDSelect = "SELECT idSPFamily FROM SPFamily WHERE name = ?";



            PreparedStatement statement = connection.prepareStatement(sqlSPChipInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement1 = connection.prepareStatement(sqlSPSensingElementOnChipInsert, Statement.RETURN_GENERATED_KEYS);
            Statement statement2 = connection.prepareStatement(sqlSPChipSelect);
            PreparedStatement statement4 = connection.prepareStatement(sqlIdSensingElementOnFamilySelect);
            PreparedStatement statement5 = connection.prepareStatement(sqlSPFamilyIDSelect);

            ResultSet resultSet1 = statement2.executeQuery(sqlSPChipSelect);

            if (!resultSet1.next()) {

                if (!sensingElementOnChip.getChip().getId().isEmpty()) {
                    statement.setString(1, sensingElementOnChip.getChip().getId());
                } else {
                    statement.setNull(1, Types.VARCHAR);
                }

                if (!sensingElementOnChip.getChip().getFamilyName().isEmpty()) {
                    statement5.setString(2, sensingElementOnChip.getChip().getFamilyName());
                    ResultSet rs = statement5.executeQuery(sqlSPFamilyIDSelect);
                    while (rs.next()){
                        statement.setInt(2, rs.getInt("idSPFamily"));
                    }

                } else {
                    statement.setNull(2, Types.VARCHAR);
                }

                statement.execute();
            }


            for (Calibration temp : calibrations) {


               // String portName = temp.getName();
                String sqlIdCalibrationSelect = "SELECT idSPCalibration FROM SPCalibration WHERE name = '" + temp.getName() + "';";
                Statement statement3 = connection.prepareStatement(sqlIdCalibrationSelect);
                ResultSet resultSet2;
                if (!temp.getName().isEmpty()) {
                    resultSet2 = statement3.executeQuery(sqlIdCalibrationSelect);

                    int idCalibration = 0;

                    while (resultSet2.next()) {

                        idCalibration = resultSet2.getInt("idSPCalibration");
                    }
                    statement1.setInt(5, idCalibration);

                }


                int n = temp.getN();
                int m = temp.getM();

                statement1.setString(1, sensingElementOnChip.getChip().getId());
                statement1.setInt(2, m);
                statement1.setInt(3, n);

                if(!sensingElementOnChip.getIdSensingElement().isEmpty()) {
                    ResultSet resultSet4 = statement4.executeQuery(sqlIdSensingElementOnFamilySelect);
                    int idSensingElementOnFamily = 0;

                    while (resultSet4.next()){
                        idSensingElementOnFamily = resultSet4.getInt("idSPSensingElementOnFamily");
                    }
                    statement1.setInt(4, idSensingElementOnFamily);
                }


                if (!sensingElementOnChip.getIdSensingElement().isEmpty() && !temp.getName().isEmpty()) {
                    ResultSet resultSet = statement1.executeQuery();
                }

            }


            connection.close();
            statement.close();
            statement1.close();
            statement2.close();
            statement4.close();

            } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Deletes from the db the sensingElement passed in.
     * @param sensingElementOnChip The sensingElement to be deleted.
     */
    @Override
    public void delete(SensingElementOnChip sensingElementOnChip) {

        try {
            Connection connection = ConnectionFactory.getConnection();



            String sqlSPChipDelete = "DELETE FROM SPChip WHERE name ='" + sensingElementOnChip.getChip().getId() + "';";

            String sqlSPSensingElementOnFamilyDelete = "DELETE FROM SPSensingElementOnChip WHERE  = '" + sensingElementOnChip.getChip().getId() + "';";

            Statement statement = connection.prepareStatement(sqlSPSensingElementOnFamilyDelete);
            statement.executeUpdate(sqlSPSensingElementOnFamilyDelete);

            Statement statement1 = connection.prepareStatement(sqlSPChipDelete);
            statement1.executeUpdate(sqlSPChipDelete);



            statement.close();
            statement1.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the sensingElementOnChip and the chips whith the same id of the sensingElementOnChip passed in.
     *
     * @param sensingElementOnChip The sensingElement to be updated.
     */
    @Override
    public void update(SensingElementOnChip sensingElementOnChip) {

        try {

            List<Calibration> calibrationList = sensingElementOnChip.getCalibrationList();

            Connection connection = ConnectionFactory.getConnection();

            String sqlUpdateSPChip = "UPDATE SPChip SET " +
                    "SPFamily_idSPFamily = ?" +
                    " WHERE idSPChip = '" + sensingElementOnChip.getChip().getId() + "';";


            String sqlDeleteSPSensingElementOnChip = "DELETE FROM SPSensingElementOnChip WHERE " +
                    "SPChip_idSPChip = '" + sensingElementOnChip.getChip().getId() + "';";


            String sqlSPSensingElementOnChipInsert =
                    "INSERT INTO SPSensingElementOnChip (" +
                            "SPChip_idSPChip, " +
                            "m, " +
                            "n, " +
                            "SPSensingElementOnFamily_idSPSensingElementOnFamily, " +
                            "SPCalibration_idSPCalibration)" +
                            " VALUES (?, ?, ?, ?, ?)";

            String sqlIdSensingElementOnFamilySelect = "SELECT idSPSensingElementOnFamily FROM SPSensingElementOnFamily" +
                    " WHERE SPSensingElement_idSPSensingElement = ?;";



            PreparedStatement statement = connection.prepareStatement(sqlUpdateSPChip);
            Statement statement1 = connection.prepareStatement(sqlDeleteSPSensingElementOnChip);
            PreparedStatement statement2 = connection.prepareStatement(sqlSPSensingElementOnChipInsert);
            PreparedStatement statement4 = connection.prepareStatement(sqlIdSensingElementOnFamilySelect);


            if (!sensingElementOnChip.getChip().getFamilyName().isEmpty()){
                statement.setString(1, sensingElementOnChip.getChip().getFamilyName());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }

            statement1.executeUpdate(sqlDeleteSPSensingElementOnChip);

            for (Calibration temp : calibrationList) {


                String sqlIdCalibrationSelect = "SELECT idSPCalibration FROM SPCalibration WHERE name = '" + temp.getName() + "';";
                Statement statement3 = connection.prepareStatement(sqlIdCalibrationSelect);
                ResultSet resultSet2;
                if (!temp.getName().isEmpty()) {
                    resultSet2 = statement3.executeQuery(sqlIdCalibrationSelect);

                    int idCalibration = 0;

                    while (resultSet2.next()) {

                        idCalibration = resultSet2.getInt("idSPCalibration");
                    }
                    statement2.setInt(5, idCalibration);

                }


                int n = temp.getN();
                int m = temp.getM();

                statement2.setString(1, sensingElementOnChip.getChip().getId());
                statement2.setInt(2, m);
                statement2.setInt(3, n);

                if(!sensingElementOnChip.getIdSensingElement().isEmpty()) {
                    ResultSet resultSet4 = statement4.executeQuery(sqlIdSensingElementOnFamilySelect);
                    int idSensingElementOnFamily = 0;

                    while (resultSet4.next()){
                        idSensingElementOnFamily = resultSet4.getInt("idSPSensingElementOnFamily");
                    }
                    statement2.setInt(4, idSensingElementOnFamily);
                }


                if (!sensingElementOnChip.getIdSensingElement().isEmpty() && !temp.getName().isEmpty()) {
                    ResultSet resultSet = statement1.executeQuery(sqlSPSensingElementOnChipInsert);
                }

            }





            statement.close();
            statement1.close();
            statement2.close();
            statement4.close();


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
    public Iterable<SensingElementOnChip> fetchAll() {

        List<SensingElementOnChip> sensingElementOnChips = new ArrayList<>();

        try {
            Connection connection = ConnectionFactory.getConnection();

            String sqlSPChipSelect = "SELECT * FROM SPChip";
            String sqlSPSensingElementOnChipSelect = "SELECT (SPSensingElementOnFamily_idSPSensingElementOnFamily)" +
                    " FROM SPSensingElementOnChip WHERE SPChip_idSPChip = ?";
            String sqlSPCalibrationsSelect = "SELECT (n, m, SPCalibration_idSPCalibration FROM SPSensingElementOnFamily WHERE SPChip_idSPChip = ?";
            String sqlSPSensingElementIDSelect = "SELECT SPSensingElement_idSPSensingElement FROM SPSensingElementOnFamily WHERE idSPSensingElementOnFamily = ?";
            String sqlSPCalibrationNameSelect = "SELECT name FROM SPCalibration WHERE idSPCalibration = ?";
            String sqlSPFamilyNameSelect = "SELECT name FROM SPFamily WHERE idSPFamily = ?";



            Statement statement = connection.prepareStatement(sqlSPChipSelect);
            ResultSet resultSet = statement.executeQuery(sqlSPChipSelect);

            PreparedStatement statement1 = connection.prepareStatement(sqlSPSensingElementOnChipSelect);
            PreparedStatement statement2 = connection.prepareStatement(sqlSPSensingElementIDSelect);
            PreparedStatement statement3 = connection.prepareStatement(sqlSPCalibrationNameSelect);
            PreparedStatement statement4 = connection.prepareStatement(sqlSPFamilyNameSelect);
            PreparedStatement statement5 = connection.prepareStatement(sqlSPCalibrationsSelect);



            while (resultSet.next()) {
                String chipId = resultSet.getString("idSPChip");
                int familyId = resultSet.getInt("SPFamily_idSPFamily");

                statement4.setInt(1, familyId);

                ResultSet rsFamily = statement4.executeQuery();

                String familyName = "";

                while(rsFamily.next()){

                    familyName = rsFamily.getString("name");

                }


                statement2.setString(1, chipId);

                ResultSet rsSEOnFamily = statement2.executeQuery();


                int n = 0, m = 0, sensingElementOnFamilyId = 0, calibrationId = 0;

                while (rsSEOnFamily.next()){

                    sensingElementOnFamilyId = rsSEOnFamily.getInt(4);
                    //calibrationId = rsSEOnFamily.getInt(5);
                }

                statement2.setInt(1, sensingElementOnFamilyId);

                ResultSet rsSensingElement = statement2.executeQuery();

                String sensingElementId = "";

                while (rsSensingElement.next()){
                    sensingElementId = rsSensingElement.getString("idSPSensingElement");
                }


                List<Calibration> calibrations = new ArrayList<>();

                statement5.setString(1, chipId);

                ResultSet rsCalibrations = statement5.executeQuery();

                while (rsCalibrations.next()) {

                    statement3.setInt(1, rsCalibrations.getInt("SPCalibration_idSPCalibration"));

                    ResultSet rsCalibration = statement3.executeQuery();

                    String calibrationName = "";

                    n = rsCalibrations.getInt("n");
                    m = rsCalibrations.getInt("m");

                    while (rsCalibration.next()) {
                        calibrationName = rsCalibration.getString("name");
                    }

                    Calibration calibration = new Calibration(calibrationName, n, m);
                    calibrations.add(calibration);
                }
                Chip chip = new Chip(familyName, chipId);

                SensingElementOnChip sensingElementOnChip = new SensingElementOnChip(chip, calibrations, sensingElementId);


                sensingElementOnChips.add(sensingElementOnChip);
            }
            statement.close();
            statement1.close();
            statement3.close();
            statement4.close();
            statement5.close();
            statement2.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sensingElementOnChips;
    }





}



