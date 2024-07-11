import React from "react";
import { Route, BrowserRouter as Router, Routes } from "react-router-dom";
import CustomerRoutes from "./Routes/CustomerRoutes";
import AdminRoutes from "./Routes/AdminRoutes";
function App() {
  return (
    <div className="App">
      <Router>
        <Routes>
          <Route path="/*" element={<CustomerRoutes />}></Route>
          <Route path="/admin/*" element={<AdminRoutes />}>
            {" "}
          </Route>
        </Routes>
      </Router>
    </div>
  );
}

export default App;
