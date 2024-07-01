package com.example.cache.client.view;

import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseView;

public interface MemberView extends IBaseView {
    Long getMemberId();
    Character getMemberStatus();
}
