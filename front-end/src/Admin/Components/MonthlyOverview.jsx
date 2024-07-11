import React from "react";
import {
  AccountCircle,
  AttachMoney,
  Devices,
  MoreVert,
  TrendingUp,
} from "@mui/icons-material";
import {
  Card,
  CardContent,
  Typography,
  IconButton,
  Grid,
  Box,
  Avatar,
  CardHeader,
} from "@mui/material";

const salesData = [
  {
    stats: "111k",
    title: "Sales",
    color: "primary.main",
    icon: <TrendingUp sx={{ fontSize: "1.75rem" }} />,
  },
  {
    stats: "333k $",
    title: "Customers",
    color: "success.main",
    icon: <AccountCircle sx={{ fontSize: "1.75rem" }} />,
  },
  {
    stats: "222k",
    title: "Products",
    color: "warning.main",
    icon: <Devices sx={{ fontSize: "1.75rem" }} />,
  },
  {
    stats: "444k",
    title: "Revenue",
    color: "info.main",
    icon: <AttachMoney sx={{ fontSize: "1.75rem" }} />,
  },
];

const renderStats = () => {
  return salesData.map((item, index) => (
    <Grid item xs={12} sm={3} key={index}>
      <Box
        sx={{
          display: "flex",
          alignItems: "center",
        }}
      >
        <Avatar
          variant="rounded"
          sx={{
            mr: 3,
            width: 44,
            height: 44,
            boxShadow: 3,
            color: "white",
            backgroundColor: item.color,
          }}
        >
          {item.icon}
        </Avatar>
        <Box sx={{ display: "flex", flexDirection: "column" }}>
          <Typography variant="caption">{item.title}</Typography>
          <Typography variant="h6">{item.stats}</Typography>
        </Box>
      </Box>
    </Grid>
  ));
};

const MonthlyOverview = () => {
  return (
    <Card sx={{ position: "relative", bgcolor: "#242B2E", color: "white" }}>
      <CardHeader
        title="Monthly Overview"
        action={
          <IconButton size="small">
            <MoreVert />
          </IconButton>
        }
        subheader={
          <Typography variant="body2">
            <Box component="span" sx={{ fontWeight: 600 }} className="pr-1">
              Total 48.5% growth
            </Box>
            this Month
          </Typography>
        }
        titleTypographyProps={{
          sx: {
            mb: 2.5,
            lineHeight: "2rem !important",
            letterSpacing: ".15px !important",
          },
        }}
      />
      <CardContent sx={{ pt: (theme) => `${theme.spacing(3)} !important` }}>
        <Grid container spacing={3} justifyContent="space-between">
          {renderStats()}
        </Grid>
      </CardContent>
    </Card>
  );
};

export default MonthlyOverview;
