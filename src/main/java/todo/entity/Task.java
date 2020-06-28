package todo.entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.json.JSONObject;


public class Task extends Entity {

    private String title;
    private Project project;
    private Priority priority;
    private String date;

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
    public void setProject(Project project) {
        this.project = project;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public Priority getPriority() {
        return priority;
    }
    public Project getProject() {
        return project;
    }
    public String getTitle() {
        return title;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getDate() {
        return date;
    }

    @Override
    public void setEnity(ResultSet rs) throws SQLException {
        this.setId(rs.getInt("id"));
        this.title = rs.getString("title");
        project = new Project();
        project.setId(rs.getInt("proId"));
        priority = new Priority();
        priority.setId(rs.getInt("priId"));       
        this.date=rs.getString("date");
    }

    @Override
    public String getCreateStatement() {
        return "INSERT INTO task(title,proId,priId,date) VALUES (\""+this.getTitle()+"\","+this.project.getId()+","+this.priority.getId()+",\""+this.date+"\");";
    }

    @Override
    public String getUpdateStatement() {
        return "UPDATE task SET title=\""+this.getTitle()+"\",proId="+this.project.getId()+",priId="+this.getPriority().getId()+",date=\""+this.date+"\" WHERE id="+this.getId()+";";
    }

    @Override
    public String getDeleteStatement() {
        return "DELETE FROM task WHERE id="+this.getId()+";";
    }

    @Override
    public String getReadStatement() {
        return "SELECT * from task";
    }

    @Override
    public String toJSON() {
        return "{\"id\":"+this.getId()+",\r\n\"title\":\""+this.getTitle()+"\",\r\n\"proId\":"+this.getProject().getId()+",\r\n\"priId\":"+this.getPriority().getId()+",\r\n\"date\":\""+this.date+"\"}";
    }

    @Override
    public void parseJSON(String json) {
        System.out.print("Parse JSON:"+json);
        JSONObject obj = new JSONObject(json);
        this.title=obj.getString("title");
        System.out.println("Set Title to "+this.title);
        this.project = new Project();
        this.project.setId(obj.getInt("proId"));
        this.priority = new Priority();
        this.priority.setId(obj.getInt("priId"));
        this.date=obj.getString("date");
    }
    
}