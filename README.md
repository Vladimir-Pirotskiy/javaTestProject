# petProjectJava

**1.** Your DB needs to hold two main objects – **Contacts** and
Organizations. Each Contact can work in several organizations
and each **Organization** has a lot of contacts.
- What type of relationship would you use?
- Draw the scheme of all tables with relationships.

1.Relationship Name: **Many-to-Many**.

2.Schema:


![](/images/Schema1.png)



As a part of project, you need to create controller for page on picture below.
Class Author already exists and you can’t change it, you just can use its
variables authorId, lastName,
firstName, phoneNumber, state.
Your future controller will use
wrapper class, with additional
variable to show if author instance
is checked or not on the page and
possibility to sort authors by Last
Name with calling one method.
Write Java code to implement this
wrapper class.

**2.** Now we have DB with each **Contact** has the only one related
**Organization**.
- How can you find all contacts with Name starts with “A” in
organization named “Cats”?
- How can you count number of contacts for each organization?
- Write SQL query for each question above.


![](/images/schema2.png)


- Find all contacts with Name starts with “A” in organization
named “Cats”:
To find all Name starts with “A” in organization called
“Cats” we need to join the contacts and organization tables in
the column that satisfy to the parameters.

```SQL
SELECT * FROM contacts
INNER JOIN organization ON
contacts.organization_id = organization.id
WHERE organization.name = "Cats"
AND contacts.name LIKE "A%"
```

- Count number of contacts for each organization:
To count all contacts, for each organization we need to
display organizations with contacts and display their contacts
and then count them.

```SQL 
SELECT organization.name, COUNT(*)
FROM organization LEFT JOIN
contacts ON organization.id = contacts.organization_id
GROUP BY organization.id
```

**3.** As a part of project, you need to create controller for page on
picture below. Class **Author** already exists and you can’t change
it, you just can use its
variables **authorId**,
**lastName**, **firstName**,
**phoneNumber**, **state**. Your
future controller will use
wrapper class, with
additional variable to show if
author instance is checked or
not on the page and
possibility to sort authors by
Last Name with calling one
method. Write Java code to
implement this wrapper
class.

![](/images/image.jpeg)

```Java
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
```

**4.** You have a list of numbers to fill the table of prices in the shown
way


|  |Jan   |Feb   |Mar   |Apr   |May   |Jun   |Jul   |Aug   |Sep   |Oct   |Nov   |Dec
| ------ | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | --- | ---
| Prod.1 |Num1|Num2|Num3|Num4|Num5|Num6|Num7|Num8|Num9|Num10|Num11|Num12
| Prod.2 |Num13|Num14|...||||||||
| Prod.3 |||||||||||



  - Implement Java method which receive this list and return the
object which will be convenient to find price using Month and
Product keys.

Answer:



```Java
import java.util.HashMap;
import java.util.Map;

public class Task4 {

    public static void main(String[] args) {
        double[] prices = {13.3, 45, 23, 45, 545, 3453, 23, 78, 4431, 4554, 55, 909, 234, 84, 34};
        PriceViewer priceViewer = new PriceViewer(prices);
        Double price = priceViewer.getPrice("Prod. 2", "Apr");
        System.out.println("Price: " + price);
    }
}

class PriceViewer {
    private static final String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};

    Map<String, Map<String, Double>> table = new HashMap<>();

    PriceViewer(double[] prices) {
        if (prices.length == 0) throw new IllegalArgumentException("prices array length must be > 0");

        int productCount = prices.length / 12 + (prices.length % 12 > 0 ? 1 : 0);

        for (int i = 0; i < productCount; i++) {
            Map<String, Double> productRow = new HashMap<>();
            for (int j = 0; j < months.length; j++) {
                int priceIndex = i * 12 + j;
                double price = priceIndex < prices.length ? prices[priceIndex] : 0;
                productRow.put(months[j], price);
            }
            table.put("Prod. " + (i + 1), productRow);
        }

    }

    Double getPrice(String productName, String monthName) {
        Map<String, Double> productRow = table.get(productName);
        if(productRow == null) throw new IllegalArgumentException("no product with name " + productName);
        return productRow.get(monthName);
    }
}
```


**5.** Describe what does the following code

```HTML
<!DOCTYPE HTML>
<html>
<head>
  <meta charset="utf-8">

  
  <script>
    function countRabbits() {
      for(var i=1; i<=3; i++) {
        alert("Rabbit number " + i);
      }
    }
  </script>
  
</head>
<body>
  <input type="button" onclick="countRabbits()" value="Count rabbits!"/>
</body>
</html>
```

  This code is an HTML page with javaScrip inside. The page
displays a button, after pushing the button page displays
warning (alert) with the rabbit counter function (countRabbits)
and print the Rabbit number. When the program enters the loop
for, it waits until the user push the button and then increments
the counter (i) by one, and then proceeds to the next iteration
until the counter will reach the condition.
