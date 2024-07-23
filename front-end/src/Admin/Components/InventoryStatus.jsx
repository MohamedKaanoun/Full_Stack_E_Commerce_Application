import React from "react";
import { Card, CardContent, Typography, Button } from "@mui/material";

const InventoryStatus = () => {
  return (
    <Card sx={{ position: "relative", bgcolor: "#242B2E", color: "white" }}>
      <CardContent>
        <Typography variant="h6" sx={{ letterSpacing: ".25px" }}>
          Inventory Status
        </Typography>
        <Typography variant="body2">Current stock levels</Typography>
        <Typography variant="h5" sx={{ my: 3.1 }}>
          1200 Items
        </Typography>
        <Button size="small" variant="contained">
          View Inventory
        </Button>
      </CardContent>
    </Card>
  );
};

export default InventoryStatus;
