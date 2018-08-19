package com.wolverineteam.ngpuppies.web;

import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Subscriber;
import com.wolverineteam.ngpuppies.services.base.BillService;
import com.wolverineteam.ngpuppies.services.base.SubscriberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client/")
public class ClientController {

    private SubscriberService subscriberService;
    private BillService billService;

    @Autowired
    public ClientController(SubscriberService subscriberService,BillService billService){

        this.subscriberService = subscriberService;
        this.billService = billService;
    }

    @PutMapping("bills/pay/{bills}")
    public void payBills(@PathVariable("bills") List<String> bills) {
        billService.payBills(bills);
    }

    @GetMapping("subscribers/{id}")
    public Subscriber getSubscriberById(@PathVariable("id") String subscriberId) {
        return subscriberService.getById(subscriberId);
    }

    @GetMapping("bills/paymentHistory/descending/{id}")
    public List<Bill> getPaymentsHistoryDescendingByBankId(@PathVariable("id") String bankId){
        int id = Integer.parseInt(bankId);
        return billService.getPaymentsHistoryDescendingByBankId(id);
    }

    @GetMapping("bills/{timeInterval}")
    public List<Object[]> getMaxAndAvgPaymentInTimeIntervalByBankId(
            @PathVariable("timeInterval") List<String> timeInterval) {
        return billService.getMaxAndAvgPaymentInTimeIntervalByBankId(timeInterval);
    }

    @GetMapping("bills/paidServices/{id}")
    public List<Bill> getPaidServicesByBankId(@PathVariable("id") String bankId){
        int id = Integer.parseInt(bankId);
        return billService.getPaidServicesByBankId(id);
    }

    @GetMapping("bills/topPayers/{id}")
    public List<Object[]> getTenBiggestPaymentsByBankId(@PathVariable("id") String bankId){
        int id = Integer.parseInt(bankId);
        return billService.getTenBiggestPaymentsByBankId(id);
    }

    @GetMapping("bills/recentPayments/{id}")
    public List<Bill> getTenMostRecentPaymentsByBankId(@PathVariable("id") String bankId){
        int id = Integer.parseInt(bankId);
        return billService.getTenMostRecentPaymentsByBankId(id);
    }

}
