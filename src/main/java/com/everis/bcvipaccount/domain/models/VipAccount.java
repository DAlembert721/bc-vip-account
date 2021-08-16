package com.everis.bcvipaccount.domain.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

@Getter
@Setter
@Document
@NoArgsConstructor
@AllArgsConstructor
public class VipAccount {
    @Id
    private String id;
    private String accountNumber;
    private BigDecimal accountBalance;
    private Boolean isActive;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date modifiedAt;

    private BigDecimal minimalAmount;


    static public VipAccount generateNewVipAccount(BigDecimal minimal) {
        VipAccount account = new VipAccount();
        account.setAccountNumber(generateAccountNumber());
        account.setCreatedAt(new Date());
        account.setAccountBalance(new BigDecimal(0));
        account.setIsActive(true);
        account.setMinimalAmount(minimal);
        return account;
    }


    static private String generateAccountNumber() {
        final String SAVINGS_ACCOUNT_PREFIX = "300-";
        Random random = new Random();
        return SAVINGS_ACCOUNT_PREFIX + random.nextInt(999999999);
    }

}
