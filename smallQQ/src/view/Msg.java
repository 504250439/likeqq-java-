package view;

/**
 *
 * 对信息进行封装
 * 防止写入错误
 *
 */


    public class Msg {

    private String MyUID;
    private String toUID;
    private String msg;
    private String type;
    private String code;

    public String getMyUID() {
        return MyUID;
    }

    public void setMyUID(String myUID) {
        MyUID = myUID;
    }

    public String getToUID() {
        return toUID;
    }

    public void setToUID(String toUID) {
        this.toUID = toUID;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }



}