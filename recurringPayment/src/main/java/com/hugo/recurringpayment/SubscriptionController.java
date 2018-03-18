package com.hugo.recurringpayment;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
public class SubscriptionController {

    @RequestMapping("/subscript")
    public Subscription subscription(@RequestParam(value="amount", defaultValue="0.0") double amount,
                                     @RequestParam(value="invoiceType", defaultValue = "1") int invoiceType,
                                     @RequestParam(value="startDate", defaultValue = "06/02/2018")Date startDate,
                                     @RequestParam(value="endDate", defaultValue = "27/08/2018")Date endDate){

        return generateSubscription ( amount,  invoiceType,  startDate,  endDate);

    }

    private Subscription generateSubscription (double amount, int invoiceType, Date startDate, Date endDate){

        Subscription sub = new Subscription();
        sub.setId(generateId());
        sub.setType(InvoiceTypeEnum.getFromCode(invoiceType).getDesc());
        sub.setAmount(new Amount(amount,"AUD"));
        sub.setInvoiceDates(generateDates(invoiceType, startDate, endDate));

        return sub;

    }

    private List<LocalDate> generateDates(int invoiceType, Date startDate, Date endDate){
        List<LocalDate> dates= new ArrayList<LocalDate>();
        long diff = endDate.getTime() - startDate.getTime();
        TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

       if(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS)>33){
           throw new RuntimeException("The maximum duration is3 months");
       }
        LocalDate sd = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate ed = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if(invoiceType == InvoiceTypeEnum.WEEKLY.getCode()){
            dates= calcTuesdays( sd, ed);
        }else if(invoiceType ==  InvoiceTypeEnum.MONTHLY.getCode()){
            dates = calcDayofMonth20s(sd, ed);

        }

        return dates;
    }

    private List<LocalDate> calcTuesdays(LocalDate sd, LocalDate ed) {

        List<LocalDate> list = new ArrayList<LocalDate>();

        while(sd.isBefore(ed)) {
            sd = sd.with(TemporalAdjusters.next( DayOfWeek.TUESDAY ));
//            System.out.println(sd);
            list.add(sd);
        }
        return list;
    }

    private List<LocalDate> calcDayofMonth20s(LocalDate sd, LocalDate ed){
        List<LocalDate> list = new ArrayList<LocalDate>();

        while(sd.isBefore(ed)) {
            sd = sd.withDayOfMonth(20);
            list.add(sd);
            sd = sd.plusMonths(1);

        }
        return list;
    }

    private String generateId() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }
}
