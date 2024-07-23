import React from "react";

const ProfilePage = () => {
  return (
    <div className="container mx-auto mt-12 p-4">
      <div className="bg-white shadow-md rounded-lg p-6">
        <div className="flex flex-col md:flex-row items-center md:items-start mb-6">
          <div className="flex-shrink-0 mb-4 md:mb-0 md:mr-6">
            <img
              className="w-24 h-24 rounded-full mx-auto"
              src="https://via.placeholder.com/100"
              alt="Mohamed Kaanoun"
            />
          </div>
          <div className="text-center md:text-left">
            <h2 className="text-xl font-semibold">Mohamed Kaanoun</h2>
            <p className="text-gray-600">Admin</p>
          </div>
        </div>

        <div className="mb-6">
          <h3 className="text-lg font-semibold mb-2">Personal Information</h3>
          <div className="border-b border-gray-200 mb-2"></div>
          <p className="mb-2">
            <strong>Email:</strong> mohamedkaanoun@example.com
          </p>
          <p className="mb-2">
            <strong>Phone:</strong> +1234567890
          </p>
          <p className="mb-2">
            <strong>Address:</strong> 123 Admin Street, Admin City, Country
          </p>
        </div>

        <div className="mb-6">
          <h3 className="text-lg font-semibold mb-2">Recent Activity</h3>
          <div className="border-b border-gray-200 mb-2"></div>
          <ul className="list-disc list-inside">
            <li className="mb-2">Logged in - 2 hours ago</li>
            <li className="mb-2">Updated product details - 1 day ago</li>
            <li className="mb-2">Processed 5 orders - 2 days ago</li>
          </ul>
        </div>

        <div className="mb-6">
          <h3 className="text-lg font-semibold mb-2">Statistics</h3>
          <div className="border-b border-gray-200 mb-2"></div>
          <p className="mb-2">
            <strong>Total Sales:</strong> $50,000
          </p>
          <p className="mb-2">
            <strong>Orders Processed:</strong> 150
          </p>
          <p className="mb-2">
            <strong>Products Managed:</strong> 50
          </p>
        </div>

        <div className="flex justify-around">
          <button className="bg-blue-500 text-white py-2 px-4 rounded hover:bg-blue-600">
            Edit Profile
          </button>
          <button className="bg-gray-500 text-white py-2 px-4 rounded hover:bg-gray-600">
            Change Password
          </button>
        </div>
      </div>
    </div>
  );
};

export default ProfilePage;
