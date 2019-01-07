package serverDb;

public class GroupInfo {
    private String groupid;
    private String groupname;
    private String[] groupmember;

    public GroupInfo(){
        groupmember=new String[10];
    }

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

    public String[] getGroupmember(){
        return groupmember;
    }


    public void setGroupMember(int i,String groupmenber){
        this.groupmember[i]=groupmenber;
    }

}
