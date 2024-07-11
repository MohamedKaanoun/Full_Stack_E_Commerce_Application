package com.backend.EcommerceProjectBackEnd.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDetails {

    private String paymentMethod;

    private String paymentStatus;

    private String paymentId;

    private String razorPaymentLinkId;

    private String razorPaymentLinkReferenceId;

    private String razorPaymentLinkStatus;

    private String razorPaymentId;
}
