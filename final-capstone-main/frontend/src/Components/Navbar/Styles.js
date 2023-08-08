import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

export const Header = styled.header`
	background: #ee4b46;
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 0.5rem 0.7rem;
`;

export const Logo = styled(Link)`
	color: white;
	font-family: Black Han Sans;
	font-weight: bolder;
	text-transform: uppercase;
	font-size: 20px;
	text-decoration: none;
`;

export const Nav = styled.nav`
	padding: 1rem 50px;
`;

export const NavLink = styled(Link)`
	color: white;
	font-size: 1.2rem;
	text-decoration: none;
`;

export const StyledUserIcon = styled(FontAwesomeIcon)`
	color: white;
`;
