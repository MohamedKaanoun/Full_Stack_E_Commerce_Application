import { Step, StepLabel, Stepper } from "@mui/material";
import React from "react";

const OrderTracker = (activeStep) => {
  const steps = [
    "Placed",
    "Order Confirmed",
    "shipped",
    "Out For Dilivery",
    "Dilivered",
  ];
  return (
    <div className="w-full">
      <Stepper activeStep={3} alternativeLabel>
        {steps.map((label) => (
          <Step>
            <StepLabel sx={{ color: "#9155FD", fontSize: "44px" }}>
              {label}
            </StepLabel>
          </Step>
        ))}
      </Stepper>
    </div>
  );
};

export default OrderTracker;
