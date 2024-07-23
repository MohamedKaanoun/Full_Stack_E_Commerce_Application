import React from "react";
import { Card, CardContent, Typography, Button } from "@mui/material";

const RevenueGrowth = () => {
  return (
    <Card sx={{ position: "relative", bgcolor: "#242B2E", color: "white" }}>
      <CardContent>
        <Typography variant="h6" sx={{ letterSpacing: ".25px" }}>
          Revenue Growth
        </Typography>
        <Typography variant="body2">Growth compared to last month</Typography>
        <Typography variant="h5" sx={{ my: 3.1 }}>
          15% 📈
        </Typography>
        <Button size="small" variant="contained">
          View Details
        </Button>
      </CardContent>
    </Card>
  );
};

export default RevenueGrowth;
