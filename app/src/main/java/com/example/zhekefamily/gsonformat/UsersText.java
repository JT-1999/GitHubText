package com.example.zhekefamily.gsonformat;

public class UsersText {

    /**
     * retcode : 0
     * obj : {"list":{"UserID":1001,"PhoneNumber":"18867665070","Password":"123456","UserName":"安卓开发"}}
     * error :
     */

    private int retcode;
    private ObjBean obj;
    private String error;

    public int getRetcode() {
        return retcode;
    }

    public void setRetcode(int retcode) {
        this.retcode = retcode;
    }

    public ObjBean getObj() {
        return obj;
    }

    public void setObj(ObjBean obj) {
        this.obj = obj;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static class ObjBean {
        /**
         * list : {"UserID":1001,"PhoneNumber":"18867665070","Password":"123456","UserName":"安卓开发"}
         */

        private ListBean list;

        public ListBean getList() {
            return list;
        }

        public void setList(ListBean list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * UserID : 1001
             * PhoneNumber : 18867665070
             * Password : 123456
             * UserName : 安卓开发
             */

            private int UserID;
            private String PhoneNumber;
            private String Password;
            private String UserName;

            public int getUserID() {
                return UserID;
            }

            public void setUserID(int UserID) {
                this.UserID = UserID;
            }

            public String getPhoneNumber() {
                return PhoneNumber;
            }

            public void setPhoneNumber(String PhoneNumber) {
                this.PhoneNumber = PhoneNumber;
            }

            public String getPassword() {
                return Password;
            }

            public void setPassword(String Password) {
                this.Password = Password;
            }

            public String getUserName() {
                return UserName;
            }

            public void setUserName(String UserName) {
                this.UserName = UserName;
            }
        }
    }
}
