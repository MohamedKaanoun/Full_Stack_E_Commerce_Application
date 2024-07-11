import React, { useEffect, useState } from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import { deleteProduct, findProducts } from "../../State/Product/Action";
import { useDispatch, useSelector } from "react-redux";
import {
  Avatar,
  Box,
  Button,
  Card,
  CardHeader,
  CircularProgress,
  TablePagination,
} from "@mui/material";

const ProductsTable = () => {
  const { products } = useSelector((store) => store);
  const dispatch = useDispatch();
  const [page, setPage] = useState(0);
  const [rowsPerPage, setRowsPerPage] = useState(10);

  useEffect(() => {
    const data = {
      category: "",
      colors: [],
      sizes: [],
      minPrice: 0,
      maxPrice: 100000,
      minDiscount: 0,
      sort: "price_low",
      pageNumber: page + 1,
      pageSize: rowsPerPage,
      stock: "",
    };
    dispatch(findProducts(data));
  }, [dispatch, page, rowsPerPage, products.deletedproduct]);

  const handleProductDelete = (productId) => {
    dispatch(deleteProduct(productId));
  };

  const handleChangePage = (event, newPage) => {
    setPage(newPage);
  };

  const handleChangeRowsPerPage = (event) => {
    setRowsPerPage(parseInt(event.target.value, 10));
    setPage(0);
  };

  if (!products || !products.products || !products.products.content) {
    return (
      <Box
        sx={{
          display: "flex",
          justifyContent: "center",
          alignItems: "center",
          height: "100vh",
        }}
      >
        <CircularProgress />
      </Box>
    );
  }

  return (
    <div className="p-5">
      <Card className="mt-2">
        <CardHeader title="All Products" />
      </Card>
      <TableContainer component={Paper}>
        <Table sx={{ minWidth: 650 }} aria-label="simple table">
          <TableHead>
            <TableRow>
              <TableCell align="left">Product Id</TableCell>
              <TableCell align="center">Product Image</TableCell>
              <TableCell align="left">Product Name</TableCell>
              <TableCell align="left">Product Category</TableCell>
              <TableCell align="left">Product Price</TableCell>
              <TableCell align="left">Product Quantity</TableCell>
              <TableCell align="left">Delete</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {products.products.content.map((item) => (
              <TableRow
                key={item.id}
                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
              >
                <TableCell align="left">{item.id}</TableCell>
                <TableCell align="center">
                  <Avatar src={item.imageUrl}></Avatar>
                </TableCell>
                <TableCell component="th" scope="row">
                  {item.title}
                </TableCell>
                <TableCell align="left">{item.category.name}</TableCell>
                <TableCell align="left">{item.price}</TableCell>
                <TableCell align="left">{item.quantity}</TableCell>
                <TableCell align="center">
                  <Button
                    onClick={() => handleProductDelete(item.id)}
                    variant="outlined"
                  >
                    Delete
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
        <TablePagination
          component="div"
          count={products?.products?.page?.totalElements}
          page={page}
          onPageChange={handleChangePage}
          rowsPerPage={rowsPerPage}
          onRowsPerPageChange={handleChangeRowsPerPage}
        />
      </TableContainer>
    </div>
  );
};

export default ProductsTable;
