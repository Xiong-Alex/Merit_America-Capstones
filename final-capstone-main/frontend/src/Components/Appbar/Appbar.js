import { faUser } from "@fortawesome/free-solid-svg-icons";
import AdbIcon from "@mui/icons-material/Adb";
import MenuIcon from "@mui/icons-material/Menu";
import AppBar from "@mui/material/AppBar";
import Box from "@mui/material/Box";
import Container from "@mui/material/Container";
import IconButton from "@mui/material/IconButton";
import Menu from "@mui/material/Menu";
import MenuItem from "@mui/material/MenuItem";
import Toolbar from "@mui/material/Toolbar";
import Tooltip from "@mui/material/Tooltip";
import Typography from "@mui/material/Typography";
import * as React from "react";
import { withRouter } from "react-router-dom";
import { StyledUserIcon } from "../Navbar/Styles";
import { LogoutLink } from "./Styles";

const ResponsiveAppBar = (props) => {
	const { history } = props;
	const [anchorElNav, setAnchorElNav] = React.useState(null);
	const [anchorElUser, setAnchorElUser] = React.useState(null);
	const handleOpenNavMenu = (event) => {
		setAnchorElNav(event.currentTarget);
	};
	const handleOpenUserMenu = (event) => {
		setAnchorElUser(event.currentTarget);
	};
	const handleNavClick = (pageURL) => {
		history.push(pageURL);
		setAnchorElNav(null);
	};
	const handleMenuClick = (pageURL) => {
		history.push(pageURL);
		setAnchorElUser(null);
	};

	return (
		<AppBar position='sticky'>
			<Container maxWidth='xxl'>
				<Toolbar disableGutters>
					<AdbIcon sx={{ display: { xs: "none", md: "flex" }, mr: 1 }} />
					<Typography
						variant='h5'
						noWrap
						component='a'
						href='/'
						sx={{
							mr: 2,
							display: { xs: "none", md: "flex" },
							fontFamily: "Roboto",
							fontWeight: "bold",
							color: "inherit",
							textDecoration: "none",
						}}
					>
						Rematch
					</Typography>
					<Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
						<IconButton
							size='large'
							aria-controls='menu-appbar'
							aria-haspopup='true'
							onClick={handleOpenNavMenu}
							color='inherit'
						>
							<MenuIcon />
						</IconButton>
						<Menu
							id='menu-appbar'
							anchorEl={anchorElNav}
							anchorOrigin={{
								vertical: "bottom",
								horizontal: "left",
							}}
							keepMounted
							transformOrigin={{
								vertical: "top",
								horizontal: "left",
							}}
							open={Boolean(anchorElNav)}
							onClose={handleNavClick}
							sx={{
								display: { xs: "block", md: "none" },
							}}
						>
							<MenuItem onClick={() => handleNavClick("/")}>Home</MenuItem>
							<MenuItem onClick={() => handleNavClick("/about")}>
								About
							</MenuItem>
							<MenuItem onClick={() => handleNavClick("/description")}>
								Description
							</MenuItem>
						</Menu>
					</Box>
					<AdbIcon sx={{ display: { xs: "flex", md: "none" }, mr: 1 }} />
					<Typography
						variant='h5'
						noWrap
						component='a'
						href=''
						sx={{
							mr: 2,
							display: { xs: "flex", md: "none" },
							flexGrow: 1,
							fontFamily: "Roboto",
							fontWeight: "bold",
							color: "inherit",
							textDecoration: "none",
						}}
					>
						Rematch
					</Typography>
					<Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
						<MenuItem onClick={() => handleNavClick("/")}>Home</MenuItem>
					</Box>
					<Box sx={{ flexGrow: 0 }}>
						<Tooltip title='Account'>
							<IconButton onClick={handleOpenUserMenu} sx={{ p: 0 }}>
								<StyledUserIcon icon={faUser} />
							</IconButton>
						</Tooltip>
						<Menu
							sx={{ mt: "45px" }}
							id='menu-appbar'
							anchorEl={anchorElUser}
							anchorOrigin={{
								vertical: "top",
								horizontal: "right",
							}}
							keepMounted
							transformOrigin={{
								vertical: "top",
								horizontal: "right",
							}}
							open={Boolean(anchorElUser)}
							onClose={() => setAnchorElUser(null)}
						>
							{props.token !== undefined ? (
								<div>
									<MenuItem onClick={() => handleMenuClick("/dashboard")}>
										Dashboard
									</MenuItem>
									<MenuItem onClick={() => props.handleLogout()}>
										<LogoutLink to='/login'>Logout</LogoutLink>
									</MenuItem>
								</div>
							) : (
								<>
									<MenuItem>
										<LogoutLink to='/login'>Login</LogoutLink>
									</MenuItem>
								</>
							)}
						</Menu>
					</Box>
				</Toolbar>
			</Container>
		</AppBar>
	);
};
export default withRouter(ResponsiveAppBar);
