package com.example;

public enum ModelingQueryResultColumnInfo {
    ORDERID("orderId",0),
    REGDTTM ("regDttm",1),
    MEMBERSRL("memberSrl",2),
    ADDRESS("address",3),
    IP("ip",4),
    MOBILE("mobile",5),
    PROCESSINGSTATUS("processingStatus",6),
    VENDORITEMID("vendorItemId",7),
    NAME("name",8),
    VIOLATIONCODE("violationCode",9);

    private String fieldName;
    private int index;
    ModelingQueryResultColumnInfo(String fieldName, int index) {
        this.fieldName = fieldName;
        this.index = index;
    }

    public static int getColumnIndexByName(final String columnName){
        return ModelingQueryResultColumnInfo.valueOf(columnName).index;
    }
}