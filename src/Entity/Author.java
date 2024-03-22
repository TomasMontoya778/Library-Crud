package Entity;

public class Author {
    private int idAuthor;
    private String name;
    private String nacionality;

    public Author(){

    }

    public Author(int idAuthor, String name, String nacionality) {
        this.idAuthor = idAuthor;
        this.name = name;
        this.nacionality = nacionality;
    }

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNacionality() {
        return nacionality;
    }

    public void setNacionality(String nacionality) {
        this.nacionality = nacionality;
    }

    @Override
    public String toString() {
        return "\n-----------Author----------" +
                "\nidAuthor: " + idAuthor +
                "\nname: " + name +
                "\nnacionality: " + nacionality;
    }
}
