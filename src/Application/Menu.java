package Application;

import Entities.Person;
import Entities.Story;
import dao.PersonDao;
import dao.StoryDao;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private StoryDao storyDao = new StoryDao();
    private PersonDao personDao = new PersonDao();
    private Scanner scanner = new Scanner(System.in);
    private List<String> options = Arrays.asList(
            "Display Stories",
            "Display Story Characters",
            "Create a Story",
            "Delete a Story",
            "Create a Person",
            "Delete a Person");


    public void start() {
        String selection = "";

        do {
            printMenu();
            selection = scanner.nextLine();
            try {
                if (selection.equals("1")) {
                    displayStories();
                } else if (selection.equals("2")) {
                    displayStory();
                } else if (selection.equals("3")) {
                createStory();
                } else if (selection.equals("4")) {
                deleteStory();
                } else if (selection.equals("5")) {
                createPerson();
                } else if (selection.equals("6")) {
                deletePerson();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            System.out.println("Press enter to continue...");
            scanner.nextLine();
        }
        while (!selection.equals("-1"));
    }

    private void deletePerson() throws SQLException {
        System.out.print("Enter id of person :");
        int id = Integer.parseInt(scanner.nextLine());
        personDao.deletePersonById(id);
    }

    private void createPerson() throws SQLException {
        System.out.print("Enter first name of new person :");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name of new person :");
        String lastName = scanner.nextLine();
        System.out.print("Enter story id of new person :");
        int id = Integer.parseInt(scanner.nextLine());
        personDao.createNewPerson(firstName,lastName,id);
    }

    private void deleteStory() throws SQLException {
        System.out.print("Enter story id to delete:");
        int id = Integer.parseInt(scanner.nextLine());
        storyDao.deleteTeamById(id);
    }

    private void createStory() throws SQLException {
        System.out.print("Enter new story name: ");
        String storyName = scanner.nextLine();
        storyDao.createNewStory(storyName);
    }

    private void displayStory() throws SQLException {
        System.out.println("Enter story Id: ");
        int id = Integer.parseInt(scanner.nextLine());
        Story story = storyDao.getStoryById(id);
        System.out.println(story.getId() + ": " + story.getTitle());
        for (Person person : story.getCast()) {
            System.out.println("\tCharacter Id: " + person.getId() + " - Name: " + person.getFirst_name() + " " + person.getLast_name());
        }
    }

    private void printMenu() {
        System.out.println("Select an option:\n------------------------");
        int x = options.size();
        for (int i = 0; i < x; i++) {
            System.out.println((i + 1 + ") ") + options.get(i));
        }
    }

    private void displayStories() throws SQLException {
        List<Story> stories = storyDao.getStoies();
        for (Story story : stories) {
            System.out.println(story.getId() + ": " + story.getTitle());
        }
    }

}
