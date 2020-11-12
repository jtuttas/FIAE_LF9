package schueler;

/**
 * Project
 */
public class Project extends Entity{


    public Project(String n) {
        super(n);
    }

    @Override
    public String getCreateStatement() {        
        return "insert into project(name) values ("+getName()+")";
    }

    @Override
    public String getUpdateStatement() {        
        return "update project set name="+getName()+" where id="+id;
    }

    @Override
    public String getDeleteStatement() {        
        return "delete from project where id="+id;
    }

    @Override
    public String getReadStatement() {
        return "select * from project where id="+id;
    }

}