package com.wolverineteam.ngpuppies.data.base;

import com.wolverineteam.ngpuppies.data.dto.BillDTO;
import com.wolverineteam.ngpuppies.models.Bill;
import com.wolverineteam.ngpuppies.models.Service;

import java.sql.Date;
import java.util.List;


public interface BillRepository {

    Bill getById(int id);

    void createBill(Bill bill);

    List<Bill> getUnpaidBillsByBankId(int bankId);

    void payBills(List<Integer> bills);

    List<Bill> getSubscribersPaymentsHistoryDescendingByBankId(int bankId);

    List<BillDTO> getMaxAndAvgPaymentInTimeIntervalByBankId(int bankId, String subscriberId,
                                                            Date startDate, Date endDate);

    List<Service> getSubscriberPaidServicesByBankId(int bankId, String subscriberId);

    List<BillDTO> getTenBiggestPaymentsByBankId(int bankId);

    List<BillDTO> getTenMostRecentPaymentsByBankId(int bankId);
}
