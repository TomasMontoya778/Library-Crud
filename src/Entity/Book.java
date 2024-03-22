package Entity;

public class Book extends Author{

    private int idBook;
    private String title;
    private String year;
    private double price;

    private Author author;
    public Book (){

    }

    public Book(int idAuthor, String name, String nacionality, int idBook, String title, String year, double price, Author author) {
        super(idAuthor, name, nacionality);
        this.idBook = idBook;
        this.title = title;
        this.year = year;
        this.price = price;
        this.author = author;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "\n------------Book------------" +
                "\nidBook: " + idBook +
                "\ntitle: " + title+
                "\nyear: " + year+
                "\nprice: "+ price;
    }
}
