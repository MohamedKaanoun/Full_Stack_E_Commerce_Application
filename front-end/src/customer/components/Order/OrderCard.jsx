import { Grid } from "@mui/material";
import React from "react";
import AdjustIcon from "@mui/icons-material/Adjust";
import { useNavigate } from "react-router-dom";
const OrderCard = () => {
  const navigate = useNavigate();
  const handleOrderCard = () => {
    navigate(`/account/order/${5}`);
  };
  return (
    <div
      className="p-5 shadow-lg hover:shadow-2xl border mr-2"
      onClick={handleOrderCard}
    >
      <Grid container spacing={2} sx={{ justifyContent: "space-between" }}>
        <Grid item sx={6}>
          <div className="flex cursor-pointer ">
            <img
              className="w-[5rem] h-[5rem] object-cover object-top"
              src="https://tailwindui.com/img/ecommerce-images/product-page-02-tertiary-product-shot-02.jpg"
              alt=""
            />
            <div className="ml-5 space-y-2">
              <p className="">This is The title </p>
              <p className="opacity-50 text-xs font-semibold">
                Size : X,M,L,XL
              </p>
            </div>
          </div>
        </Grid>
        <Grid item xs={2}>
          <p>300 MAD</p>
        </Grid>
        <Grid item xs={4}>
          <p>
            {false && (
              <p>
                <span> Expected Dilivery on : March-03</span>
              </p>
            )}
            {true && (
              <div>
                <p>
                  <AdjustIcon
                    sx={{ width: "15px", height: "15px" }}
                    className="text-green-600 mr-2 text-sm"
                  />
                  <span>Dilivered on : March-03</span>
                </p>
                <p className="text-xs">Your item has been dilivered</p>
              </div>
            )}
          </p>
        </Grid>
      </Grid>
    </div>
  );
};

export default OrderCard;
