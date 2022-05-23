package edu.javacourse.studentorder.dao;
import edu.javacourse.studentorder.config.Config;
import edu.javacourse.studentorder.domain.PassportOffice;
import edu.javacourse.studentorder.domain.RegisterOffice;
import edu.javacourse.studentorder.domain.Street;
import edu.javacourse.studentorder.exception.DaoException;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

//класс для подключения и работы с Базой данных
public class DictionaryDaoImpl implements DictionaryDao{

    private static final String GET_STREET = "select street_code, street_name from jc_street where upper(street_name) like upper(?)";
    private static final String GET_PASSPORT = "SELECT * FROM jc_passport_office WHERE p_office_area_id =?";
    private static final String GET_REGISTER = "SELECT * FROM jc_register_office WHERE r_office_area_id =?";


    private Connection getConnection() throws SQLException {
//        теперь эту запись можно записать иначе
//        Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5433/jc_student","postgres","postgres");
        Connection con = DriverManager.getConnection(
                Config.getProperty(Config.DB_URL),
                Config.getProperty(Config.DB_LOGIN),
                Config.getProperty(Config.DB_PASSWORD));
        return con;
    }

    public List<Street> findStreets(String pattern) throws DaoException {
        List<Street> result = new LinkedList<>();
        try (Connection con = getConnection();
            PreparedStatement stmt = con.prepareStatement(GET_STREET)){
            stmt.setString(1,"%" + pattern +"%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Street str = new Street(rs.getLong("street_code"), rs.getString("street_name"));
                result.add(str);
            }
        } catch (SQLException ex){
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<PassportOffice> findPassportOffices(String areaId) throws DaoException {
        List<PassportOffice> result = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_PASSPORT)){
            stmt.setString(1,areaId);
            //получаем коллекцию знаений из нашей БД
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //для каждого значения в коллекции создаем объект ПасспортОфис и в его параметры записываем значения из
                // очереного элемента коллегции rs.
                PassportOffice passportOffice = new PassportOffice(
                        rs.getLong("p_office_id"),
                        rs.getString("p_office_area_id"),
                        rs.getString("p_office_name"));
//записываем получившийся объект РаспортОфис в списко объектов дя вдачи результата
                result.add(passportOffice);
            }
        } catch (SQLException ex){
            throw new DaoException(ex);
        }
        return result;
    }

    @Override
    public List<RegisterOffice> findRegisterOffices(String areaId) throws DaoException {
        List<RegisterOffice> result = new LinkedList<>();
        try (Connection con = getConnection();
             PreparedStatement stmt = con.prepareStatement(GET_REGISTER)){
            stmt.setString(1,areaId);
            //получаем коллекцию знаений из нашей БД
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                //для каждого значения в коллекции создаем объект РегистрОфис и в его параметры записываем значения из
                // очереного элемента коллегции rs.
                RegisterOffice registerOffice = new RegisterOffice(
                        rs.getLong("r_office_id"),
                        rs.getString("r_office_area_id"),
                        rs.getString("r_office_name"));
//записываем получившийся объект РаспортОфис в списко объектов дя вдачи результата
                result.add(registerOffice);
            }
        } catch (SQLException ex){
            throw new DaoException(ex);
        }
        return result;
    }
}
