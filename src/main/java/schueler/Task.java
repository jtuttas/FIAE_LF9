package schueler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import todo.MissingParamaterException;
import todo.entity.Entity;

public class Task extends Entity {

    private Project project;
    private Priority priority;
    private Date date;

    public Task(String t, Date d) {

        date = d;
    }

    public String getTitle() {
        return null;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Project getProject() {
        return project;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override
    public void setEnity(ResultSet rs) throws SQLException {
        // TODO Auto-generated method stub

    }

    @Override
    public String getCreateStatement() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getUpdateStatement() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getDeleteStatement() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String getReadStatement() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toJSON() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Entity parseJSON(String json) throws MissingParamaterException {
        // TODO Auto-generated method stub
        return null;
    }

}
