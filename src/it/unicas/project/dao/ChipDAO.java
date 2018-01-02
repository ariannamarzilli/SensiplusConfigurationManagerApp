package it.unicas.project.dao;

import it.unicas.project.model.*;
import it.unicas.project.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChipDAO implements CrudDAO<Chip> {

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
     * @param chip The sensingElement to be added.
     */
    @Override
    public void create(Chip chip) {

        try {
            Connection connection = ConnectionFactory.getConnection();

            String sqlSPChipInsert = "INSERT INTO SPChip (idSPChip, SPFamily_idSPFamily) VALUES (?, ?)";
            String sqlSPFamilyIDSelect = "SELECT idSPFamily FROM SPFamily WHERE name = ?;";



            PreparedStatement statement = connection.prepareStatement(sqlSPChipInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement5 = connection.prepareStatement(sqlSPFamilyIDSelect);


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


            connection.close();
            statement.close();

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



            Statement statement = connection.prepareStatement(sqlSPChipDelete);
            statement.executeUpdate(sqlSPChipDelete);



            statement.close();

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

            Connection connection = ConnectionFactory.getConnection();

            String sqlUpdateSPChip = "UPDATE SPChip SET " +
                    "SPFamily_idSPFamily = ?" +
                    " WHERE idSPChip = '" + chip.getId() + "';";

            String sqlSPFamilyIDSelect = "SELECT idSPFamily FROM SPFamily WHERE name = ?;";




            PreparedStatement statement = connection.prepareStatement(sqlUpdateSPChip);
            PreparedStatement statement5 = connection.prepareStatement(sqlSPFamilyIDSelect);

            if (!chip.getFamilyName().isEmpty()) {
                statement5.setString(1, chip.getFamilyName());
                ResultSet rs = statement5.executeQuery();
                while (rs.next()) {
                    statement.setInt(1, rs.getInt("idSPFamily"));
                }

            } else {
                statement.setNull(1, Types.VARCHAR);
            }


            statement.execute();



            statement.close();
            statement5.close();

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
            String sqlSPFamilyNameSelect = "SELECT name FROM SPFamily WHERE idSPFamily = ?";




            Statement statement = connection.prepareStatement(sqlSPChipSelect);
            ResultSet resultSet = statement.executeQuery(sqlSPChipSelect);


            PreparedStatement statement4 = connection.prepareStatement(sqlSPFamilyNameSelect);



            while (resultSet.next()) {
                String chipId = resultSet.getString("idSPChip");
                int familyId = resultSet.getInt("SPFamily_idSPFamily");

                statement4.setInt(1, familyId);

                ResultSet rsFamily = statement4.executeQuery();

                String familyName = "";

                while(rsFamily.next()){

                    familyName = rsFamily.getString("name");

                }



                Chip chip = new Chip(familyName, chipId);


                chips.add(chip);
            }
            statement.close();

            statement4.close();


            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return chips;
    }

    public ChipWithCalibration fetchCalibration (Chip chip){

        List<SensingElementWithCalibration> sensingElementWithCalibrations = new ArrayList<>();

        ChipWithCalibration chipWithCalibration = new ChipWithCalibration();

        try {
            Connection connection = ConnectionFactory.getConnection();

            String sqlSPSensOnChipSelect = "SELECT n, m, SPSensingElementOnFamily_idSPSensingElementOnFamily FROM SPSensingElementOnChip WHERE SPChip_idSPChip = '"+ chip.getId() + "';";

            String sqlSelectIdSensingElement = "SELECT SPSensingElement_idSPSensingElement FROM SPSensingElementOnFamily " +
                    "WHERE idSPSensingElementOnFamily = ?";




            Statement statement = connection.prepareStatement(sqlSPSensOnChipSelect);
            ResultSet resultSet = statement.executeQuery(sqlSPSensOnChipSelect);

            PreparedStatement statement1 = connection.prepareStatement(sqlSelectIdSensingElement);

            int n=0, m=0, idSensElOnFamily =0;


            while (resultSet.next()){

                n = resultSet.getInt(1);
                m = resultSet.getInt(2);
                idSensElOnFamily = resultSet.getInt(3);

                statement1.setInt(1, idSensElOnFamily);

                ResultSet resultSet1 = statement1.executeQuery();

                String idSensingElement = "";

                while (resultSet1.next()){

                    idSensingElement = resultSet1.getString(1);
                }

                sensingElementWithCalibrations.add(new SensingElementWithCalibration(idSensingElement, n, m));


            }


            chipWithCalibration.setChip(chip);
            chipWithCalibration.setSensingElementWithCalibrations(sensingElementWithCalibrations);





            statement.close();
            statement1.close();




            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return chipWithCalibration;
    }





}



