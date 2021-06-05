package schueler;

public abstract class Entity {

    private String name;
    protected int id;

    public Entity(String name) {
        this.name=name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract String getCreateStatement();
    public abstract String getUpdateStatement();
    public abstract String getDeleteStatement();
    public abstract String getReadStatement();
    public abstract String getReadAllStatement();
}
