import React from "react";
import { Card, CardContent, Typography, Button } from "@mui/material";

const CustomerFeedback = () => {
  return (
    <Card sx={{ position: "relative", bgcolor: "#242B2E", color: "white" }}>
      <CardContent>
        <Typography variant="h6" sx={{ letterSpacing: ".25px" }}>
          Customer Feedback
        </Typography>
        <Typography variant="body2">What customers are saying</Typography>
        <Typography variant="h5" sx={{ my: 3.1 }}>
          4.5/5 ⭐
        </Typography>
        <Button size="small" variant="contained">
          View Feedback
        </Button>
      </CardContent>
    </Card>
  );
};

export default CustomerFeedback;
