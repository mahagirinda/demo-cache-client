package com.example.cache.client.dto;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
@ToString
public class AccountDTO implements Serializable {
    @Id
    private long accountId;
    private String accountCode;
    private long memberId;
    private String clientCode;
    private String accountType;
    private String checkDigit;
    private String accountName;
    private String accountPosition;
    private String correspondAcNo;
    private String blockingReason;
    private String accountStatus;
    private String createdBy;
    private Date createdOn;
    private String modifiedBy;
    private Date modifiedOn;
    private String checkedBy;
    private Date checkedOn;
    private String approvedBy;
    private Date approvedOn;
}
