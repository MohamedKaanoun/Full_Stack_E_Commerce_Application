import React from "react";
import MainCarosel from "../../components/HomeCarosel/Maincarosel";
import HomeSectionCarosel from "../../components/HomeSectionCarosel/HomeSectionCarosel";
import { mens_kurta } from "../../../Data/mens_kurta";
const HomePage = () => {
  return (
    <div className="z-0">
      <MainCarosel />
      <div className="py-20 space-y-10 flex flex-col px-5 lg:px-10">
        <HomeSectionCarosel data={mens_kurta} sectionName={"Men's Kurta"} />
        <HomeSectionCarosel data={mens_kurta} sectionName={"Men's Shoes"} />
        <HomeSectionCarosel data={mens_kurta} sectionName={"Men's Shirts"} />
        <HomeSectionCarosel data={mens_kurta} sectionName={"Women's Saree"} />
        <HomeSectionCarosel data={mens_kurta} sectionName={"Women's Dress"} />
      </div>
    </div>
  );
};

export default HomePage;
