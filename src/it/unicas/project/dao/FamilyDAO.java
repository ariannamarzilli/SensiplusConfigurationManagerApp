package it.unicas.project.dao;

import it.unicas.project.model.Family;
import it.unicas.project.model.Port;
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

            //family object has a list of ports,

            List<Port> portList = family.getPorts();

            String sqlIdSPPOrtSelect = "SELECT idSPPort FROM SPPort WHERE name = ?;";

            String sqlSPFamilyTemplateInsert = "INSERT INTO SPFamilyTemplate (" +
                    "SPFamily_idSPFamily, " +
                    "SPPort_idSPPort)" +
                    " VALUES (?, ?)";

            String sqlSPSensingElementOnFamilyInsert =
                    "INSERT INTO SPSensingElementOnFamily (" +
                            "SPSensingElement_idSPSensingElement, " +
                            "SPFamilyTemplate_idSPFamilyTemplate, " +
                            "name)" +
                            " VALUES (?, ?, ?)";

            List<String> measureTypeList = family.getMeasureType();

            String sqlIdSPMeasureTypeSelect = "SELECT idSPMeasureType FROM SPMeasureTechniques WHERE type = ?;";

            String sqlSPFamilyHasSPMeasureTypeInsert = "INSERT INTO SPFamily_has_SPMeasureType (" +
                    "SPFamily_idSPFamily, " +
                    "SPMeasureType_idSPMeasureType)" +
                    "VALUES (?, ?)";


            PreparedStatement statement = connection.prepareStatement(sqlSPFamilyInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement1 = connection.prepareStatement(sqlIdSPPOrtSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement2 = connection.prepareStatement(sqlSPFamilyTemplateInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement3 = connection.prepareStatement(sqlIdSPMeasureTypeSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement4 = connection.prepareStatement(sqlSPFamilyHasSPMeasureTypeInsert);
            PreparedStatement statement5 = connection.prepareStatement(sqlSPSensingElementOnFamilyInsert);




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

            for (Port temp : portList) {


                String sensingElementId = temp.getIdSensingElement();

                String portName = temp.getName();

                statement1.setString(1, portName);

                ResultSet resultSet = statement1.executeQuery();


                while (resultSet.next()) {
                    idPort = resultSet.getInt("idSPPort");
                }

                statement2.setInt(1, (int) idFamily);
                statement2.setInt(2, idPort);


                long idFamilyTemplate = 0l;

                affectedRows = statement2.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statement2.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idFamilyTemplate=(generatedKeys.getLong(1));
                    }
                    else {
                        throw new SQLException("Creating user failed, no ID obtained.");
                    }
                }

                if(!sensingElementId.equals("") && sensingElementId != null) {

                    statement5.setString(1, sensingElementId);
                    statement5.setInt(2, (int) idFamilyTemplate);
                    statement5.setString(3, family.getName());

                    statement5.execute();
                }


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
            statement5.close();

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


            String sqlSPFamilyDelete = "DELETE FROM SPFamily WHERE name ='" + family.getName() + "';";


            Statement statement = connection.prepareStatement(sqlSPFamilyDelete);
            statement.executeUpdate(sqlSPFamilyDelete);





            statement.close();
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

            List<Port> newPorts = family.getPorts();
            List<Port> oldPorts = new ArrayList<Port>();
            List<String> oldMeasureType = new ArrayList<>();
            List<String> newMeasureType = family.getMeasureType();

            // le righe da sensingelement on family e da family template vanno eliminate solo se la porta in questione viene deselezionata

            Connection connection = ConnectionFactory.getConnection();
            String sqlUpdateSPFamily = "UPDATE SPFamily SET " +
                    "id = ?, hwVersion = ?, sysclock = ?, osctrim = ?" +
                    " WHERE name = ? ;";

            String sqlIdFamilySelect = "SELECT idSPFamily FROM SPFamily WHERE name = '" + family.getName() + "';";

            String sqlIdSPPOrtSelect = "SELECT idSPPort FROM SPPort WHERE name = ?;";

            String sqlIdSPMeasureTypeSelect = "SELECT idSPMeasureType FROM SPMeasureTechniques WHERE type = ?;";

            String sqlDeleSPPort = "DELETE FROM SPFamilyTemplate WHERE SPFamily_idSPFamily = ?;";

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


            String sqlPort = "SELECT (SELECT name FROM SPPort WHERE idSPPort = SPPort_idSPPort), "+
                    "SPSensingElement_idSPSensingElement, (SELECT name FROM SPSensingElement WHERE "+
                    "idSPSensingElement = SPSensingElement_idSPSensingElement)  FROM SPFamily join SPFamilyTemplate on "+
                    "(idSPFamily = SPFamily_idSPFamily) join SPSensingElementOnFamily on (idSPFamilyTemplate = SPFamilyTemplate_idSPFamilyTemplate)" +
                    " WHERE idSPFamily = ?";

            PreparedStatement statement = connection.prepareStatement(sqlUpdateSPFamily, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement3 = connection.prepareStatement(sqlIdSPPOrtSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement4 = connection.prepareStatement(sqlIdSPMeasureTypeSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement5 = connection.prepareStatement(sqlIdFamilySelect);
            PreparedStatement statement6 = connection.prepareStatement(sqlDeleSPPort);
            PreparedStatement statement1 = connection.prepareStatement(sqlSPFamilyTemplateInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement2 = connection.prepareStatement(sqlSPFamilyHasSPMeasureTypeInsert);
            PreparedStatement statement9 = connection.prepareStatement(sqlSPSensingElementOnFamilyInsert);
            PreparedStatement statement7 = connection.prepareStatement(sqlPort);



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

            statement.execute();


            int idSPFamily = 0;

            ResultSet resultSet3 = statement5.executeQuery();

            while(resultSet3.next()){
                idSPFamily = resultSet3.getInt("idSPFamily");
            }

            //qui vengono fatti i delete dalle tabelle intermedie



            statement7.setInt(1, idSPFamily);
            ResultSet rs = statement7.executeQuery();

            String portName = "", sensId = "", sensName = "";

            while (rs.next()){
                portName = rs.getString(1);
                sensId = rs.getString(2);
                sensName = rs.getString(3);

                oldPorts.add(new Port(portName, sensId, sensName));
            }




            int idPort = 0;

            for (Port temp : newPorts) {

                if (!(oldPorts.contains(temp))) {



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
                            idFamilyTemplate = (generatedKeys.getLong(1));
                        } else {
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

            }




            int idMeasureType = 0;

            for (String temp : newMeasureType) {


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
            statement9.close();


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
    public List<Family> fetchAll() {

        List<Family> families = new ArrayList<>();

        try {
            Connection connection = ConnectionFactory.getConnection();
            String sqlSPFamilySelect = "SELECT * FROM SPFamily";
            String sqlSPFamilyHasSPMeasureType = "SELECT SPMeasureType_idSPMeasureType " +
                    "FROM SPFamily_has_SPMeasureType WHERE SPFamily_idSPFamily = ?";
            String sqlSPMeasureType = "SELECT type FROM SPMeasureTechniques WHERE idSPMeasureType = ?";
            String sqlPort = "SELECT (SELECT name FROM SPPort WHERE idSPPort = SPPort_idSPPort), "+
                    "SPSensingElement_idSPSensingElement, (SELECT name FROM SPSensingElement WHERE "+
                    "idSPSensingElement = SPSensingElement_idSPSensingElement)  FROM SPFamily join SPFamilyTemplate on "+
                    "(idSPFamily = SPFamily_idSPFamily) join SPSensingElementOnFamily on (idSPFamilyTemplate = SPFamilyTemplate_idSPFamilyTemplate)" +
                    " WHERE idSPFamily = ?";


            Statement statement = connection.prepareStatement(sqlSPFamilySelect);
            ResultSet resultSet = statement.executeQuery(sqlSPFamilySelect);

            PreparedStatement statement3 = connection.prepareStatement(sqlSPFamilyHasSPMeasureType);
            PreparedStatement statement4 = connection.prepareStatement(sqlSPMeasureType);
            PreparedStatement statement1 = connection.prepareStatement(sqlPort);




            while (resultSet.next()) {
                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String hwVersion = resultSet.getString("hwVersion");
                String sysclock = resultSet.getString("sysclock");
                String osctrim = resultSet.getString("osctrim");

                int idFamily = resultSet.getInt("idSPFamily");


                List<Port> ports = new ArrayList<Port>();
                List<String> measureTypeNameList = new ArrayList<String>();

                statement1.setInt(1, idFamily);


                ResultSet rsPort = statement1.executeQuery();

                String portName = "";
                String idSensingElement = "";
                String nameSensingElement = "";

                while(rsPort.next()){

                    portName = rsPort.getString(1);
                    idSensingElement = rsPort.getString(2);
                    nameSensingElement = rsPort.getString(3);

                    ports.add(new Port (portName, idSensingElement, nameSensingElement));
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



               families.add(new Family(id, name, hwVersion, sysclock, osctrim, ports, measureTypeNameList));
            }
            statement.close();
            statement1.close();
            statement3.close();
            statement4.close();

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return families;
    }


}
