package todo.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.JSONObject;

import todo.MissingParamaterException;

/**
 * Class of the Project Entity
 * 
 */
public class Project extends Entity {

    private String projectName="project";

    /**
     * Gets the name of the Project
     * @return the name
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the Name of the Project
     * @param projectName the name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }


    /**
     * INSERT Statement for the Project
     */
    @Override
    public String getCreateStatement() {        
        return "INSERT INTO project(name) VALUES (\""+this.getProjectName()+"\");";
    }

    /**
     * UPDATE Statement for the Project
     */
    @Override
    public String getUpdateStatement() {
        return "UPDATE project SET name=\""+this.getProjectName()+"\" WHERE projId="+this.getId()+";";
    }

    /**
     * DELETE Statement for the Project
     */
    @Override
    public String getDeleteStatement() {
        return "DELETE FROM project WHERE projId="+this.getId()+";";
    }

    /**
     * SELECT Statement for the Project
     */
    @Override
    public String getReadStatement() {
        return "SELECT * from project;";
    }

    /**
     * Set the Project from the Result Set from the Database
     */
    @Override
    public void setEnity(ResultSet rs) throws SQLException {
        this.setId(rs.getInt("projId"));
        this.projectName = rs.getString("name");
    }

    /**
     * Converts the Project to a JSON String
     */
    @Override
    public String toJSON() {
        return "{\"id\":"+this.getId()+",\r\n\"name\":\""+this.getProjectName()+"\"}";
    }

    /**
     * Create the Project from a JSON String
     * 
     * @throws Exception
     */
    @Override
    public Project parseJSON(String json) throws MissingParamaterException {
        Project p = new Project();
        JSONObject obj = new JSONObject(json);
        if (obj.has("name")) {
            this.projectName=obj.getString("name");
            p.setProjectName(this.projectName);
            System.out.println("Set Projektname to "+this.projectName);
        }
        else {
            throw new MissingParamaterException("Paramater name is missing");
        }
        if (obj.has("id")) {
            if (!obj.isNull("id")){
                this.setId(obj.getInt("id"));
                p.setId(this.getId());
            }

        }
        return p;
    }

    @Override
    public String toString() {        
        return this.getProjectName();
    }
    
}