import React from "react";
import { Card, CardContent, Typography, Button } from "@mui/material";

const SalesOverview = () => {
  return (
    <Card sx={{ position: "relative", bgcolor: "#242B2E", color: "white" }}>
      <CardContent>
        <Typography variant="h6" sx={{ letterSpacing: ".25px" }}>
          Sales Overview
        </Typography>
        <Typography variant="body2">Great job! 🎉</Typography>
        <Typography variant="h5" sx={{ my: 3.1 }}>
          $500,000
        </Typography>
        <Button size="small" variant="contained">
          View Details
        </Button>
      </CardContent>
    </Card>
  );
};

export default SalesOverview;
