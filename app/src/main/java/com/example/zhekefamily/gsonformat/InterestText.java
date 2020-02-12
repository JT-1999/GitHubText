package com.example.zhekefamily.gsonformat;

import java.util.List;

public class InterestText {

    /**
     * retcode : 0
     * obj : {"list":[{"InterestID":1001,"InterestName":"信息学院"},{"InterestID":1002,"InterestName":"浙江科技学院"},{"InterestID":1003,"InterestName":"后勤服务中心"},{"InterestID":1004,"InterestName":"西和公寓"},{"InterestID":1005,"InterestName":"灵峰超市"},{"InterestID":1006,"InterestName":"阿里妈妈"}]}
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
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * InterestID : 1001
             * InterestName : 信息学院
             */

            private int InterestID;
            private String InterestName;

            public int getInterestID() {
                return InterestID;
            }

            public void setInterestID(int InterestID) {
                this.InterestID = InterestID;
            }

            public String getInterestName() {
                return InterestName;
            }

            public void setInterestName(String InterestName) {
                this.InterestName = InterestName;
            }
        }
    }
}
