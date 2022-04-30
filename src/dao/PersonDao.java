package dao;

import Entities.Person;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

    private Connection connection;
    private final String GET_PEOPLE_BY_STORY_ID_QUERY = "Select * from person where story_id = ?";
    private final String DELETE_PEOPLE_BY_STORY_ID_QUERY = "delete from person where story_id = ?";
    private final String CREAT_NEW_PERSON_QUERY = "insert into person(first_name, last_name, story_id) values (?,?,?)";
    private final String DELETE_PERSON_BY_ID = "delete from person where id = ?";

    public PersonDao() {
        connection = DBConnection.getConnection();
    }

    public List<Person> getPeoplebyStoryId(int story_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_PEOPLE_BY_STORY_ID_QUERY);
        ps.setInt(1, story_id);
        ResultSet rs = ps.executeQuery();
        List<Person> people = new ArrayList<Person>();

        while (rs.next()) {
            people.add(new Person(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }
        return people;
    }

    public void deletePeopleByStoryId(int story_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_PEOPLE_BY_STORY_ID_QUERY);
        ps.setInt(1, story_id);
        ps.executeUpdate();
    }

    public void createNewPerson(String first_name, String last_name, int story_id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(CREAT_NEW_PERSON_QUERY);
        ps.setString(1,first_name);
        ps.setString(2,last_name);
        ps.setInt(3,story_id);
        ps.executeUpdate();
    }

    public void deletePersonById (int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(DELETE_PERSON_BY_ID);
        ps.setInt(1,id);
        ps.executeUpdate();
    }
}
