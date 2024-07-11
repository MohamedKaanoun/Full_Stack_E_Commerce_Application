import React from "react";
import { Card, CardContent, Typography, Button, styled } from "@mui/material";

const TopProducts = () => {
  return (
    <Card sx={{ position: "relative", bgcolor: "#242B2E", color: "white" }}>
      <CardContent>
        <Typography variant="h6" sx={{ letterSpacing: ".25px" }}>
          Top Products
        </Typography>
        <Typography variant="body2">Best sellers</Typography>
        <Typography variant="h5" sx={{ my: 3.1 }}>
          Product A, Product B
        </Typography>
        <Button size="small" variant="contained">
          View Products
        </Button>
      </CardContent>
    </Card>
  );
};

export default TopProducts;
