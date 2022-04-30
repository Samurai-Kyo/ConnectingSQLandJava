package dao;

import Entities.Story;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoryDao {

    private Connection connection;
    private PersonDao personDao;
    private final String GET_TEAMS_QUERY = "SELECT * FROM story";
    private final String GET_STORY_BY_ID_QUERY = "Select * from story where id = ?";
    private final String CREATE_NEW_STORY_QUERY = "insert into story(name) values (?)";
    private final String DELETE_STORY = "delete from story where id = ?";

    public StoryDao() {
        connection = DBConnection.getConnection();
        personDao = new PersonDao();
    }

    public List<Story> getStoies() throws SQLException {
        ResultSet rs = connection.prepareStatement(GET_TEAMS_QUERY).executeQuery();
        List<Story> stories = new ArrayList<Story>();

        while (rs.next()) {
            stories.add(populateStory(rs.getInt(1), rs.getString(2)));
        }
        return stories;
    }

    public Story getStoryById(int id) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(GET_STORY_BY_ID_QUERY);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return populateStory(rs.getInt(1), rs.getString(2));
    }

    public void createNewStory(String storyName) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(CREATE_NEW_STORY_QUERY);
        ps.setString(1, storyName);
        ps.executeUpdate();
    }

    public void deleteTeamById(int id) throws SQLException {
        personDao.deletePeopleByStoryId(id);
        PreparedStatement ps = connection.prepareStatement(DELETE_STORY);
        ps.setInt(1,id);
        ps.executeUpdate();
    }

    private Story populateStory(int id, String title) throws SQLException {
        return new Story(id, title, personDao.getPeoplebyStoryId(id));
    }

}