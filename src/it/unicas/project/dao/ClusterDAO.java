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

    private static ClusterDAO uniqueInstanceOfClusterDAO = null;

    /**
     * Returns the unique instance of clusterDAO.
     *
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

        List<Cluster> clusters = new ArrayList<>();

        try {
            Connection connection = ConnectionFactory.getConnection();


            List<ChipWithCalibration> chipWithCalibrations = new ArrayList<>();

            String sqlSPClusterSelect = "SELECT * FROM SPCluster";

            String sqlSPChipOnClusterSelect = "SELECT (SPChip_idSPChip)" +
                    " FROM SPSensingElementOnChip WHERE SPCluster_idSPCluster = ?";

            String sqlSPSensingElementOnChipSelect = "SELECT (SPSensingElementOnFamily_idSPSensingElementOnFamily, m, n)" +
                    " FROM SPSensingElementOnChip WHERE SPCluster_idSPCluster = ? and SPChip_idSPChip = ?;";


            String sqlPortSelect = "select (select name from SPPort where idSPPort = SPPort_idSPPort) from SPSensingElementOnFamily join " +
                    "SPFamilyTemplate on (idSPFamilyTemplate = SPFamilyTemplate_idSPFamilyTemplate) " +
                    "where SPSensingElement_idSPSensingElement = ? and name = ?;";

            String sqlFamilySelect = "select SPFamily_idSPFamily, (select name from SPFamily where idSPFamily = SPFamily_idSPFamily) from SPChip where idSPChip = ?;";



            Statement statement = connection.prepareStatement(sqlSPClusterSelect);
            ResultSet resultSet = statement.executeQuery(sqlSPClusterSelect);

            PreparedStatement statement1 = connection.prepareStatement(sqlSPChipOnClusterSelect);
            PreparedStatement statement6 = connection.prepareStatement(sqlSPSensingElementOnChipSelect);
            PreparedStatement statement7 = connection.prepareStatement(sqlPortSelect);
            PreparedStatement statement8 = connection.prepareStatement(sqlFamilySelect);


            while (resultSet.next()) {
                String clusterId = resultSet.getString("idSPCluster");

                statement1.setString(1, clusterId);

                ResultSet rsChipOnCluster = statement1.executeQuery();
                String idChip = "", nameFamily = "";
                int m = 0;
                int n = 0;
                int idFamily = 0;

                while (rsChipOnCluster.next()){

                    idChip = rsChipOnCluster.getString(1);

                    statement8.setString(1, idChip);

                    ResultSet rsFamilyID = statement8.executeQuery();

                    while (rsFamilyID.next()){

                        idFamily = rsFamilyID.getInt(1);
                        nameFamily = rsFamilyID.getString(2);

                    }

                    statement6.setString(1, clusterId);
                    statement6.setString(2, idChip);

                    ResultSet rsSensingElementOnChip = statement6.executeQuery();
                    String idSensingElement = "", portName = "";

                    List<SensingElementWithCalibration> sensingElementWithCalibrations = new ArrayList<>();

                    while (rsSensingElementOnChip.next()){
                        idSensingElement = rsSensingElementOnChip.getString(1);
                        m = rsSensingElementOnChip.getInt(2);
                        n = rsSensingElementOnChip.getInt(3);

                        statement7.setString(1, idSensingElement);
                        statement7.setInt(2, idFamily);

                        ResultSet rsPortName = statement7.executeQuery();

                        while (rsPortName.next()){
                            portName = rsPortName.getString(1);
                        }

                        sensingElementWithCalibrations.add(new SensingElementWithCalibration(idSensingElement, m, n, portName));

                    }

                    Chip chip = new Chip(nameFamily, idChip);

                    chipWithCalibrations.add(new ChipWithCalibration(chip, sensingElementWithCalibrations));

                }

                clusters.add(new Cluster(clusterId,chipWithCalibrations));
            }

            statement.close();
            statement1.close();
            statement6.close();
            statement7.close();
            statement8.close();

            connection.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clusters;
    }

}
