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
    public String getDepCode() {

        return this.depCode;
    }
}
