import React from "react";
import { Card, CardContent, Typography, Button, styled } from "@mui/material";

const RecentActivity = () => {
  return (
    <Card sx={{ position: "relative", bgcolor: "#242B2E", color: "white" }}>
      <CardContent>
        <Typography variant="h6" sx={{ letterSpacing: ".25px" }}>
          Recent Activity
        </Typography>
        <Typography variant="body2">Here's what's happening</Typography>
        <Typography variant="h5" sx={{ my: 3.1 }}>
          50 New Orders
        </Typography>
        <Button size="small" variant="contained">
          View Activity
        </Button>
      </CardContent>
    </Card>
  );
};

export default RecentActivity;
