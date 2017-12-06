package it.unicas.project.dao;

import it.unicas.project.model.Family;
import it.unicas.project.util.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FamilyDAO implements CrudDAO<Family> {

    private static FamilyDAO uniqueInstanceOfFamilyDAO = null;

    /**
     * Returns the unique instance of sensingElementDAO.
     * @return
     */
    public static FamilyDAO getInstance() {
        if (uniqueInstanceOfFamilyDAO == null) {
            uniqueInstanceOfFamilyDAO = new FamilyDAO();
            return uniqueInstanceOfFamilyDAO;
        } else {
            return uniqueInstanceOfFamilyDAO;
        }
    }


    /**
     * Inserts in the db a sensingElement passed in.
     * @param family The sensingElement to be added.
     */
    @Override
    public void create(Family family) {

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sqlSPFamilyInsert = "INSERT INTO SPFamily (name, id, hwVersion, sysclock, osctrim) VALUES (?, ?, ?, ?, ?)";


            List<String> portList = family.getPortName();
            String sqlIdSPPOrtSelect = "SELECT idSPPort FROM SPPort WHERE name = ?;";

            String sqlSPFamilyTemplateInsert = "INSERT INTO SPFamilyTemplate (" +
                    "SPFamily_idSPFamily, " +
                    "SPPort_idSPPort)" +
                    " VALUES (?, ?)";

            List<String> measureTypeList = family.getMeasureType();

            String sqlIdSPMeasureTypeSelect = "SELECT idSPMeasureType FROM SPMeasureType WHERE type = ?;";

            String sqlSPFamilyHasSPMeasureTypeInsert = "INSERT INTO SPFamily_has_SPMeasureType (" +
                    "SPFamily_idSPFamily, " +
                    "SPMeasureType_idSPMeasureType)" +
                    "VALUES (?, ?)";



            PreparedStatement statement = connection.prepareStatement(sqlSPFamilyInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement1 = connection.prepareStatement(sqlIdSPPOrtSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement2 = connection.prepareStatement(sqlSPFamilyTemplateInsert);
            PreparedStatement statement3 = connection.prepareStatement(sqlIdSPMeasureTypeSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement4 = connection.prepareStatement(sqlSPFamilyHasSPMeasureTypeInsert);



            if (!family.getId().isEmpty()) {
                statement.setString(2, family.getId());
            } else {
                statement.setNull(2, Types.VARCHAR);
            }

            if (!family.getName().isEmpty()) {
                statement.setString(1, family.getName());
            } else {
                statement.setNull(1, Types.VARCHAR);
            }

            if (!family.getHwVersion().isEmpty()) {
                statement.setString(3, family.getHwVersion());
            } else {
                statement.setNull(3, Types.VARCHAR);
            }

            if (!family.getSysclock().isEmpty()) {
                statement.setString(4, family.getSysclock());
            } else {
                statement.setNull(4, Types.VARCHAR);
            }

            if (!family.getOsctrim().isEmpty()) {
                statement.setString(5, family.getOsctrim());
            } else {
                statement.setNull(5, Types.VARCHAR);
            }

            long idFamily = 0l;

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
            }




            int idPort = 0;

            for (String temp : portList) {

                statement1.setString(1, temp);

                ResultSet resultSet = statement1.executeQuery();


                while (resultSet.next()) {
                    idPort = resultSet.getInt("idSPPort");
                }

                statement2.setInt(1, (int) idFamily);
                statement2.setInt(2, idPort);

                statement2.execute();
            }

            int idMeasureType = 0;

            for (String temp : measureTypeList) {


                statement3.setString(1, temp);
                ResultSet resultSet1 = statement3.executeQuery();


                while (resultSet1.next()) {
                    idMeasureType = resultSet1.getInt("idSPMeasureType");
                }

                statement4.setInt(1, (int) idFamily);
                statement4.setInt(2, idMeasureType);

                statement4.execute();


            }
            statement2.close();
            statement1.close();
            statement3.close();
            statement4.close();

            statement.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Deletes from the db the sensingElement passed in.
     * @param family The sensingElement to be deleted.
     */
    @Override
    public void delete(Family family) {

        try {
            Connection connection = ConnectionFactory.getConnection();
            //si eliminano in base al nome e non in base all'id in quanto l'utente non conosce l'id della family che
            //in realtà è autoincrementale

            String sqlSPFamilyId = "SELECT idSPFamily FROM SPFamily WHERE name ='" + family.getName() + "';";
            Statement statement1 = connection.prepareStatement(sqlSPFamilyId, Statement.RETURN_GENERATED_KEYS);

            ResultSet resultSet = statement1.executeQuery(sqlSPFamilyId);

            int idSPFamily = 0;


            while (resultSet.next()){
                idSPFamily = resultSet.getInt("idSPFamily");
            }



            String sqlSPFamilyDelete = "DELETE FROM SPFamily WHERE name ='" + family.getName() + "';";
            String sqlSPFamilyTemplateDelete = "DELETE FROM SPFamilyTemplate WHERE SPFamily_idSPFamily = '" + idSPFamily + "';";
            String sqlSPFamilyHasMeasureTypeDelete = "DELETE FROM SPFamily_has_SPMeasureType WHERE SPFamily_idSPFamily = '" + idSPFamily + "';";

            Statement statement2 = connection.prepareStatement(sqlSPFamilyTemplateDelete);
            statement2.executeUpdate(sqlSPFamilyTemplateDelete);

            Statement statement3 = connection.prepareStatement(sqlSPFamilyHasMeasureTypeDelete);
            statement3.executeUpdate(sqlSPFamilyHasMeasureTypeDelete);


            Statement statement = connection.prepareStatement(sqlSPFamilyDelete);
            statement.executeUpdate(sqlSPFamilyDelete);





            statement.close();
            statement1.close();
            statement2.close();
            statement3.close();

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

            List<String> portList = family.getPortName();
            List<String> measureTypeList = family.getMeasureType();

            Connection connection = ConnectionFactory.getConnection();
            String sqlUpdateSPFamily = "UPDATE SPFamily SET " +
                    "id = ?, hwVersion = ?, sysclock = ?, osctrim = ?" +
                    " WHERE name = ? ;";

            String sqlIdFamilySelect = "SELECT idSPFamily FROM SPFamily WHERE name = '" + family.getName() + "';";

            String sqlIdSPPOrtSelect = "SELECT idSPPort FROM SPPort WHERE name = ?;";

            String sqlIdSPMeasureTypeSelect = "SELECT idSPMeasureType FROM SPMeasureType WHERE type = ?;";

            String sqlDeleSPPort = "DELETE FROM SPFamilyTemplate WHERE SPFamily_idSPFamily = ?;";

            String sqlDeleteSPMeasureType = "DELETE FROM SPFamily_has_SPMeasureType WHERE SPFamily_idSPFamily = ?;";

            String sqlSPFamilyTemplateInsert = "INSERT INTO SPFamilyTemplate (" +
                    "SPFamily_idSPFamily, " +
                    "SPPort_idSPPort)" +
                    " VALUES (?, ?)";

            String sqlSPFamilyHasSPMeasureTypeInsert = "INSERT INTO SPFamily_has_SPMeasureType (" +
                    "SPFamily_idSPFamily, " +
                    "SPMeasureType_idSPMeasureType)" +
                    "VALUES (?, ?)";

            PreparedStatement statement = connection.prepareStatement(sqlUpdateSPFamily, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement3 = connection.prepareStatement(sqlIdSPPOrtSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement4 = connection.prepareStatement(sqlIdSPMeasureTypeSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement5 = connection.prepareStatement(sqlIdFamilySelect);
            PreparedStatement statement6 = connection.prepareStatement(sqlDeleSPPort);
            PreparedStatement statement7 = connection.prepareStatement(sqlDeleteSPMeasureType);
            PreparedStatement statement1 = connection.prepareStatement(sqlSPFamilyTemplateInsert);
            PreparedStatement statement2 = connection.prepareStatement(sqlSPFamilyHasSPMeasureTypeInsert);



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


            statement6.setInt(1, idSPFamily);

            statement6.executeUpdate();

            statement7.setInt(1, idSPFamily);

            statement7.executeUpdate();



            int idPort = 0;

            for (String temp : portList) {

                statement3.setString(1, temp);

                ResultSet resultSet = statement3.executeQuery();


                while (resultSet.next()) {
                    idPort = resultSet.getInt("idSPPort");
                }

                statement1.setInt(1, idSPFamily);
                statement1.setInt(2, idPort);

                statement1.execute();
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
    public Iterable<Family> fetchAll() {

        List<Family> families = new ArrayList<>();

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sqlSPFamilySelect = "SELECT * FROM SPFamily";
            String sqlSPFamilyTemplate = "SELECT SPPort_idSPPort FROM SPFamilyTemplate WHERE SPFamily_idSPFamily = ?";
            String sqlSPPort = "SELECT name FROM SPPort WHERE idSPPort = ?";
            String sqlSPFamilyHasSPMeasureType = "SELECT SPMeasureType_idSPMeasureType " +
                    "FROM SPFamily_has_SPMeasureType WHERE SPFamily_idSPFamily = ?";
            String sqlSPMeasureType = "SELECT type FROM SPMeasureType WHERE idSPMeasureType = ?";

            Statement statement = connection.prepareStatement(sqlSPFamilySelect);
            ResultSet resultSet = statement.executeQuery(sqlSPFamilySelect);

            PreparedStatement statement1 = connection.prepareStatement(sqlSPFamilyTemplate);
            PreparedStatement statement2 = connection.prepareStatement(sqlSPPort);
            PreparedStatement statement3 = connection.prepareStatement(sqlSPFamilyHasSPMeasureType);
            PreparedStatement statement4 = connection.prepareStatement(sqlSPMeasureType);

            int idPort=0, idMeasureType=0;
            List<Integer> portList = new ArrayList<Integer>();
            List<Integer> measureTypeList = new ArrayList<Integer>();


            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String hwVersion = resultSet.getString("hwVersion");
                String sysclock = resultSet.getString("sysclock");
                String osctrim = resultSet.getString("osctrim");

                int idFamily = resultSet.getInt("idSPFamily");

                statement1.setInt(1, idFamily);
                ResultSet resultSetPortId = statement1.executeQuery();

                List<String> portNameList = new ArrayList<String>();
                List<String> measureTypeNameList = new ArrayList<String>();


                while (resultSetPortId.next()){
                    portList.add(resultSetPortId.getInt("SPPort_idSPPort"));

                    statement2.setInt(1, resultSetPortId.getInt("SPPort_idSPPort"));

                    ResultSet resultSetPortName = statement2.executeQuery();
                    while (resultSetPortName.next()){
                        portNameList.add(resultSetPortName.getString("name"));
                    }
                }

                statement3.setInt(1, idFamily);
                ResultSet resultSetMeasureTypeId = statement3.executeQuery();


                while (resultSetMeasureTypeId.next()){

                    statement4.setInt(1, resultSetMeasureTypeId.getInt("SPMeasureType_idSPMeasureType"));
                    ResultSet resultSetMeasureTypeName = statement4.executeQuery();
                    while (resultSetMeasureTypeName.next()){
                        measureTypeNameList.add(resultSetMeasureTypeName.getString("type"));
                    }
                }



               families.add(new Family(id, name, hwVersion, sysclock, osctrim, portNameList, measureTypeNameList));
            }
            statement.close();
            statement1.close();
            statement2.close();
            statement3.close();
            statement4.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return families;
    }


}
