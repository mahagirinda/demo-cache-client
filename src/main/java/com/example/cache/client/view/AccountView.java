package com.example.cache.client.view;

import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseView;
import lombok.Data;

public interface AccountView extends IBaseView {
    Long getAccountId();
    String getAccountCode();
    Character getAccountStatus();
    Long getMemberId();
    String getAccountType();
    String getClientCode();
    Integer getCountAccountId();
}
