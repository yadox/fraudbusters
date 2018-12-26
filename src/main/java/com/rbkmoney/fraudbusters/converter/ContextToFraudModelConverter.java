package com.rbkmoney.fraudbusters.converter;

import com.rbkmoney.damsel.domain.Payer;
import com.rbkmoney.damsel.proxy_inspector.Context;
import com.rbkmoney.damsel.proxy_inspector.Party;
import com.rbkmoney.damsel.proxy_inspector.PaymentInfo;
import com.rbkmoney.fraudo.model.FraudModel;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class ContextToFraudModelConverter implements Converter<Context, FraudModel> {

    @Override
    public FraudModel convert(Context context) {
        FraudModel fraudModel = new FraudModel();
        PaymentInfo payment = context.getPayment();
        Party party = payment.getParty();
        fraudModel.setPartyId(party.getPartyId());
        Payer payer = payment.getPayment().getPayer();
        fraudModel.setBin(payer.getCustomer().getPaymentTool().getBankCard().getBin());
        fraudModel.setEmail(payer.getCustomer().getContactInfo().getEmail());
        fraudModel.setShopId(payment.getShop().getId());
        return fraudModel;
    }
}