package Entities;

        import java.util.List;

public class Story {

    private int id;
    private String title;
    private List<Person> cast;

    public Story(int id, String title, List<Person> cast) {
        this.id = id;
        this.title = title;
        this.cast = cast;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Person> getCast() {
        return cast;
    }

    public void setCast(List<Person> cast) {
        this.cast = cast;
    }

}
