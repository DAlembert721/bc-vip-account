package com.everis.bcvipaccount.dto;

import com.everis.bcvipaccount.domain.models.VipAccount;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class VipAccountRequestDto {
    private BigDecimal accountBalance;
    private Boolean isActive;

    static public VipAccount requestToEntity(VipAccountRequestDto requestDto) {
        VipAccount vipAccount = new VipAccount();
        vipAccount.setAccountBalance(requestDto.getAccountBalance());
        vipAccount.setIsActive(requestDto.getIsActive());
        return vipAccount;
    }
}
