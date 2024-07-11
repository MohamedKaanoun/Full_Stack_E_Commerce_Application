import React from "react";
import { Card, CardContent, Typography, Button, styled } from "@mui/material";

const src =
  "https://i.etsystatic.com/34826767/r/il/4f8c6f/3782633063/il_1080xN.3782633063_d0zy.jpg";

const TriangleImg = styled("img")({
  right: 0,
  bottom: 0,
  height: 170,
  position: "absolute",
});

const TrophyImg = styled("img")({
  right: 36,
  bottom: 20,
  height: 96,
  position: "absolute",
});

const Achievement = () => {
  return (
    <Card sx={{ position: "relative", bgcolor: "#242B2E", color: "white" }}>
      <CardContent>
        <Typography variant="h6" sx={{ letterSpacing: ".25px" }}>
          Shop With Kaanoun
        </Typography>
        <Typography variant="body2">Congratulations 🤩</Typography>
        <Typography variant="h5" sx={{ my: 3.1 }}>
          222.2 k
        </Typography>
        <Button size="small" variant="contained">
          View Sales
        </Button>
        <TriangleImg />
        <TrophyImg src={src} />
      </CardContent>
    </Card>
  );
};

export default Achievement;
