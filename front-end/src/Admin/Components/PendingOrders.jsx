import React from "react";
import { Card, CardContent, Typography, Button } from "@mui/material";

const PendingOrders = () => {
  return (
    <Card sx={{ position: "relative", bgcolor: "#242B2E", color: "white" }}>
      <CardContent>
        <Typography variant="h6" sx={{ letterSpacing: ".25px" }}>
          Pending Orders
        </Typography>
        <Typography variant="body2">Orders awaiting processing</Typography>
        <Typography variant="h5" sx={{ my: 3.1 }}>
          75 Orders
        </Typography>
        <Button size="small" variant="contained">
          View Orders
        </Button>
      </CardContent>
    </Card>
  );
};

export default PendingOrders;
