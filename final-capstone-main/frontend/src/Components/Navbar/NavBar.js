import { faUser } from '@fortawesome/free-solid-svg-icons';
import React from 'react';
import { Header, Logo, Nav, NavLink, StyledUserIcon } from './Styles';

const NavBar = () => {
	return (
		<Header>
			<Logo to="/"> rematch </Logo>
			<Nav>
				<NavLink to="/">Home </NavLink>
				<NavLink to="/about">About </NavLink>
				<NavLink to="/contact">Contact </NavLink>
			</Nav>
			<StyledUserIcon icon={faUser} />
		</Header>
	);
};

export default NavBar;
