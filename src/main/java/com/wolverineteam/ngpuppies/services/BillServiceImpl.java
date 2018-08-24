package com.wolverineteam.ngpuppies.services;

import com.wolverineteam.ngpuppies.data.base.BillRepository;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.User;
import com.wolverineteam.ngpuppies.services.base.BillService;
import com.wolverineteam.ngpuppies.services.base.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillServiceImpl implements BillService {

    private BillRepository billRepository;
    private UserService userService;

    @Autowired
    public BillServiceImpl(BillRepository billRepository, UserService userService) {
        this.billRepository = billRepository;
        this.userService = userService;
    }

    @Override
    public List<Bill> getUnpaidBillsByBankId(String bankId) {
        int id = Integer.parseInt(bankId);
        return billRepository.getUnpaidBillsByBankId(id);
    }

    @Override
    public void createBill(Bill bill) {
        billRepository.createBill(bill);
    }

    @Override
    public void payBills(List<String> bills) {
        List<Integer> billsToBePaid = bills.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        billRepository.payBills(billsToBePaid);
    }

    @Override
    public List<Object[]> getMaxAndAvgPaymentInTimeIntervalByBankId(List<String> timeInterval) {
        int bankId = Integer.parseInt(timeInterval.get(2));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null, endDate = null;
        try {
            startDate = new Date(format.parse(timeInterval.get(0)).getTime());
            endDate = new Date(format.parse(timeInterval.get(1)).getTime());
        } catch (ParseException pe) {
            System.out.println(pe.getMessage());
        }

        return billRepository.getMaxAndAvgPaymentInTimeIntervalByBankId(bankId, startDate, endDate);
    }

    @Override
    public List<Bill> getSubscriberPaymentsHistoryDescendingByBankId(HttpServletRequest request, String subscriberId) {
        String userName = userService.getUsernameFromToken(request);
        User user = userService.loadUserByUsername(userName);
        int bankId = user.getUserId();
        return billRepository.getSubscriberPaymentsHistoryDescendingByBankId(bankId, subscriberId);
    }

    @Override
    public List<com.wolverineteam.ngpuppies.models.Service> getPaidServicesByBankId(String bankId) {
        int id = Integer.parseInt(bankId);
        return billRepository.getPaidServicesByBankId(id);
    }

    @Override
    public List<Object[]> getTenBiggestPaymentsByBankId(String bankId) {
        int id = Integer.parseInt(bankId);
        return billRepository.getTenBiggestPaymentsByBankId(id);
    }

    @Override
    public List<Bill> getTenMostRecentPaymentsByBankId(String bankId) {
        int id = Integer.parseInt(bankId);
        return billRepository.getTenMostRecentPaymentsByBankId(id);
    }
}
