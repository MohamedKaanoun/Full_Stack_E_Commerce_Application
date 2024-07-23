import { Button, Grid, TextField } from "@mui/material";
import React from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { register } from "../State/Auth/Action";

const RegisterForm = () => {
  const dispatch = useDispatch();
  const handleSubmit = (event) => {
    event.preventDefault();
    const data = new FormData(event.currentTarget);
    const userData = {
      firstname: data.get("firstName"),
      lastname: data.get("lastName"),
      password: data.get("password"),
      email: data.get("email"),
    };

    dispatch(register(userData));
    navigate("/login");
  };

  const navigate = useNavigate();

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>
          <Grid item xs={12} sm={6}>
            <TextField
              required
              id="firstName"
              name="firstName"
              label="First Name"
              fullWidth
              autoComplete="given-name"
            />
          </Grid>
          <Grid item xs={12} sm={6}>
            <TextField
              required
              id="lastName"
              name="lastName"
              label="Last Name"
              fullWidth
              autoComplete="given-name"
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              required
              id="email"
              name="email"
              label="Email"
              fullWidth
              autoComplete="given-name"
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              required
              id="password"
              name="password"
              label="Password"
              fullWidth
              autoComplete="password"
            />
          </Grid>
          <Grid item xs={12}>
            <Button
              className="bg-[#9155FD] w-full"
              type="submit"
              variant="contained"
              size="large"
              sx={{ padding: "0.8rem 0" }}
            >
              Register
            </Button>
          </Grid>
        </Grid>
      </form>
      <div className="flex justify-center flex-col items-center">
        <div className="py-3 flex items-center">
          <p>Already have an account ? </p>
          <Button
            onClick={() => navigate("/login")}
            className="ml=5"
            size="small"
          >
            signin
          </Button>
        </div>
      </div>
    </div>
  );
};

export default RegisterForm;
