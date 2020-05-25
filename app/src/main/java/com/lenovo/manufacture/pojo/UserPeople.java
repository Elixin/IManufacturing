package com.lenovo.manufacture.pojo;

import java.util.List;

public class UserPeople {
    /**
     * status : 200
     * message : SUCCESS
     * data : [{"id":4381,"userWorkId":1,"power":52,"peopleId":8,"userProductionLineId":2654,"workPostId":4},{"id":4380,"userWorkId":1,"power":52,"peopleId":7,"userProductionLineId":2654,"workPostId":3},{"id":4379,"userWorkId":1,"power":52,"peopleId":5,"userProductionLineId":2654,"workPostId":1},{"id":4378,"userWorkId":1,"power":100,"peopleId":30,"userProductionLineId":2654,"workPostId":""},{"id":4377,"userWorkId":1,"power":40,"peopleId":23,"userProductionLineId":2655,"workPostId":1},{"id":4376,"userWorkId":1,"power":100,"peopleId":25,"userProductionLineId":2655,"workPostId":6},{"id":4375,"userWorkId":1,"power":100,"peopleId":22,"userProductionLineId":2655,"workPostId":""},{"id":4374,"userWorkId":1,"power":50,"peopleId":17,"userProductionLineId":2655,"workPostId":8},{"id":4373,"userWorkId":1,"power":100,"peopleId":15,"userProductionLineId":2655,"workPostId":""},{"id":4372,"userWorkId":1,"power":100,"peopleId":16,"userProductionLineId":2655,"workPostId":7},{"id":4371,"userWorkId":1,"power":100,"peopleId":4,"userProductionLineId":2654,"workPostId":""},{"id":4370,"userWorkId":1,"power":100,"peopleId":3,"userProductionLineId":2654,"workPostId":""},{"id":4369,"userWorkId":1,"power":72,"peopleId":2,"userProductionLineId":2654,"workPostId":2},{"id":4368,"userWorkId":1,"power":100,"peopleId":1,"userProductionLineId":2654,"workPostId":""}]
     */

    private int status;
    private String message;
    private List<DataBean> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 4381
         * userWorkId : 1
         * power : 52
         * peopleId : 8
         * userProductionLineId : 2654
         * workPostId : 4
         */

        private int id;
        private int userWorkId;
        private int power;
        private int peopleId;
        private int userProductionLineId;
        private String workPostId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserWorkId() {
            return userWorkId;
        }

        public void setUserWorkId(int userWorkId) {
            this.userWorkId = userWorkId;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public int getPeopleId() {
            return peopleId;
        }

        public void setPeopleId(int peopleId) {
            this.peopleId = peopleId;
        }

        public int getUserProductionLineId() {
            return userProductionLineId;
        }

        public void setUserProductionLineId(int userProductionLineId) {
            this.userProductionLineId = userProductionLineId;
        }

        public String getWorkPostId() {
            return workPostId;
        }

        public void setWorkPostId(String workPostId) {
            this.workPostId = workPostId;
        }
    }
}
