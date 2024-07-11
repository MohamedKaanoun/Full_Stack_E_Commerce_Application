import React, { useState, useRef } from "react";
import AliceCarousel from "react-alice-carousel";
import HomeSectionCard from "../HomeSectionCard/HomeSectionCard";
import KeyboardArrowLeftIcon from "@mui/icons-material/KeyboardArrowLeft";
import { Button } from "@mui/material";

const HomeSectionCarosel = ({ data, sectionName }) => {
  const [activeIndex, setActiveIndex] = useState(0);
  const carouselRef = useRef(null);

  const responsive = {
    0: { items: 1 },
    720: { items: 3 },
    1024: { items: 5 },
  };

  const slidePrev = () => {
    const newIndex = activeIndex > 0 ? activeIndex - 1 : activeIndex;
    setActiveIndex(newIndex);
    carouselRef.current.slideTo(newIndex);
  };

  const slideNext = () => {
    const newIndex =
      activeIndex < items.length - 1 ? activeIndex + 1 : activeIndex;
    setActiveIndex(newIndex);
    carouselRef.current.slideTo(newIndex);
  };

  const syncActiveIndex = ({ item }) => setActiveIndex(item);

  const items = data.map((item) => (
    <HomeSectionCard key={item.id} product={item} />
  ));

  return (
    <div className="border">
      <h2 className="text-2xl font-extrabold text-gray-800 py-5">
        {sectionName}
      </h2>
      <div className="relative p-5">
        <AliceCarousel
          ref={carouselRef}
          disableDotsControls
          disableButtonsControls
          items={items}
          responsive={responsive}
          onSlideChanged={syncActiveIndex}
          activeIndex={activeIndex}
        />
        {activeIndex < items.length - responsive[1024].items && (
          <Button
            sx={{
              position: "absolute",
              top: "8rem",
              right: "0rem",
              transform: "translateX(50%) rotate(90deg)",
              bgcolor: "white",
            }}
            onClick={slideNext}
            variant="contained"
            aria-label="next"
            className="z-50 bg-white "
          >
            <KeyboardArrowLeftIcon
              sx={{ transform: "rotate(90deg)", color: "black" }}
            />
          </Button>
        )}
        {activeIndex > 0 && (
          <Button
            onClick={slidePrev}
            sx={{
              position: "absolute",
              top: "8rem",
              left: "0rem",
              transform: " rotate(-90deg)",
              bgcolor: "white",
            }}
            variant="contained"
            aria-label="prev"
            className="z-50 bg-white "
          >
            <KeyboardArrowLeftIcon
              sx={{ transform: "rotate(90deg)", color: "black" }}
            />
          </Button>
        )}
      </div>
    </div>
  );
};

export default HomeSectionCarosel;
