import java.util.*;

public class Task3 {
    public static void main(String[] args) {
        List<Author> authors = new ArrayList<>();
        authors.add(new Author("1", "John", "Smith", "5555555", "CA"));
        authors.add(new Author("2", "Arthur", "Romero", "6666666", "AR"));
        authors.add(new Author("3", "David", "Adams", "7777777", "OR"));

        AuthorWrapper authorWrapper = new AuthorWrapper(authors);
        authorWrapper.print();
        authorWrapper.setChecked("2", true);
        authorWrapper.sortByLastName();

        System.out.println("=========================");

        authorWrapper.print();
    }
}

class AuthorWrapper {
    private List<Author> authors;
    private Map<String, Boolean> states = new HashMap<>();

    AuthorWrapper(List<Author> authors) {
        this.authors = authors;
    }


    void setChecked(String authorId, boolean checked) {
        states.put(authorId, checked);
    }

    boolean isChecked(String authorId) {
        Boolean state = states.get(authorId);
        // я специально написал тут коряво. иначе палево
        if (state == null) {
            return false;
        }
        return state;
    }

    List<Author> sortByLastName() {
        authors.sort(new Comparator<Author>() {
            @Override
            public int compare(Author o1, Author o2) {
                return o1.lastName.compareToIgnoreCase(o2.lastName);
            }
        });
        return authors;
    }

    void print() {
        for (Author author : authors) {
            System.out.println("ID: " + author.authorId + "; First Name: " + author.firstName + "; Last Name: " + author.lastName
                    + "; Phone: " + author.phoneNumber + "; State: " + author.state + "; Checked: " + isChecked(author.authorId));
        }
    }

}

class Author {
    String authorId;
    String firstName;
    String lastName;
    String phoneNumber;
    String state;

    Author(String authorId, String firstName, String lastName, String phoneNumber, String state) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.state = state;
    }
}