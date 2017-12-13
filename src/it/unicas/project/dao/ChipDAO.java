package it.unicas.project.dao;

import it.unicas.project.model.*;
import it.unicas.project.util.ConnectionFactory;

import java.sql.*;
import java.util.List;

public class ChipDAO implements CrudDAO<Chip> {

    private static ChipDAO uniqueInstanceOfChipDAO = null;

    /**
     * Returns the unique instance of sensingElementDAO.
     * @return
     */
    public static FamilyDAO getInstance() {
        if (uniqueInstanceOfChipDAO == null) {
            uniqueInstanceOfChipDAO = new FamilyDAO();
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

            /*String sqlSPSensingElementOnFamilyInsert =
                    "INSERT INTO SPSensingElementOnFamily (" +
                            "SPSensingElement_idSPSensingElement, " +
                            "SPFamilyTemplate_idSPFamilyTemplate, " +
                            "name)" +
                            " VALUES (?, ?, ?)";   */

            //List<String> measureTypeList = family.getMeasureType();

            String sqlIdSPCalibrationSelect = "SELECT idSPCalibration FROM SPCalibration WHERE name = ?;";



            PreparedStatement statement = connection.prepareStatement(sqlSPChipInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement1 = connection.prepareStatement(sqlSPSensingElementOnChipInsert, Statement.RETURN_GENERATED_KEYS);
            Statement statement2 = connection.prepareStatement(sqlSPChipSelect);

            ResultSet resultSet1 = statement2.executeQuery(sqlSPChipSelect);

            if (!resultSet1.next()) {

                if (!sensingElementOnChip.getChip().getId().isEmpty()) {
                    statement.setString(2, sensingElementOnChip.getChip().getId());
                } else {
                    statement.setNull(2, Types.VARCHAR);
                }

                if (!sensingElementOnChip.getChip().getFamilyName().isEmpty()) {
                    statement.setString(1, sensingElementOnChip.getChip().getFamilyName());
                } else {
                    statement.setNull(1, Types.VARCHAR);
                }

                statement.execute();
            }


            /*long idFamily = 0l;

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idFamily=(generatedKeys.getLong(1));
                }
                else {
                    throw new SQLException("Creating user failed, no ID obtained.");
                }
            }   */

            String sensingElementId = sensingElementOnChip.getIdSensingElement();

            for (Calibration temp : calibrations) {


               // String portName = temp.getName();
                String sqlIdCalibrationSelect = "SELECT idSPCalibration FROM SPCalibration WHERE name = '" + temp.getName() + "';";
                Statement statement3 = connection.prepareStatement(sqlIdCalibrationSelect);

                ResultSet resultSet2 = statement3.executeQuery(sqlIdCalibrationSelect);

                int idCalibration = 0;

                while (resultSet2.next()) {

                    idCalibration = resultSet2.getInt("idSPCalibration");
                }

                int n = temp.getN();
                int m = temp.getM();

                statement1.setString(1, sensingElementOnChip.getChip().getId();
                statement1.setInt(2, m);
                statement1.setInt(3, n);
                statement1.setString(4, sensingElementId);
                statement1.setInt(5, idCalibration);


                ResultSet resultSet = statement1.executeQuery();

            }


            connection.close();

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
     * Updates the sensingElement whith the same id of the sensingElement passed in.
     *
     * @param family The sensingElement to be updated.
     */
    @Override
    public void update(Family family) {

        try {

            List<Port> portList = family.getPorts();
            List<String> measureTypeList = family.getMeasureType();

            Connection connection = ConnectionFactory.getConnection();
            String sqlUpdateSPFamily = "UPDATE SPFamily SET " +
                    "id = ?, hwVersion = ?, sysclock = ?, osctrim = ?" +
                    " WHERE name = ? ;";

            String sqlIdFamilySelect = "SELECT idSPFamily FROM SPFamily WHERE name = '" + family.getName() + "';";

            String sqlIdSPPOrtSelect = "SELECT idSPPort FROM SPPort WHERE name = ?;";

            String sqlIdSPMeasureTypeSelect = "SELECT idSPMeasureType FROM SPMeasureTechniques WHERE type = ?;";

            String sqlDeleSPPort = "DELETE FROM SPFamilyTemplate WHERE SPFamily_idSPFamily = ?;";

            String sqlDeleteSPMeasureType = "DELETE FROM SPFamily_has_SPMeasureType WHERE SPFamily_idSPFamily = ?;";

            String sqlDeleteSPSensingElementOnFamily = "DELETE FROM SPSensingElementOnFamily WHERE " +
                    "name = '" + family.getName() + "';";

            String sqlSPFamilyTemplateInsert = "INSERT INTO SPFamilyTemplate (" +
                    "SPFamily_idSPFamily, " +
                    "SPPort_idSPPort)" +
                    " VALUES (?, ?)";

            String sqlSPFamilyHasSPMeasureTypeInsert = "INSERT INTO SPFamily_has_SPMeasureType (" +
                    "SPFamily_idSPFamily, " +
                    "SPMeasureType_idSPMeasureType)" +
                    "VALUES (?, ?)";

            String sqlSPSensingElementOnFamilyInsert =
                    "INSERT INTO SPSensingElementOnFamily (" +
                            "SPSensingElement_idSPSensingElement, " +
                            "SPFamilyTemplate_idSPFamilyTemplate, " +
                            "name)" +
                            " VALUES (?, ?, ?)";



            PreparedStatement statement = connection.prepareStatement(sqlUpdateSPFamily, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement3 = connection.prepareStatement(sqlIdSPPOrtSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement4 = connection.prepareStatement(sqlIdSPMeasureTypeSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement5 = connection.prepareStatement(sqlIdFamilySelect);
            PreparedStatement statement6 = connection.prepareStatement(sqlDeleSPPort);
            PreparedStatement statement7 = connection.prepareStatement(sqlDeleteSPMeasureType);
            PreparedStatement statement1 = connection.prepareStatement(sqlSPFamilyTemplateInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement2 = connection.prepareStatement(sqlSPFamilyHasSPMeasureTypeInsert);
            PreparedStatement statement8 = connection.prepareStatement(sqlDeleteSPSensingElementOnFamily);
            PreparedStatement statement9 = connection.prepareStatement(sqlSPSensingElementOnFamilyInsert);



            if (!family.getName().isEmpty()) {
                statement.setString(5, family.getName());
            } else {
                statement.setNull(5, Types.VARCHAR);
            }

            if (!family.getId().isEmpty()){
                statement.setString(1, family.getId());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }

            if (!family.getHwVersion().isEmpty()) {
                statement.setString(2, family.getHwVersion());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }

            if (!family.getSysclock().isEmpty()) {
                statement.setString(3, family.getSysclock());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }

            if (!family.getOsctrim().isEmpty()) {
                statement.setString(4, family.getOsctrim());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }


            int idSPFamily = 0;

            ResultSet resultSet3 = statement5.executeQuery();

            while(resultSet3.next()){
                idSPFamily = resultSet3.getInt("idSPFamily");
            }

            statement8.executeUpdate();
            statement6.setInt(1, idSPFamily);

            statement6.executeUpdate();


            statement7.setInt(1, idSPFamily);

            statement7.executeUpdate();




            int idPort = 0;

            for (Port temp : portList) {

                String namePort = temp.getName();
                String idSensingElement = temp.getIdSensingElement();

                statement3.setString(1, namePort);

                ResultSet resultSet = statement3.executeQuery();


                while (resultSet.next()) {
                    idPort = resultSet.getInt("idSPPort");
                }

                statement1.setInt(1, idSPFamily);
                statement1.setInt(2, idPort);

                long idFamilyTemplate = 0l;

                int affectedRows = statement1.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statement1.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idFamilyTemplate=(generatedKeys.getLong(1));
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

                if (idSensingElement != "" && idSensingElement != null) {
                    statement9.setString(1, idSensingElement);
                    statement9.setInt(2, (int) idFamilyTemplate);
                    statement9.setString(3, family.getName());

                    statement9.execute();
                }



            }

            int idMeasureType = 0;

            for (String temp : measureTypeList) {


                statement4.setString(1, temp);
                ResultSet resultSet1 = statement4.executeQuery();


                while (resultSet1.next()) {
                    idMeasureType = resultSet1.getInt("idSPMeasureType");
                }

                statement2.setInt(1, (int) idSPFamily);
                statement2.setInt(2, idMeasureType);

                statement2.execute();


            }


            statement.close();
            statement1.close();
            statement2.close();
            statement3.close();
            statement4.close();
            statement5.close();
            statement6.close();
            statement7.close();
            statement9.close();

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}



