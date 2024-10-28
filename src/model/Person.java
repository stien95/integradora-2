package model;

public class Person {
    // Atributtes
    protected String id;
    protected String name;
    protected String country;

    /**
     * Constructs a new Person object with the specified id, name, and country.
     *
     * @param id      the unique identifier for the person
     * @param name    the name of the person
     * @param country the country of the person
     */
    public Person(String id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    /**
     * Returns the name of the person.
     * <br/>
     * Post: The name of the person has been returned.
     * 
     * @return the name of the person
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the person.
     * <br/>
     * Post: The name of the person has been updated.
     * 
     * @param name the new name of the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the country of the person.
     * <br/>
     * Post: The country of the person has been returned.
     * 
     * @return the country of the person
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the country of the person.
     * <br/>
     * Post: The country of the person has been updated.
     * 
     * @param country the new country of the person
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /************* ✨ Codeium Command ⭐ *************/
    /**
     * Gets the id of the person.
     * <br/>
     * Post: The id of the person has been returned.
     * 
     * @return the id of the person
     */
    /****** 2c9b7a57-8b66-464e-aa18-8a619592f1b9 *******/
    public String getId() {
        return id;
    }

    /**
     * Sets the id of the person.
     * <br/>
     * Post: The id of the person has been updated.
     * 
     * @param id the new id of the person
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Returns a string representation of the person, including the id, name, and
     * country of the person.
     * 
     * @return a string representation of the person
     */
    @Override
    public String toString() {
        return "Id: " + id + "\nNombre: " + name + "\nPaís: " + country;
    }
}
