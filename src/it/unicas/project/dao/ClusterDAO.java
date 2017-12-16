package it.unicas.project.dao;

<<<<<<< HEAD
import it.unicas.project.model.Calibration;
import it.unicas.project.model.Chip;
import it.unicas.project.model.Cluster;
import it.unicas.project.model.SensingElementWithCalibration;
import it.unicas.project.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClusterDAO implements CrudDAO<Cluster> {

    private static ChipDAO uniqueInstanceOfClusterDAO = null;

    /**
     * Returns the unique instance of clusterDAO.
     * @return
     */
    public static ClusterDAO getInstance() {
        if (uniqueInstanceOfClusterDAO == null) {
            uniqueInstanceOfClusterDAO = new ClusterDAO();
            return uniqueInstanceOfClusterDAO;
        } else {
            return uniqueInstanceOfClusterDAO;
        }
    }

    /**
     * Inserts in the db a sensingElement passed in.
     * @param chip The sensingElement to be added.
     */
    @Override
    public void create(Chip chip) {

        try {
            Connection connection = ConnectionFactory.getConnection();

            String sqlSPChipInsert = "INSERT INTO SPChip (idSPChip, SPFamily_idSPFamily) VALUES (?, ?)";

            String sqlSPChipSelect = "SELECT COUNT(idSPChip) FROM SPChip WHERE '" + chip.getId() + "';";


            List<SensingElementWithCalibration> sensingElementWithCalibrations = chip.getSensingElementWithCalibrations();

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

            String sqlSPFamilyIDSelect = "SELECT idSPFamily FROM SPFamily WHERE name = ?;";



            PreparedStatement statement = connection.prepareStatement(sqlSPChipInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement1 = connection.prepareStatement(sqlSPSensingElementOnChipInsert, Statement.RETURN_GENERATED_KEYS);
            Statement statement2 = connection.prepareStatement(sqlSPChipSelect);
            PreparedStatement statement4 = connection.prepareStatement(sqlIdSensingElementOnFamilySelect);
            PreparedStatement statement5 = connection.prepareStatement(sqlSPFamilyIDSelect);

            ResultSet resultSet1 = statement2.executeQuery(sqlSPChipSelect);

            while (resultSet1.next()) {

                int count = resultSet1.getInt(1);

                if (count == 0) {

                    if (!chip.getId().isEmpty()) {
                        statement.setString(1, chip.getId());
                    } else {
                        statement.setNull(1, Types.VARCHAR);
                    }

                    if (!chip.getFamilyName().isEmpty()) {
                        statement5.setString(1, chip.getFamilyName());
                        ResultSet rs = statement5.executeQuery();
                        while (rs.next()) {
                            statement.setInt(2, rs.getInt("idSPFamily"));
                        }

                    } else {
                        statement.setNull(2, Types.VARCHAR);
                    }

                    statement.execute();
                }
            }


            for (SensingElementWithCalibration temp : sensingElementWithCalibrations) {


                // String portName = temp.getName();
                List<Calibration> calibrations = temp.getCalibrationList();

                for (Calibration temp1 : calibrations) {
                    String sqlIdCalibrationSelect = "SELECT idSPCalibration FROM SPCalibration WHERE name = '" + temp1.getName() + "';";
                    Statement statement3 = connection.prepareStatement(sqlIdCalibrationSelect);
                    ResultSet resultSet2;
                    if (!temp1.getName().isEmpty()) {
                        resultSet2 = statement3.executeQuery(sqlIdCalibrationSelect);

                        int idCalibration = 0;

                        while (resultSet2.next()) {

                            idCalibration = resultSet2.getInt("idSPCalibration");
                        }
                        statement1.setInt(5, idCalibration);

                    }


                    int n = temp1.getN();
                    int m = temp1.getM();

                    statement1.setString(1, chip.getId());
                    statement1.setInt(2, m);
                    statement1.setInt(3, n);

                    if (!temp.getIdSensingElement().isEmpty()) {
                        statement4.setString(1, temp.getIdSensingElement());
                        ResultSet resultSet4 = statement4.executeQuery();
                        int idSensingElementOnFamily = 0;

                        while (resultSet4.next()) {
                            idSensingElementOnFamily = resultSet4.getInt("idSPSensingElementOnFamily");
                        }
                        statement1.setInt(4, idSensingElementOnFamily);
                    }


                    if (!temp.getIdSensingElement().isEmpty() && !temp1.getName().isEmpty()) {
                        statement1.execute();
                    }
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
     * @param chip The sensingElement to be deleted.
     */
    @Override
    public void delete(Chip chip) {

        try {
            Connection connection = ConnectionFactory.getConnection();



            String sqlSPChipDelete = "DELETE FROM SPChip WHERE idSPChip ='" + chip.getId() + "';";

            String sqlSPSensingElementOnFamilyDelete = "DELETE FROM SPSensingElementOnChip WHERE  SPChip_idSPChip= '" + chip.getId() + "';";

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
     * @param chip The sensingElement to be updated.
     */
    @Override
    public void update(Chip chip) {

        try {

            List<SensingElementWithCalibration> sensingElementWithCalibrations = chip.getSensingElementWithCalibrations();

            Connection connection = ConnectionFactory.getConnection();

            String sqlUpdateSPChip = "UPDATE SPChip SET " +
                    "SPFamily_idSPFamily = ?" +
                    " WHERE idSPChip = '" + chip.getId() + "';";


            String sqlDeleteSPSensingElementOnChip = "DELETE FROM SPSensingElementOnChip WHERE " +
                    "SPChip_idSPChip = '" + chip.getId() + "';";


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


            if (!chip.getFamilyName().isEmpty()){
                statement.setString(1, chip.getFamilyName());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }

            statement1.executeUpdate(sqlDeleteSPSensingElementOnChip);

            for (SensingElementWithCalibration temp : sensingElementWithCalibrations) {


                List<Calibration> calibrations = temp.getCalibrationList();
                for (Calibration temp1 : calibrations) {
                    String sqlIdCalibrationSelect = "SELECT idSPCalibration FROM SPCalibration WHERE name = '" + temp1.getName() + "';";
                    Statement statement3 = connection.prepareStatement(sqlIdCalibrationSelect);
                    ResultSet resultSet2;
                    if (!temp1.getName().isEmpty()) {
                        resultSet2 = statement3.executeQuery(sqlIdCalibrationSelect);

                        int idCalibration = 0;

                        while (resultSet2.next()) {

                            idCalibration = resultSet2.getInt("idSPCalibration");
                        }
                        statement2.setInt(5, idCalibration);

                    }


                    int n = temp1.getN();
                    int m = temp1.getM();

                    statement2.setString(1, chip.getId());
                    statement2.setInt(2, m);
                    statement2.setInt(3, n);

                    if (!temp.getIdSensingElement().isEmpty()) {
                        statement4.setString(1, temp.getIdSensingElement());
                        ResultSet resultSet4 = statement4.executeQuery();
                        int idSensingElementOnFamily = 0;

                        while (resultSet4.next()) {
                            idSensingElementOnFamily = resultSet4.getInt("idSPSensingElementOnFamily");
                        }
                        statement2.setInt(4, idSensingElementOnFamily);
                    }


                    if (!temp.getIdSensingElement().isEmpty() && !temp1.getName().isEmpty()) {
                        statement2.execute();
                    }
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
    public List<Chip> fetchAll() {

        List<Chip> chips = new ArrayList<>();

        try {
            Connection connection = ConnectionFactory.getConnection();

            String sqlSPChipSelect = "SELECT * FROM SPChip";
            String sqlSPSensingElementOnChipSelect = "SELECT (SPSensingElementOnFamily_idSPSensingElementOnFamily)" +
                    " FROM SPSensingElementOnChip WHERE SPChip_idSPChip = ?";
            String sqlSPCalibrationsSelect = "SELECT n, m, SPCalibration_idSPCalibration FROM SPSensingElementOnChip WHERE SPChip_idSPChip = ?";
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


                statement1.setString(1, chipId);

                ResultSet rsSEOnChip = statement1.executeQuery();

                List<SensingElementWithCalibration> sensingElementWithCalibrations = new ArrayList<>();


                int n = 0, m = 0, sensingElementOnFamilyId = 0, calibrationId = 0;

                while (rsSEOnChip.next()){

                    sensingElementOnFamilyId = rsSEOnChip.getInt("SPSensingElementOnFamily_idSPSensingElementOnFamily");

                    statement2.setInt(1, sensingElementOnFamilyId);

                    ResultSet rsSensingElement = statement2.executeQuery();

                    String sensingElementId = "";

                    while (rsSensingElement.next()){
                        sensingElementId = rsSensingElement.getString("SPSensingElement_idSPSensingElement");
                    }

                    statement5.setString(1, chipId);

                    List<Calibration> calibrations = new ArrayList<>();

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

                    SensingElementWithCalibration sensingElementWithCalibration = new SensingElementWithCalibration(calibrations, sensingElementId);

                    if (!(sensingElementWithCalibrations.contains(sensingElementWithCalibration))) {

                        sensingElementWithCalibrations.add(sensingElementWithCalibration);
                    }
                }


                Chip chip = new Chip(familyName, chipId, sensingElementWithCalibrations);


                chips.add(chip);
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

        return chips;
    }

=======
public class ClusterDAO {
>>>>>>> 1bba8cf022dd3829bba439921b8280859eabd3a6
}
