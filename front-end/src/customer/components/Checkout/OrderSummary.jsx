import React, { useEffect } from "react";
import AddressCard from "../AddressCard/AddressCard";
import CartItem from "../Cart/CartItem";
import { Button } from "@mui/material";
import { useDispatch, useSelector } from "react-redux";
import { getOrderById } from "../../../State/Order/Action";
import { useLocation } from "react-router-dom";
import { get } from "../../../State/Cart/Action";
const OrderSummary = () => {
  const dispatch = useDispatch();
  const location = useLocation();
  const { order } = useSelector((store) => store);
  const { cart } = useSelector((store) => store);
  const searchParams = new URLSearchParams(location.search);
  const orderId = searchParams.get("order_id");
  useEffect(() => {
    dispatch(get());
  }, [cart.cartItems.length, dispatch]);
  const calculateTotalDiscount = () => {
    if (!cart.cart || !cart.cart.cartItems) {
      return 0;
    }
    return cart.cart.cartItems.reduce((total, item) => {
      const discount =
        (item.product.price - item.product.discountPrice) * item.quantity;
      return total + discount;
    }, 0);
  };

  useEffect(() => {
    dispatch(getOrderById(orderId));
  }, [orderId , dispatch]);
  return (
    <div>
      <div className="p-5 shadow-lg rounded-s-md border">
        <AddressCard address={order.order?.shippingAddress} />
      </div>
      <div>
        <div className="lg:grid grid-cols-3 relative ">
          <div className="col-span-2 ">
            {order.order?.orderItems?.map((item) => (
              <CartItem item={item} />
            ))}
          </div>
          <div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0">
            <div className="border">
              <p className="uppercase font-bold opacity-60 pb-2 pt-2">
                Price Details
              </p>
              <hr />
              <div className="space-y-3 font-sesmibold pr-3 pl-3">
                <div className="flex justify-between pt-3 text-black ">
                  <span>Price( {cart.cart?.totalItems} items )</span>
                  <span className="text-green-600 font-bold">
                    {cart.cart?.totalPrice} MAD
                  </span>
                </div>

                <div className="flex justify-between  ">
                  <span>Discount</span>
                  <span className="text-green-600 font-bold">
                    {calculateTotalDiscount()} MAD
                  </span>
                </div>
                <div className="flex justify-between text-black ">
                  <span>Dilivery Charge</span>
                  <span className="text-green-600 font-bold">Free</span>
                </div>
                <div className="flex justify-between pb-4 text-black ">
                  <span className=" font-bold">Total Amount</span>
                  <span className="text-green-600 font-bold">
                    {cart.cart?.totalDiscountedPrice} MAD
                  </span>
                </div>
              </div>
              <Button
                className="w-full"
                variant="contained"
                sx={{ px: "2rem", bgcolor: "#9155fd" }}
              >
                Checkout
              </Button>
            </div>
          </div>
        </div>
      </div>
    </div>
  );
};

export default OrderSummary;
