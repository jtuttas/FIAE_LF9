package schueler;

/**
 * Priority
 */
public class Priority extends Entity {

    private int value;

    public Priority(int value,String name) {
        super(name);
        this.setValue(value);
    }

   
    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        if (value<0) {
            value=0;
        }
        this.value = value;
    }

    @Override
    public String getCreateStatement() {        
        return" insert into priority(value,description) values ("+value+",\""+getName()+"\");";
    }

    @Override
    public String getUpdateStatement() {
        return "update priority set value="+value+",description=\""+getName()+"\" where id="+id;
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
}