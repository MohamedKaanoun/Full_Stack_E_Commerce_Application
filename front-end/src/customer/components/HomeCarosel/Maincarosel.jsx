import React from "react";
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";
import mainCarouselData from "./MainCaroselData";

const items = [
  <div className="item" data-value="1">
    1
  </div>,
  <div className="item" data-value="2">
    2
  </div>,
  <div className="item" data-value="3">
    3
  </div>,
  <div className="item" data-value="4">
    4
  </div>,
  <div className="item" data-value="5">
    5
  </div>,
];

const MainCarosel = () => {
  const items = mainCarouselData.map((item) => (
    <img
      src={item.image}
      alt={`Carousel item`}
      role="presentation"
      style={{
        width: "80%",
        height: "auto",
        display: "block",
        margin: "0 auto",
        cursor: "pointer",
      }}
      className="cursor-pointer z-0"
    />
  ));
  return (
    <AliceCarousel
      autoPlay
      autoPlayStrategy="none"
      autoPlayInterval={1000}
      animationDuration={1000}
      animationType="slide"
      infinite
      disableDotsControls
      disableButtonsControls
      items={items}
    />
  );
};

export default MainCarosel;
