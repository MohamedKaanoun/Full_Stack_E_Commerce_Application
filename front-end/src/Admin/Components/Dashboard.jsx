import React from "react";
import { Grid } from "@mui/material";
import Achievement from "./Achievement";
import MonthlyOverview from "./MonthlyOverview";
import SalesOverview from "./SalesOverview";
import RecentActivity from "./RecentActivity";
import TopProducts from "./TopProducts";
import InventoryStatus from "./InventoryStatus";
import PendingOrders from "./PendingOrders";
import CustomerFeedback from "./CustomerFeedback";
import RevenueGrowth from "./RevenueGrowth";
const Dashboard = () => {
  return (
    <div className="p-10 pt-8">
      <Grid container spacing={2}>
        <Grid item xs={12} md={4}>
          <Achievement />
        </Grid>
        <Grid item xs={12} md={4}>
          <SalesOverview />
        </Grid>
        <Grid item xs={12} md={4}>
          <RecentActivity />
        </Grid>
        <Grid item xs={12} md={8}>
          <MonthlyOverview />
        </Grid>
        <Grid item xs={12} md={4}>
          <TopProducts />
        </Grid>
        <Grid item xs={12} md={4}>
          <CustomerFeedback />
        </Grid>
        <Grid item xs={12} md={4}>
          <InventoryStatus />
        </Grid>
        <Grid item xs={12} md={4}>
          <RevenueGrowth />
        </Grid>
        <Grid item xs={12} md={4}>
          <PendingOrders />
        </Grid>
      </Grid>
    </div>
  );
};

export default Dashboard;
