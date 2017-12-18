package it.unicas.project.dao;

import it.unicas.project.model.*;
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
     *
     * @return
     */

    /*public static ClusterDAO getInstance() {
        if (uniqueInstanceOfClusterDAO == null) {
            uniqueInstanceOfClusterDAO = new ClusterDAO();
            return uniqueInstanceOfClusterDAO;
        } else {
            return uniqueInstanceOfClusterDAO;
        }
    }*/

    /**
     * Inserts in the db a sensingElement passed in.
     *
     * @param cluster The sensingElement to be added.
     */

    @Override
    public void create(Cluster cluster) {

        try {
            Connection connection = ConnectionFactory.getConnection();

            List<ChipWithCalibration> chipWithCalibrations = cluster.getChipWithCalibrations();

            String sqlSPSensingElementOnChipInsert = "INSERT INTO SPSensingElementOnChip (" +
                    "SPChip_idSPChip, " +
                    "m, " +
                    "n, " +
                    "SPSensingElementOnFamily_idSPSensingElementOnFamily, " +
                    "SPCluster_idSPCluster)" +
                    " VALUES (?, ?, ?, ?, ?)";


            String sqlIdSensingElementOnFamilySelect = "SELECT idSPSensingElementOnFamily FROM SPSensingElementOnFamily" +
                    " WHERE SPSensingElement_idSPSensingElement = ?;";


            String sqlSPClusterInsert = "INSERT INTO SPCluster (idSPCluster) VALUES (?);";

            PreparedStatement statement1 = connection.prepareStatement(sqlSPSensingElementOnChipInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement4 = connection.prepareStatement(sqlIdSensingElementOnFamilySelect);
            PreparedStatement statement = connection.prepareStatement(sqlSPClusterInsert);

            statement.setString(1, cluster.getId());
            statement.execute();




            for (ChipWithCalibration temp : chipWithCalibrations) {

                List<SensingElementWithCalibration> sensingElementWithCalibrations = temp.getSensingElementWithCalibrations();

                for (SensingElementWithCalibration temp1 : sensingElementWithCalibrations) {

                    statement1.setString(5, cluster.getId());
                    int n = temp1.getN();
                    int m = temp1.getM();

                    statement1.setString(1, temp.getChip().getId());
                    statement1.setInt(2, m);
                    statement1.setInt(3, n);

                    if (!temp1.getIdSensingElement().isEmpty()) {
                        statement4.setString(1, temp1.getIdSensingElement());
                        ResultSet resultSet4 = statement4.executeQuery();
                        int idSensingElementOnFamily = 0;

                        while (resultSet4.next()) {
                            idSensingElementOnFamily = resultSet4.getInt("idSPSensingElementOnFamily");
                        }
                        statement1.setInt(4, idSensingElementOnFamily);
                    }

                    if (!temp1.getIdSensingElement().isEmpty() && !temp.getChip().getId().isEmpty()) {
                        statement1.execute();
                    }
                }

                }




            connection.close();
            statement.close();
            statement1.close();
            statement4.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    /**
     * Deletes from the db the sensingElement passed in.
     *
     * @param cluster The sensingElement to be deleted.
     */

    @Override
    public void delete(Cluster cluster) {

        try {
            Connection connection = ConnectionFactory.getConnection();


            String sqlSPChipDelete = "DELETE FROM SPCluster WHERE idSPCluster ='" + cluster.getId() + "';";


            Statement statement1 = connection.prepareStatement(sqlSPChipDelete);
            statement1.executeUpdate(sqlSPChipDelete);


            statement1.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates the sensingElementOnChip and the chips whith the same id of the sensingElementOnChip passed in.
     *
     * @param cluster The sensingElement to be updated.
     */

    @Override
    public void update(Cluster cluster) {

        try {

            List<ChipWithCalibration> chipWithCalibrations = cluster.getChipWithCalibrations();

            Connection connection = ConnectionFactory.getConnection();


            String sqlDeleteSPSensingElementOnChip = "DELETE FROM SPSensingElementOnChip WHERE " +
                    "SPCluster_idSPCluster = '" + cluster.getId() + "';";


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


            Statement statement1 = connection.prepareStatement(sqlDeleteSPSensingElementOnChip);
            PreparedStatement statement2 = connection.prepareStatement(sqlSPSensingElementOnChipInsert);
            PreparedStatement statement4 = connection.prepareStatement(sqlIdSensingElementOnFamilySelect);



            statement1.executeUpdate(sqlDeleteSPSensingElementOnChip);

            for (ChipWithCalibration temp : chipWithCalibrations) {


                List<SensingElementWithCalibration> sensingElementWithCalibrations = temp.getSensingElementWithCalibrations();

                for (SensingElementWithCalibration temp1 : sensingElementWithCalibrations) {


                    statement2.setString(5, cluster.getId());

                    int n = temp1.getN();
                    int m = temp1.getM();

                    statement2.setString(1, temp.getChip().getId());
                    statement2.setInt(2, m);
                    statement2.setInt(3, n);

                    if (!temp1.getIdSensingElement().isEmpty()) {
                        statement4.setString(1, temp1.getIdSensingElement());
                        ResultSet resultSet4 = statement4.executeQuery();
                        int idSensingElementOnFamily = 0;

                        while (resultSet4.next()) {
                            idSensingElementOnFamily = resultSet4.getInt("idSPSensingElementOnFamily");
                        }
                        statement2.setInt(4, idSensingElementOnFamily);
                    }


                    if (!temp1.getIdSensingElement().isEmpty() && !temp.getChip().getId().isEmpty()) {
                        statement2.execute();
                    }
                }

            }


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
     *
     * @return All the sensingElements.
     */

    @Override
    public List<Cluster> fetchAll() {

        List<Cluster> chips = new ArrayList<>();

        /*try {
            Connection connection = ConnectionFactory.getConnection();

            String sqlSPClusterSelect = "SELECT * FROM SPCluster";
            String sqlSPChipOnClusterSelect = "SELECT (SPChip_idSPChip)" +
                    " FROM SPSensingElementOnChip WHERE SPCluster_idSPCluster = ?";
            String sqlSPSensingElementOnChipSelect = "SELECT (SPSensingElementOnFamily_idSPSensingElementOnFamily)" +
                    " FROM SPSensingElementOnChip WHERE SPCluster_idSPCluster = ? join SPChip_idSPChip = ?;";
            String sqlSPCalibrationsSelect = "SELECT SPChip_idSPChip, n, m FROM SPSensingElementOnChip WHERE SPCluster_idSPCluster = ?";
            String sqlSPSensingElementIDSelect = "SELECT SPSensingElement_idSPSensingElement FROM SPSensingElementOnFamily WHERE idSPSensingElementOnFamily = ?";
            String sqlSPCalibrationNameSelect = "SELECT name FROM SPCalibration WHERE idSPCalibration = ?";
            String sqlSPFamilyNameSelect = "SELECT name FROM SPFamily WHERE idSPFamily = ?";


            Statement statement = connection.prepareStatement(sqlSPClusterSelect);
            ResultSet resultSet = statement.executeQuery(sqlSPClusterSelect);

            PreparedStatement statement1 = connection.prepareStatement(sqlSPChipOnClusterSelect);
            PreparedStatement statement2 = connection.prepareStatement(sqlSPSensingElementIDSelect);
            PreparedStatement statement3 = connection.prepareStatement(sqlSPCalibrationNameSelect);
            PreparedStatement statement4 = connection.prepareStatement(sqlSPFamilyNameSelect);
            PreparedStatement statement5 = connection.prepareStatement(sqlSPCalibrationsSelect);


            while (resultSet.next()) {
                String clusterId = resultSet.getString("idSPCluster");
                /*int familyId = resultSet.getInt("SPFamily_idSPFamily");

                statement4.setInt(1, familyId);

                ResultSet rsFamily = statement4.executeQuery();

                String familyName = "";

                while (rsFamily.next()) {

                    familyName = rsFamily.getString("name");*/

               /* statement1.setString(1, clusterId);

                ResultSet rsChOnCl = statement1.executeQuery();

                List<ChipWithCalibration> chipWithCalibrations = new ArrayList<>();


                int n = 0, m = 0, chipId = 0, calibrationId = 0;

                while (rsChOnCl.next()) {

                    chipId = rsChOnCl.getInt("SPChip_idSPChip");

                    statement2.setInt(1, chipId);

                    ResultSet rsSensingElement = statement2.executeQuery();

                    String sensingElementId = "";

                    while (rsSensingElement.next()) {
                        sensingElementId = rsSensingElement.getString("SPSensingElement_idSPSensingElement");
                    }

                    List

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
            }


                Chip chip = new Chip(familyName, chipId, sensingElementWithCalibrations);


                chips.add(chip);

            statement.close();
            statement1.close();
            statement3.close();
            statement4.close();
            statement5.close();
            statement2.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }*/

        return chips;
    }

}
