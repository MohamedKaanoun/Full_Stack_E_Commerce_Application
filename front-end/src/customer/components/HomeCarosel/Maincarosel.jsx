import React from "react";
import AliceCarousel from "react-alice-carousel";
import "react-alice-carousel/lib/alice-carousel.css";
import mainCarouselData from "./MainCaroselData";

const MainCarosel = () => {
  const items = mainCarouselData.map((item, index) => (
    <img
      src={item.image}
      alt={`Carousel item ${index + 1}`} // Added index to make alt text unique
      role="presentation"
      style={{
        width: "80%",
        height: "auto",
        display: "block",
        margin: "0 auto",
        cursor: "pointer",
      }}
      className="cursor-pointer z-0"
      key={index} // Added key for list items
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
