package com.furntrade.furntrademanagmentservet.Models;
public enum OrderStatus {
  //  @JsonProperty("waiting")
    WAITING,
 //   @JsonProperty("in_progress")
    IN_PROGRESS,
  //  @JsonProperty("distance-in-km")
    COMPLETED,
 //   @JsonProperty("distance-in-km")
    CANCELLED;
    //MOZDA JOS I ON_THE_WAY

    private String depCode;
//    @Override
//    public String toString() {
//        return depCode;
//    }
////
//    OrderStatus(String depCode) {
//
//        this.depCode = depCode;
//    }
////
//    OrderStatus() {
//
//    }
////
    public String getDepCode() {

        return this.depCode;
    }
////
//    @JsonCreator
//    public static OrderStatus getOrderStatus(String value) {
//
//        for (OrderStatus dep : OrderStatus.values()) {
//
//            if (dep.getDepCode().equals(value)) {
//
//                return dep;
//            }
//        }
//
//        return null;
//    }
}
