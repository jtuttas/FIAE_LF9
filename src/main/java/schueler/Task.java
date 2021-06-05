package schueler;

import java.util.Date;

public class Task extends Entity{

    private Priority priority;
    private Project project;
    private Date date;

    public Task(String title,Date d) {
        super(title);
        date=d;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return super.getName();
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
    public String getReadAllStatement() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
