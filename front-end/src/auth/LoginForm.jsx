import { Button, Grid, TextField } from "@mui/material";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useDispatch } from "react-redux";
import { login } from "../State/Auth/Action";

const LoginForm = () => {
  const [errorMessage] = useState("");
  const dispatch = useDispatch();
  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();

    const data = new FormData(event.currentTarget);

    const userData = {
      email: data.get("email"),
      password: data.get("password"),
    };

    dispatch(login(userData));
  };

  return (
    <div>
      <form onSubmit={handleSubmit}>
        <Grid container spacing={3}>
          <Grid item xs={12}>
            <TextField
              required
              id="email"
              name="email"
              label="Email"
              fullWidth
              autoComplete="email"
            />
          </Grid>
          <Grid item xs={12}>
            <TextField
              required
              id="password"
              name="password"
              label="Password"
              fullWidth
              autoComplete="current-password"
              type="password"
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
              Login
            </Button>
          </Grid>
        </Grid>
      </form>
      {errorMessage && (
        <div className="flex justify-center flex-col items-center mt-4">
          <p className="text-red-500">{errorMessage}</p>
        </div>
      )}
      <div className="flex justify-center flex-col items-center">
        <div className="py-3 flex items-center">
          <p>Don't have an account?</p>
          <Button
            onClick={() => navigate("/register")}
            className="ml-5"
            size="small"
          >
            Signup
          </Button>
        </div>
      </div>
    </div>
  );
};

export default LoginForm;
