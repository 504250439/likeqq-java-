package serverDb;

public class GroupInfo {
    private String groupid;
    private String groupname;

    public void setGroupname(String groupname){
        this.groupname=groupname;
    }

    public void setGroupid(String groupid){
        this.groupid=groupid;
    }

    public String getGroupname(){
        return groupname;
    }


    public String getGroupid(){
        return groupid;
    }

}
