package it.unicas.project.dao;

import it.unicas.project.model.Calibration;
import it.unicas.project.model.Chip;
import it.unicas.project.model.SensingElement;
import it.unicas.project.model.SensingElementOnChip;
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
            int n = sensingElementOnChip.getN();
            int m = sensingElementOnChip.getM();

            for (Calibration temp : calibrations) {


               // String portName = temp.getName();
                String sqlIdCalibrationSelect = "SELECT idSPCalibration FROM SPCalibration WHERE name = '" + temp.getName() + "';";
                Statement statement3 = connection.prepareStatement(sqlIdCalibrationSelect);

                ResultSet resultSet2 = statement3.executeQuery(sqlIdCalibrationSelect);

                int idCalibration = 0;

                while (resultSet2.next()) {

                    idCalibration = resultSet2.getInt("idSPCalibration");
                }

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

            //
            String sqlSPSensingElementOnFamilyDelete = "DELETE FROM SPSensingElementOnChip WHERE  = '" + sensingElementOnChip.getChip().getId() + "';";

            /*Statement statement2 = connection.prepareStatement(sqlSPFamilyTemplateDelete);
            statement2.executeUpdate(sqlSPFamilyTemplateDelete);

            Statement statement5 = connection.prepareStatement(sqlSPSensingElementOnFamilyDelete);
            statement5.executeUpdate(sqlSPSensingElementOnFamilyDelete);

            Statement statement3 = connection.prepareStatement(sqlSPFamilyHasMeasureTypeDelete);
            statement3.executeUpdate(sqlSPFamilyHasMeasureTypeDelete);
            //

            Statement statement = connection.prepareStatement(sqlSPFamilyDelete);
            statement.executeUpdate(sqlSPFamilyDelete);





            statement.close();
            statement1.close();
            statement2.close();
            statement3.close();
            statement4.close();
            statement5.close();*/

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}



