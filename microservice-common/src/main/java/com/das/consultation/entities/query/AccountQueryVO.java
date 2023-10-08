package com.das.consultation.entities.query;

import com.das.consultation.entities.vo.AccountTradeInfoVO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * created by jun on 2020/11/26
 * describe:his对账账拉取出参实体
 * version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "data")
public class AccountQueryVO {
    @XmlElement(name = "tradeInfo")
    private List<AccountTradeInfoVO> tradeInfo;

}
