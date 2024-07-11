import React, { useEffect } from "react";
import CartItem from "./CartItem";
import { Button } from "@mui/material";
import { useNavigate } from "react-router-dom";
import { useDispatch, useSelector } from "react-redux";
import { get } from "../../../State/Cart/Action";

const Cart = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { cart } = useSelector((store) => store);

  const handleCheckout = () => {
    navigate("/checkout?step=2");
  };

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

  return (
    <div>
      <div className="lg:grid grid-cols-3 lg:px-16 relative ">
        <div className="col-span-2 ">
          {cart.cart?.cartItems?.map((item) => (
            <CartItem item={item} key={item.id} />
          ))}
        </div>
        <div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0">
          <div className="border">
            <p className="uppercase font-bold opacity-60 pb-2 pt-2">
              Price Details
            </p>
            <hr />
            <div className="space-y-3 font-semibold pr-3 pl-3">
              <div className="flex justify-between pt-3 text-black">
                <span>Price ({cart.cart?.totalItem} items)</span>
                <span className="text-green-600 font-bold">
                  {cart.cart?.totalPrice} MAD
                </span>
              </div>
              <div className="flex justify-between">
                <span>Discount</span>
                <span className="text-green-600 font-bold">
                  {calculateTotalDiscount()} MAD
                </span>
              </div>
              <div className="flex justify-between text-black">
                <span>Delivery Charge</span>
                <span className="text-green-600 font-bold">Free</span>
              </div>
              <div className="flex justify-between pb-4 text-black">
                <span className="font-bold">Total Amount</span>
                <span className="text-green-600 font-bold">
                  {cart.cart?.totalDiscountedPrice} MAD
                </span>
              </div>
            </div>
            <Button
              className="w-full"
              variant="contained"
              sx={{ px: "2rem", bgcolor: "#9155fd" }}
              onClick={handleCheckout}
            >
              Checkout
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Cart;
