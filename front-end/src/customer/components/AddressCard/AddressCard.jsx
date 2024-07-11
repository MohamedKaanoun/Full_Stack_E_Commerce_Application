import React from "react";

const AddressCard = ({ address }) => {
  return (
    <div>
      <div className="space-y-3">
        {console.log("shipping : ", address)}
        <p className="font-semibold">
          {address?.firstname + " " + address?.lastname}
        </p>
        <p>
          {address?.state + " ,"}
          {address?.streetAddress} , {address?.zip}
        </p>
        <div className="space-y-1">
          <p className="font-semibold">Phone Number :</p>
          <p>{address?.phoneNumber}</p>
        </div>
      </div>
    </div>
  );
};

export default AddressCard;
