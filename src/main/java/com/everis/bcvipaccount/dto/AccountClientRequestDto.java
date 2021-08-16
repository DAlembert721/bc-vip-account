package com.everis.bcvipaccount.dto;

import com.everis.bcvipaccount.domain.models.VipAccount;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AccountClientRequestDto {
    private String idAccount;
    private String accountNumber;
    private String type;
    private BigDecimal amountTotal;

    static public AccountClientRequestDto buildAccountClientRequest(VipAccount account) {
        return new AccountClientRequestDto(account.getId(),
                    account.getAccountNumber(),
                "VIP", account.getAccountBalance());
    }
}
