package todo.entity;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import todo.MissingParamaterException;

/**
 * Task class
 */
public class Task extends Entity {

    private String title;
    private Project project;
    private Priority priority;
    private String date = null;

    /**
     * Set the priority for a task
     * 
     * @param priority the prority
     */
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    /**
     * sets the project for the task
     * 
     * @param project the project
     */
    public void setProject(Project project) {
        this.project = project;
    }

    /**
     * set the title of a task
     * 
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * get the priority of the task
     * 
     * @return the priority
     */
    public Priority getPriority() {
        return priority;
    }

    /**
     * get the project of a task
     * 
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * get the title of the task
     * 
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * set the date for the task
     * 
     * @param date the date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * get the date for the task
     * 
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * Build the task from the ResulSet of the database
     */
    @Override
    public void setEnity(ResultSet rs) throws SQLException {
        this.setId(rs.getInt("id"));
        this.title = rs.getString("title");
        project = new Project();
        project.setId(rs.getInt("proId"));
        priority = new Priority();
        priority.setId(rs.getInt("priId"));
        this.date = rs.getString("date");
    }

    /**
     * returns the INSERT statement of the task
     */
    @Override
    public String getCreateStatement() {
        String sql1 = "INSERT INTO task(title";
        String sql2 = ") VALUES (\"" + this.title + "\"";
        if (this.project != null) {
            sql1 = sql1 + ",proId";
            sql2 = sql2 + "," + this.project.getId();
        }
        if (this.priority != null) {
            sql1 = sql1 + ",priId";
            sql2 = sql2 + "," + this.priority.getId();
        }
        if (this.date != null) {
            sql1 = sql1 + ",date";
            sql2 = sql2 + "," + this.date;
        }
        return sql1 + sql2 + ")";
    }

    /**
     * returns the UPDATE statement of the task
     */
    @Override
    public String getUpdateStatement() {
        System.out.println("Get Update Statement");
        String sql = "UPDATE task SET title=\"" + this.getTitle() + "\"";
        if (project != null) {
            System.out.println("Project: " + this.project.getId());
            sql = sql + ",proId=" + this.project.getId();
        }
        if (priority != null) {
            sql = sql + ",priId=" + this.getPriority().getId();
        }
        if (date != null) {
            sql = sql + ",date=\"" + this.date + "\"";
        }
        sql += " WHERE id=" + this.getId() + ";";
        return sql;
    }

    /**
     * returns the DELETE statement of the task
     */
    @Override
    public String getDeleteStatement() {
        return "DELETE FROM task WHERE id=" + this.getId() + ";";
    }

    /**
     * returns the SELECT statement of the task
     */
    @Override
    public String getReadStatement() {
        return "SELECT * from task";
    }

    /**
     * Converts the task to a JSON String
     */
    @Override
    public String toJSON() {
        String s ="";
        s+="{\"id\":" + this.getId() + ",\r\n\"title\":\"" + this.getTitle() + "\",\r\n";
        if (this.getProject()!=null) {
            s+="\"proId\":"+ this.getProject().getId() + ",\r\n";
        }
        if (this.getPriority()!=null) {
            s+="\"priId\":"+ this.getPriority().getId() + ",\r\n";

        }
        s+="\r\n\"date\":\""+ this.date + "\"}";
        return s;
    }

    /**
     * Converts a JSON String to the task Object
     * 
     * @throws MissingParamaterException
     */
    @Override
    public void parseJSON(String json) throws MissingParamaterException {
        System.out.print("Parse JSON:"+json);
        try {
            JSONObject obj = new JSONObject(json);
            if (obj.has("title")) {
                this.title=obj.getString("title");
                System.out.println("Set Title to "+this.title);
            }
            else {
                throw new MissingParamaterException("Parameter required title is missing");
            }
            if (obj.has("proId")) {
                this.project = new Project();
                if (!obj.isNull("proId")) {
                    System.out.println("Set proId to "+obj.getInt("proId"));
                    this.project.setId(obj.getInt("proId"));
                }
            }
            else {
                System.out.println("No proId");
                this.project=null;
            }
            
            if (obj.has("priId")) {
                this.priority = new Priority();
                if (!obj.isNull("priId")) {
                    System.out.println("Set priId to "+obj.getInt("priId"));
                    this.priority.setId(obj.getInt("priId"));
                }
            }
            else {
                System.out.println("No priId");
                this.priority=null;
            }
            
            if (obj.has("date")) {
                if (!obj.isNull("date")) {
                    System.out.println("Set date to "+obj.getString("date"));
                    this.date=obj.getString("date");
                }
            }
            else {
                System.out.println("No date");
                this.date=null;
            }
            if (obj.has("id")) {
                if (!obj.isNull("id")) {
                    this.setId(obj.getInt("id"));
                }
    
            }
        }
        catch (JSONException jx) {
            jx.printStackTrace();
            String msg = jx.getMessage();
            msg=msg.replace("\"", "");
            throw new MissingParamaterException(msg);
        }

    }

}