import { Avatar, Box, Grid, Rating } from "@mui/material";
import React from "react";
const Productreviewcard = () => {
  return (
    <div className="">
      <Grid container spacing={2} gap={3}>
        <Grid item xs={1}>
          <Box>
            <Avatar
              className="text-white "
              sx={{ width: 56, height: 56, bgcolor: "#9155fd" }}
            >
              R
            </Avatar>
          </Box>
        </Grid>
        <Grid item xs={9}>
          <div className="space-y-2">
            <div>
              <p className="font-semibold font-lg">Mohamed Kaanoun</p>
              <p className="opacity-70">Juin 23, 2024</p>
            </div>
            <Rating value={4.5} precision={0.5} readOnly />
            <p>
              Lorem ipsum dolor sit amet consectetur adipisicing elit. Quos
              nihil culpa recusandae.
            </p>
          </div>
        </Grid>
      </Grid>
    </div>
  );
};

export default Productreviewcard;
