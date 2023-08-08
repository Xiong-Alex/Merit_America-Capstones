import { createTheme } from "@mui/material/styles";

export const theme = createTheme({
  status: {
    danger: "#e53e3e",
  },
  palette: {
    primary: {
      main: "#EE4B46",
      darker: "#D74743",
    },
    dark: {
      main: '#222222',
    },
    light: {
      main: '#ffffff',
      contrastText: "#222222",
    },
    neutral: {
      main: "#64748B",
      contrastText: "#fff",
    },
  },
});
