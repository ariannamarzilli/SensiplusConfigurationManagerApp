package it.unicas.project.dao;

import it.unicas.project.model.Analyte;
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

            String sqlSPAnalyte_SPSensingElementInsert = "INSERT INTO SPAnalyte_SPSensingElementOnFamily " +
                    "(idSPAnalyte_SPSensingElementOnFamily, SPAnalyte_idSPAnalyte) " +
                    "VALUES (?, ?)";

            String sqlIdAnalyte = "SELECT idSPAnalyte FROM SPAnalyte WHERE name = ?";


            PreparedStatement statement = connection.prepareStatement(sqlSPFamilyInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement1 = connection.prepareStatement(sqlIdSPPOrtSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement2 = connection.prepareStatement(sqlSPFamilyTemplateInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement3 = connection.prepareStatement(sqlIdSPMeasureTypeSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement4 = connection.prepareStatement(sqlSPFamilyHasSPMeasureTypeInsert);
            PreparedStatement statement5 = connection.prepareStatement(sqlSPSensingElementOnFamilyInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statement6 = connection.prepareStatement(sqlSPAnalyte_SPSensingElementInsert);
            PreparedStatement statement7 = connection.prepareStatement(sqlIdAnalyte);




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
                List<Analyte> analytes = temp.getAnalytes();

                String portName = temp.getName();

                statement1.setString(1, portName);

                ResultSet resultSet = statement1.executeQuery();


                while (resultSet.next()) {
                    idPort = resultSet.getInt("idSPPort");
                }

                statement2.setInt(1, (int) idFamily);
                statement2.setInt(2, idPort);


                long idFamilyTemplate = 0l;

                long idSensingElementOnFamily = 0l;

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

                    statement5.executeUpdate();

                    try(ResultSet generatedKeys = statement5.getGeneratedKeys()){
                        if (generatedKeys.next()){
                            idSensingElementOnFamily = (generatedKeys.getLong(1));
                        }
                    }

                    if (!analytes.isEmpty()){

                        for (Analyte temp2 : analytes){
                            statement7.setString(1, temp2.getName());
                            ResultSet rsIdAnalyte = statement7.executeQuery();

                            int idAnalyte = 0;

                            while (rsIdAnalyte.next()){
                                idAnalyte = rsIdAnalyte.getInt(1);
                            }

                            statement6.setInt(1, (int) idSensingElementOnFamily);
                            statement6.setInt(2, idAnalyte);
                            statement6.executeUpdate();
                        }
                    }
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
            statement6.close();
            statement7.close();

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

            String sqlDeleSPPort = "DELETE FROM SPFamilyTemplate WHERE SPPort_idSPPort = ? and SPFamily_idSPFamily = ?;";

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


            String sqlSPAnalyte_SPSensingElementInsert = "INSERT INTO SPAnalyte_SPSensingElementOnFamily " +
                    "(idSPAnalyte_SPSensingElementOnFamily, SPAnalyte_idSPAnalyte) " +
                    "VALUES (?, ?)";

            String sqlIdAnalyte = "SELECT idSPAnalyte FROM SPAnalyte WHERE name = ?";

            String sqlNamesAnalyteSelect = "select name from SPAnalyte join SPAnalyte_SPSensingElementOnFamily on " +
                    "(idSPAnalyte = SPAnalyte_idSPAnalyte) where idSPAnalyte_SPSensingElementOnFamily = ?";



            String sqlPort = "SELECT (SELECT name FROM SPPort WHERE idSPPort = SPPort_idSPPort) "+
                    "FROM SPFamily join SPFamilyTemplate on "+
                    "(idSPFamily = SPFamily_idSPFamily)" +
                    " WHERE idSPFamily = ?";

            //Questa query seleziona idSensingElement, nameSensingElement e idSensingElementOnfamily relativi
            //a tutti i sensing element montati sulle porte di una famiglia specifica
            String sqlSensingElement = "SELECT SPSensingElement_idSPSensingElement, " +
                    "(SELECT name FROM SPSensingElement WHERE idSPSensingElement = SPSensingElement_idSPSensingElement)," +
                    " idSPSensingElementOnFamily" +
                    " FROM SPFamily join SPFamilyTemplate on (SPFamily_idSPFamily = idSPFamily) join SPSensingElementOnFamily on (idSPFamilyTemplate = SPFamilyTemplate_idSPFamilyTemplate)" +
                    " WHERE SPFamily_idSPFamily = ?;";

            String sqlDeleteMeasureType = "DELETE FROM SPFamily_has_SPMeasureType WHERE SPFamily_idSPFamily = ?";

            PreparedStatement statementUpdateSPFamily = connection.prepareStatement(sqlUpdateSPFamily, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statementIdPortSelect = connection.prepareStatement(sqlIdSPPOrtSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statementIdMeasureTypeSelect = connection.prepareStatement(sqlIdSPMeasureTypeSelect, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statementIdFamilySelect = connection.prepareStatement(sqlIdFamilySelect);
            PreparedStatement statementDeleteSPPort = connection.prepareStatement(sqlDeleSPPort);
            PreparedStatement statementFamilyTemplateInsert = connection.prepareStatement(sqlSPFamilyTemplateInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statementFamilyHasMeasureTypeInsert = connection.prepareStatement(sqlSPFamilyHasSPMeasureTypeInsert);
            PreparedStatement statementSensingElementOnFamilyInsert = connection.prepareStatement(sqlSPSensingElementOnFamilyInsert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement statementPortSelect = connection.prepareStatement(sqlPort);
            PreparedStatement statementSensingElementSelect = connection.prepareStatement(sqlSensingElement);
            PreparedStatement statementDeleteMeasureType = connection.prepareStatement(sqlDeleteMeasureType);
            PreparedStatement statementAnalyte_SPSensingElementInsert = connection.prepareStatement(sqlSPAnalyte_SPSensingElementInsert);
            PreparedStatement statementIdAnalyteSelect = connection.prepareStatement(sqlIdAnalyte);
            PreparedStatement statementNamesAnalyteSelect = connection.prepareStatement(sqlNamesAnalyteSelect);



            if (!family.getName().isEmpty()) {
                statementUpdateSPFamily.setString(5, family.getName());
            } else {
                statementUpdateSPFamily.setNull(5, Types.VARCHAR);
            }

            if (!family.getId().isEmpty()){
                statementUpdateSPFamily.setString(1, family.getId());
            } else {
                statementUpdateSPFamily.setNull(1, Types.VARCHAR);
            }

            if (!family.getHwVersion().isEmpty()) {
                statementUpdateSPFamily.setString(2, family.getHwVersion());
            } else {
                statementUpdateSPFamily.setNull(2, Types.VARCHAR);
            }

            if (!family.getSysclock().isEmpty()) {
                statementUpdateSPFamily.setString(3, family.getSysclock());
            } else {
                statementUpdateSPFamily.setNull(3, Types.VARCHAR);
            }

            if (!family.getOsctrim().isEmpty()) {
                statementUpdateSPFamily.setString(4, family.getOsctrim());
            } else {
                statementUpdateSPFamily.setNull(4, Types.VARCHAR);
            }

            statementUpdateSPFamily.execute();


            int idSPFamily = 0;

            ResultSet resultSet3 = statementIdFamilySelect.executeQuery();

            while(resultSet3.next()){
                idSPFamily = resultSet3.getInt("idSPFamily");
            }

            //qui vengono fatti i delete dalle tabelle intermedie



            statementPortSelect.setInt(1, idSPFamily);
            ResultSet rs = statementPortSelect.executeQuery();

            String portName = "", sensId = "", sensName = "";
            int idSensingElementOnFamily = 0;

            while (rs.next()){
                portName = rs.getString(1);

                statementSensingElementSelect.setInt(1, idSPFamily);

                ResultSet rsSensingElement = statementSensingElementSelect.executeQuery();

                while(rsSensingElement.next()){
                    sensId = rsSensingElement.getString(1);
                    sensName = rsSensingElement.getString(2);
                    idSensingElementOnFamily = rsSensingElement.getInt(3);

                    statementNamesAnalyteSelect.setInt(1, idSensingElementOnFamily);

                    ResultSet rsNamesAnalyte = statementNamesAnalyteSelect.executeQuery();

                    String nameAnalyte = "";
                    List<Analyte> oldAnalites = new ArrayList<>();

                    while (rsNamesAnalyte.next()){
                        nameAnalyte = rsNamesAnalyte.getString(1);

                        Analyte analyte = new Analyte(nameAnalyte);
                        oldAnalites.add(analyte);
                    }


                    oldPorts.add(new Port(portName, sensId, sensName, oldAnalites));
                }



            }




            int idPort = 0;

            for (Port temp : oldPorts) {
                if (!(newPorts.contains(temp))) {

                    String namePort = temp.getName();

                    statementIdPortSelect.setString(1, namePort);

                    ResultSet resultSet = statementIdPortSelect.executeQuery();


                    while (resultSet.next()) {
                        idPort = resultSet.getInt("idSPPort");
                    }

                    statementDeleteSPPort.setInt(1, idPort);
                    statementDeleteSPPort.setInt(2, idSPFamily);
                    statementDeleteSPPort.execute();

                }
            }

            for (Port temp : newPorts) {

                if (!(oldPorts.contains(temp))) {



                    String namePort = temp.getName();
                    String idSensingElement = temp.getIdSensingElement();
                    List<Analyte> analytes = temp.getAnalytes();

                    statementIdPortSelect.setString(1, namePort);

                    ResultSet resultSet = statementIdPortSelect.executeQuery();


                    while (resultSet.next()) {
                        idPort = resultSet.getInt("idSPPort");
                    }



                    statementFamilyTemplateInsert.setInt(1, idSPFamily);
                    statementFamilyTemplateInsert.setInt(2, idPort);

                    long idFamilyTemplate = 0l;
                    long idSensingElementOnFamily2 = 0l;

                    int affectedRows = statementFamilyTemplateInsert.executeUpdate();

                    if (affectedRows == 0) {
                        throw new SQLException("Creating user failed, no rows affected.");
                    }

                    try (ResultSet generatedKeys = statementFamilyTemplateInsert.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            idFamilyTemplate = (generatedKeys.getLong(1));
                        } else {
                            throw new SQLException("Creating user failed, no ID obtained.");
                        }
                    }

                    if (!idSensingElement.equals("") && idSensingElement != null) {
                        statementSensingElementOnFamilyInsert.setString(1, idSensingElement);
                        statementSensingElementOnFamilyInsert.setInt(2, (int) idFamilyTemplate);
                        statementSensingElementOnFamilyInsert.setString(3, family.getName());

                        statementSensingElementOnFamilyInsert.executeUpdate();

                        try(ResultSet generatedKeys = statementSensingElementOnFamilyInsert.getGeneratedKeys()){
                            if (generatedKeys.next()){
                                idSensingElementOnFamily2 = (generatedKeys.getLong(1));
                            }
                        }

                        if (!analytes.isEmpty()){

                            for (Analyte temp2 : analytes){
                                statementIdAnalyteSelect.setString(1, temp2.getName());
                                ResultSet rsIdAnalyte = statementIdAnalyteSelect.executeQuery();

                                int idAnalyte = 0;

                                while (rsIdAnalyte.next()){
                                    idAnalyte = rsIdAnalyte.getInt(1);
                                }

                                statementAnalyte_SPSensingElementInsert.setInt(1, (int) idSensingElementOnFamily2);
                                statementAnalyte_SPSensingElementInsert.setInt(2, idAnalyte);
                                statementAnalyte_SPSensingElementInsert.executeUpdate();
                            }
                        }



                    }

                }

            }

            int idMeasureType = 0;
            statementDeleteMeasureType.setInt(1, idSPFamily);
            statementDeleteMeasureType.executeUpdate();

            for (String temp : newMeasureType) {


                statementIdMeasureTypeSelect.setString(1, temp);
                ResultSet resultSet1 = statementIdMeasureTypeSelect.executeQuery();


                while (resultSet1.next()) {
                    idMeasureType = resultSet1.getInt("idSPMeasureType");
                }


                statementFamilyHasMeasureTypeInsert.setInt(1, (int) idSPFamily);
                statementFamilyHasMeasureTypeInsert.setInt(2, idMeasureType);

                statementFamilyHasMeasureTypeInsert.execute();
            }




            statementUpdateSPFamily.close();
            statementFamilyTemplateInsert.close();
            statementFamilyHasMeasureTypeInsert.close();
            statementIdPortSelect.close();
            statementIdMeasureTypeSelect.close();
            statementIdFamilySelect.close();
            statementDeleteSPPort.close();
            statementSensingElementOnFamilyInsert.close();
            statementPortSelect.close();
            statementSensingElementSelect.close();
            statementDeleteMeasureType.close();
            statementAnalyte_SPSensingElementInsert.close();
            statementIdAnalyteSelect.close();

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
            String sqlPort = "SELECT (SELECT name FROM SPPort WHERE idSPPort = SPPort_idSPPort) "+
                     "FROM SPFamily join SPFamilyTemplate on "+
                    "(idSPFamily = SPFamily_idSPFamily)" +
                    " WHERE idSPFamily = ?";
            String sqlSensingElement = "SELECT SPSensingElement_idSPSensingElement, " +
                    "(SELECT name FROM SPSensingElement WHERE idSPSensingElement = SPSensingElement_idSPSensingElement)," +
                    " idSPSensingElementOnFamily" +
                    " FROM SPFamily join SPFamilyTemplate on (SPFamily_idSPFamily = idSPFamily) join SPSensingElementOnFamily on (idSPFamilyTemplate = SPFamilyTemplate_idSPFamilyTemplate)" +
                    " WHERE SPFamily_idSPFamily = ? and SPPort_idSPPort = ?;";

            String sqlIdPort = "SELECT idSPPort FROM SPPort WHERE name = ?";

            String sqlSPAnalyteSPSeOnFamilySelect = "SELECT SPAnalyte_idSPAnalyte FROM SPAnalyte_SPSensingElementOnFamily " +
                    "WHERE idSPAnalyte_SPSensingElementOnFamily = ?";

            String sqlAnalyteNameSelect = "SELECT name FROM SPAnalyte WHERE idSPAnalyte = ?";


            Statement statement = connection.prepareStatement(sqlSPFamilySelect);
            ResultSet resultSet = statement.executeQuery(sqlSPFamilySelect);

            PreparedStatement statement3 = connection.prepareStatement(sqlSPFamilyHasSPMeasureType);
            PreparedStatement statement4 = connection.prepareStatement(sqlSPMeasureType);
            PreparedStatement statement1 = connection.prepareStatement(sqlPort);
            PreparedStatement statement2 = connection.prepareStatement(sqlSensingElement);
            PreparedStatement statement5 = connection.prepareStatement(sqlIdPort);
            PreparedStatement statement6 = connection.prepareStatement(sqlSPAnalyteSPSeOnFamilySelect);
            PreparedStatement statement7 = connection.prepareStatement(sqlAnalyteNameSelect);

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
                int idSensingElementOnFamily = 0;

                while(rsPort.next()){

                    portName = rsPort.getString(1);

                    statement2.setInt(1, idFamily);

                    statement5.setString(1, portName);
                    ResultSet rsIdPort = statement5.executeQuery();
                    int idPort = 0;


                    while (rsIdPort.next()){

                        idPort = rsIdPort.getInt(1);
                        statement2.setInt(2, idPort);
                    }

                    ResultSet rsSensingElement = statement2.executeQuery();

                    while(rsSensingElement.next()){
                        idSensingElement = rsSensingElement.getString(1);
                        nameSensingElement = rsSensingElement.getString(2);
                        idSensingElementOnFamily = rsSensingElement.getInt(3);
                    }

                    statement6.setInt(1, idSensingElementOnFamily);

                    ResultSet rsIdAnalyte = statement6.executeQuery();

                    List<Analyte> analytes = new ArrayList<>();
                    int idAnalyte = 0;
                    String analyteName = "";

                    while (rsIdAnalyte.next()){
                        idAnalyte = rsIdAnalyte.getInt(1);

                        statement7.setInt(1, idAnalyte);

                        ResultSet rsAnalyteName = statement7.executeQuery();

                        while (rsAnalyteName.next()){
                            analyteName = rsAnalyteName.getString(1);
                        }

                        Analyte analyte = new Analyte(analyteName);

                        analytes.add(analyte);
                    }



                    ports.add(new Port (portName, idSensingElement, nameSensingElement, analytes));
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



    public List<Analyte> fetchAllAnalyte() {

        List<Analyte> analytes = new ArrayList<>();

        try{
            Connection connection = ConnectionFactory.getConnection();

            String sqlAnalyteselect = "SELECT * FROM SPAnalyte";

            Statement statement = connection.prepareStatement(sqlAnalyteselect);

            ResultSet rsAnalyte = statement.executeQuery(sqlAnalyteselect);

            String name = "";

            while (rsAnalyte.next()){

                name = rsAnalyte.getString("name");

                Analyte analyte = new Analyte(name);

                analytes.add(analyte);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return analytes;
    }
}
