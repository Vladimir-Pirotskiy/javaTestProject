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


-Find all contacts with Name starts with “A” in organization
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
