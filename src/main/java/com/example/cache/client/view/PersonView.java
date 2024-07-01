package com.example.cache.client.view;

import com.telkomsigma.framework.core.api.cache.repository.interfaces.IBaseView;

public interface PersonView extends IBaseView {
    String getNik();
    String getFullName();
}
