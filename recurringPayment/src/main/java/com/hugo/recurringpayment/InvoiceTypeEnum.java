package com.hugo.recurringpayment;

public enum InvoiceTypeEnum {

    UNKNOWN(0, "UNKNOWN"),
    WEEKLY(1,"WEEKLY"),
    MONTHLY(2, "MONTHLY");


    private int code;
    private String desc;

     InvoiceTypeEnum(int code, String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public static InvoiceTypeEnum getFromCode(int code){

         for(InvoiceTypeEnum it: values()){
             if(it.getCode() == code){
                 return it;
             }
         }
         return UNKNOWN;

    }
}
