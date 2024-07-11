import React from "react";
import "./ProductCard.css";
import { useNavigate } from "react-router-dom";
const ProductCard = ({ item }) => {
  const navigate = useNavigate();
  return (
    <div
      onClick={() => navigate(`/product/${item.id}`)}
      className="productCard w-[15rem] m-3 transition-all cursor-pointer"
    >
      <div className="h-[20rem]">
        <img
          className="h-full w-full object-cover object-left-top"
          src={item.imageUrl}
          alt=""
        />
      </div>
      <div className="textPart bg-white p-3">
        <div className="">
          <p className="font-bold opacity-60 ">{item.brand}</p>
          <p>{item.title}</p>
        </div>
        <div className="flex items-center space-x-2">
          <p className="font-semibold">{item.discountPrice} MAD</p>
          <p className="line-through  opacity-50">{item.price} MAD</p>
          <p className="text-red-600 font-semibold">
            {item.discountPercent}% off
          </p>
        </div>
      </div>
    </div>
  );
};

export default ProductCard;
