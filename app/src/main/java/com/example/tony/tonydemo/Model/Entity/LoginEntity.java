package com.example.tony.tonydemo.Model.Entity;

/**
 * Created by lzy on 2016/7/30.
 */
public class LoginEntity {

    /**
     * Status : 0
     * Data : {"USER_NAME":"XTB:NULL","USER_SCHOOL":null,"SERVICE_START_TIME":"0001-01-01 00:00:00","SERVICE_END_TIME":"0001-01-01 00:00:00","OID":"4AC5C6C2-7A70-46DA-B5BA-E243C0CC706E","SEQID":257,"LOGIN_NAME":"student6","USER_EMAIL":"","USER_MOBILE":"","USER_PASSWORD":"******","ACCOUNT_STATUS":0,"CRETE_TIME":"0001-01-01 00:00:00","AppID":null,"UserID":null,"Id":0}
     * Message : BF09653C-4F47-4D8F-9D2F-135ABD18FF4A
     */

    private int Status;
    /**
     * USER_NAME : XTB:NULL
     * USER_SCHOOL : null
     * SERVICE_START_TIME : 0001-01-01 00:00:00
     * SERVICE_END_TIME : 0001-01-01 00:00:00
     * OID : 4AC5C6C2-7A70-46DA-B5BA-E243C0CC706E
     * SEQID : 257
     * LOGIN_NAME : student6
     * USER_EMAIL :
     * USER_MOBILE :
     * USER_PASSWORD : ******
     * ACCOUNT_STATUS : 0
     * CRETE_TIME : 0001-01-01 00:00:00
     * AppID : null
     * UserID : null
     * Id : 0
     */

    private UserEntity Data;
    private String Message;

    public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }

    public UserEntity getData() {
        return Data;
    }

    public void setData(UserEntity Data) {
        this.Data = Data;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public static class UserEntity {
        private String USER_NAME;
        private Object USER_SCHOOL;
        private String SERVICE_START_TIME;
        private String SERVICE_END_TIME;
        private String OID;
        private int SEQID;
        private String LOGIN_NAME;
        private String USER_EMAIL;
        private String USER_MOBILE;
        private String USER_PASSWORD;
        private int ACCOUNT_STATUS;
        private String CRETE_TIME;
        private Object AppID;
        private Object UserID;
        private int Id;

        public String getUSER_NAME() {
            return USER_NAME;
        }

        public void setUSER_NAME(String USER_NAME) {
            this.USER_NAME = USER_NAME;
        }

        public Object getUSER_SCHOOL() {
            return USER_SCHOOL;
        }

        public void setUSER_SCHOOL(Object USER_SCHOOL) {
            this.USER_SCHOOL = USER_SCHOOL;
        }

        public String getSERVICE_START_TIME() {
            return SERVICE_START_TIME;
        }

        public void setSERVICE_START_TIME(String SERVICE_START_TIME) {
            this.SERVICE_START_TIME = SERVICE_START_TIME;
        }

        public String getSERVICE_END_TIME() {
            return SERVICE_END_TIME;
        }

        public void setSERVICE_END_TIME(String SERVICE_END_TIME) {
            this.SERVICE_END_TIME = SERVICE_END_TIME;
        }

        public String getOID() {
            return OID;
        }

        public void setOID(String OID) {
            this.OID = OID;
        }

        public int getSEQID() {
            return SEQID;
        }

        public void setSEQID(int SEQID) {
            this.SEQID = SEQID;
        }

        public String getLOGIN_NAME() {
            return LOGIN_NAME;
        }

        public void setLOGIN_NAME(String LOGIN_NAME) {
            this.LOGIN_NAME = LOGIN_NAME;
        }

        public String getUSER_EMAIL() {
            return USER_EMAIL;
        }

        public void setUSER_EMAIL(String USER_EMAIL) {
            this.USER_EMAIL = USER_EMAIL;
        }

        public String getUSER_MOBILE() {
            return USER_MOBILE;
        }

        public void setUSER_MOBILE(String USER_MOBILE) {
            this.USER_MOBILE = USER_MOBILE;
        }

        public String getUSER_PASSWORD() {
            return USER_PASSWORD;
        }

        public void setUSER_PASSWORD(String USER_PASSWORD) {
            this.USER_PASSWORD = USER_PASSWORD;
        }

        public int getACCOUNT_STATUS() {
            return ACCOUNT_STATUS;
        }

        public void setACCOUNT_STATUS(int ACCOUNT_STATUS) {
            this.ACCOUNT_STATUS = ACCOUNT_STATUS;
        }

        public String getCRETE_TIME() {
            return CRETE_TIME;
        }

        public void setCRETE_TIME(String CRETE_TIME) {
            this.CRETE_TIME = CRETE_TIME;
        }

        public Object getAppID() {
            return AppID;
        }

        public void setAppID(Object AppID) {
            this.AppID = AppID;
        }

        public Object getUserID() {
            return UserID;
        }

        public void setUserID(Object UserID) {
            this.UserID = UserID;
        }

        public int getId() {
            return Id;
        }

        public void setId(int Id) {
            this.Id = Id;
        }
    }
}
