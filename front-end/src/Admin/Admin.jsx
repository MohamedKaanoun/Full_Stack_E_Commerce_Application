import React, { useState } from "react";
import { Route, Routes, useNavigate } from "react-router-dom";
import DashboardIcon from "@mui/icons-material/Dashboard";
import Inventory2Icon from "@mui/icons-material/Inventory2";
import PersonIcon from "@mui/icons-material/Person";
import ShoppingCartIcon from "@mui/icons-material/ShoppingCart";
import AddIcon from "@mui/icons-material/Add";
import AccountCircleIcon from "@mui/icons-material/AccountCircle";
import {
  useTheme,
  Box,
  List,
  ListItem,
  ListItemButton,
  ListItemIcon,
  useMediaQuery,
  CssBaseline,
  ListItemText,
} from "@mui/material";
import Dashboard from "./Components/Dashboard";
import ProductsTable from "./Components/ProductsTable";
import CustomerTable from "./Components/CustomerTable";
import OrderTable from "./Components/OrderTable";
import CreateProductForm from "./Components/CreateProductForm";
import ProfilePage from "./Components/Profile";

const menu = [
  { name: "Dashboard", path: "/admin", icon: <DashboardIcon /> },
  { name: "Products", path: "/admin/products", icon: <Inventory2Icon /> },
  { name: "Customers", path: "/admin/customers", icon: <PersonIcon /> },
  { name: "Orders", path: "/admin/orders", icon: <ShoppingCartIcon /> },
  { name: "Add Product", path: "/admin/products/create", icon: <AddIcon /> },
];

const Admin = () => {
  const theme = useTheme();
  const navigate = useNavigate();

  const drawer = (
    <Box
      sx={{
        display: "flex",
        flexDirection: "column",
        height: "100vh",
      }}
    >
      <List sx={{ flexGrow: 1 }}>
        {menu.map((item, index) => (
          <ListItem
            key={item.name}
            disablePadding
            onClick={() => navigate(item.path)}
          >
            <ListItemButton>
              <ListItemIcon>{item.icon}</ListItemIcon>
              <ListItemText>{item.name}</ListItemText>
            </ListItemButton>
          </ListItem>
        ))}
      </List>
      <List>
        <ListItem disablePadding>
          <ListItemButton>
            <ListItemIcon>
              <AccountCircleIcon />
            </ListItemIcon>
            <ListItemText onClick={() => navigate("/admin/profile")}>
              Profile
            </ListItemText>
          </ListItemButton>
        </ListItem>
      </List>
    </Box>
  );

  return (
    <div className="flex h-[100vh]">
      <CssBaseline />
      <div className="w-[15%] border border-r-gray-500 h-full fixed top-0">
        {drawer}
      </div>
      <div className="w-[85%] h-full ml-[15%]">
        <Routes>
          <Route path="/" element={<Dashboard />} />
          <Route path="/profile" element={<ProfilePage />} />
          <Route path="/products" element={<ProductsTable />} />
          <Route path="/customers" element={<CustomerTable />} />
          <Route path="/orders" element={<OrderTable />} />
          <Route path="/products/create" element={<CreateProductForm />} />
        </Routes>
      </div>
    </div>
  );
};

export default Admin;
