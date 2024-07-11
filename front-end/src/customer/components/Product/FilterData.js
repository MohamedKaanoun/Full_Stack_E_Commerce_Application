export const color = [
  "white",
  "Black",
  "red",
  "marun",
  "Being",
  "Pink",
  "Green",
  "Yellow",
];

export const filters = [
  {
    id: "color",
    name: "Color",
    options: [
      { value: "white", label: "white" },
      { value: "Black", label: "Black" },
      { value: "red", label: "red" },
      { value: "marun", label: "marun" },
      { value: "Being", label: "Being" },
      { value: "Pink", label: "Pink" },
      { value: "Green", label: "Green" },
      { value: "Yellow", label: "Yellow" },
    ],
  },
  {
    id: "size",
    name: "Size",
    options: [
      { value: "S", label: "S" },
      { value: "M", label: "M" },
      { value: "L", label: "L" },
      { value: "XL", label: "XL" },
      { value: "XXL", label: "XXL" },
      { value: "XXXL", label: "XXXL" },
    ],
  },
];

export const singleFilter = [
  {
    id: "price",
    name: "Price",
    options: [
      { value: "99-199", label: "99 MAD - 199 MAD" },
      { value: "199-299", label: "199 MAD - 299 MAD" },
      { value: "299-399", label: "299 MAD - 399 MAD" },
      { value: "399-499", label: "399 MAD - 499 MAD" },
    ],
  },
  {
    id: "discount",
    name: "Discount Range",
    options: [
      { value: "10", label: "10% and above" },
      { value: "20", label: "20% and above" },
      { value: "30", label: "30% and above" },
      { value: "40", label: "40% and above" },
      { value: "50", label: "50% and above" },
      { value: "60", label: "60% and above" },
      { value: "70", label: "70% and above" },
      { value: "80", label: "80% and above" },
      { value: "90", label: "90% and above" },
    ],
  },
  {
    id: "stock",
    name: "Availability",
    options: [
      { value: "in_stock", label: "In Stock" },
      { value: "out_of_stock", label: "Out Of Stock" },
    ],
  },
];
