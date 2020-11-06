package com.testProject.Shop.db.service.api;

import com.testProject.Shop.domain.CustomerAccount;
import org.springframework.lang.Nullable;

public interface CustomerAccountService {

    void addCustomerAccount(CustomerAccount customerAccount);

    @Nullable
    Double getmoney(int customerId);

    void setMoney(int customerId, double money);
}
