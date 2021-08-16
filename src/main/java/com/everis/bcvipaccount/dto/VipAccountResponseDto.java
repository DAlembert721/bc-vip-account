package com.everis.bcvipaccount.dto;

import com.everis.bcvipaccount.domain.models.VipAccount;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class VipAccountResponseDto {
    private String accountNumber;
    private BigDecimal accountBalance;
    private Boolean isActive;
    private Date createdAt;
    private Date modifiedAt;
    private BigDecimal minimalAmount;

    static public VipAccountResponseDto entityToResponse(VipAccount vipAccount) {
        VipAccountResponseDto responseDto = new VipAccountResponseDto();
        responseDto.setAccountNumber(vipAccount.getAccountNumber());
        responseDto.setAccountBalance(vipAccount.getAccountBalance());
        responseDto.setIsActive(vipAccount.getIsActive());
        responseDto.setCreatedAt(vipAccount.getCreatedAt());
        responseDto.setModifiedAt(vipAccount.getModifiedAt());
        responseDto.setMinimalAmount(vipAccount.getMinimalAmount());
        return responseDto;
    }
}
