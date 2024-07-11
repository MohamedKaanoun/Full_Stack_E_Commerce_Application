import { Box, Button, Grid, TextField } from "@mui/material";
import React from "react";
import AddressCard from "../AddressCard/AddressCard";
import { useDispatch } from "react-redux";
import { createOrder } from "../../../State/Order/Action";
import { useNavigate } from "react-router-dom";
const DeliveryAddressForm = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const handleSubmit = (e) => {
    e.preventDefault();
    const data = new FormData(e.currentTarget);
    const address = {
      firstname: data.get("FirstName"),
      lastname: data.get("LastName"),
      streetAddress: data.get("Address"),
      city: data.get("City"),
      zip: data.get("Zip"),
      phoneNumber: data.get("PhoneNumber"),
      state: data.get("State"),
    };
    const orderData = { address, navigate };
    dispatch(createOrder(orderData));
  };
  return (
    <div>
      <Grid container space={4}>
        <Grid
          xs={12}
          lg={5}
          item
          className="border rounded-e-md shadow-md h-[30.5rem] overflow-y-scroll"
        >
          <div className="p-5 py-7 border-b cursor-pointer">
            <AddressCard />
            <Button
              sx={{ mt: 2, bgcolor: "#822DEF" }}
              size="large"
              variant="contained"
            >
              Deliver Here
            </Button>
          </div>
        </Grid>
        <Grid item xs={12} lg={7}>
          <Box className="border rounded-s-md shadow-md p-5 ">
            <form onSubmit={handleSubmit}>
              <Grid container spacing={3}>
                <Grid item xs={12} sm={6}>
                  <TextField
                    required
                    id="FirstName"
                    name="FirstName"
                    label="FirstName"
                    fullWidth
                    autoComplete="given name"
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    required
                    id="LastName"
                    name="LastName"
                    label="LastName"
                    fullWidth
                    autoComplete="given name"
                  />
                </Grid>
                <Grid item xs={12}>
                  <TextField
                    required
                    id="address"
                    name="Address"
                    label="Address"
                    fullWidth
                    autoComplete="given name"
                    multiline
                    rows={4}
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    required
                    id="city"
                    name="City"
                    label="City"
                    fullWidth
                    autoComplete="given name"
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    required
                    id="state"
                    name="State"
                    label="State/Province/Region"
                    fullWidth
                    autoComplete="given name"
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    required
                    id="zip"
                    name="Zip"
                    label="Zip / Postal Code"
                    fullWidth
                    autoComplete="shipping postal-code"
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <TextField
                    required
                    id="phone"
                    name="PhoneNumber"
                    label="Phone Number"
                    fullWidth
                    autoComplete="given-name"
                  />
                </Grid>
                <Grid item xs={12} sm={6}>
                  <Button
                    sx={{ mt: 2, bgcolor: "#822DEF" }}
                    size="large"
                    variant="contained"
                    type="submit"
                  >
                    Deliver Here
                  </Button>
                </Grid>
              </Grid>
            </form>
          </Box>
        </Grid>
      </Grid>
    </div>
  );
};

export default DeliveryAddressForm;
