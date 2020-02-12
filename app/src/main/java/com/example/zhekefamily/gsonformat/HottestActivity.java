package com.example.zhekefamily.gsonformat;

public class HottestActivity {

    /**
     * retcode : 0
     * obj : {"list":{"ActivityID":1001,"ActivityTittle":"安卓答辩","ActivityContent":"2020年1月4日下午一点，在实验中心618进行安卓期末答辩。","ActivityHot":"00015","UserID":1001,"UserName":"安卓开发"}}
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
         * list : {"ActivityID":1001,"ActivityTittle":"安卓答辩","ActivityContent":"2020年1月4日下午一点，在实验中心618进行安卓期末答辩。","ActivityHot":"00015","UserID":1001,"UserName":"安卓开发"}
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
             * ActivityID : 1001
             * ActivityTittle : 安卓答辩
             * ActivityContent : 2020年1月4日下午一点，在实验中心618进行安卓期末答辩。
             * ActivityHot : 00015
             * UserID : 1001
             * UserName : 安卓开发
             */

            private int ActivityID;
            private String ActivityTittle;
            private String ActivityContent;
            private String ActivityHot;
            private int UserID;
            private String UserName;

            public int getActivityID() {
                return ActivityID;
            }

            public void setActivityID(int ActivityID) {
                this.ActivityID = ActivityID;
            }

            public String getActivityTittle() {
                return ActivityTittle;
            }

            public void setActivityTittle(String ActivityTittle) {
                this.ActivityTittle = ActivityTittle;
            }

            public String getActivityContent() {
                return ActivityContent;
            }

            public void setActivityContent(String ActivityContent) {
                this.ActivityContent = ActivityContent;
            }

            public String getActivityHot() {
                return ActivityHot;
            }

            public void setActivityHot(String ActivityHot) {
                this.ActivityHot = ActivityHot;
            }

            public int getUserID() {
                return UserID;
            }

            public void setUserID(int UserID) {
                this.UserID = UserID;
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
