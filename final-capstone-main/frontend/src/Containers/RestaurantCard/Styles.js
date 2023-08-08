import { Card } from "@mui/material";
import styled from "styled-components";

export const RestaurantBox = styled(Card)`
	background-color: #e5e5e5;
	margin-top: 1rem;
	margin-right: 1rem;
	border: 1px solid;
	border-color: ${({ isclicked }) => (isclicked ? "red" : "transparent")};
`;

export const DiscoverBox = styled(Card)`
	background-color: #e5e5e5;
	margin-top: 2rem;
	border: 1px solid;
	border-color: ${({ isclicked }) => (isclicked ? "red" : "transparent")};
	flex: 0 0 auto;
	display: flex;
	flex-direction: column;
`;

export const RestaurantType = styled.p`
	padding: 10px;
	position: absolute;
	font-size: 12px;
	font-family: Archivo;
	background: #ee4b46;
	margin-top: -41px;
	text-transform: capitlized;
	font-size: 14px;
	color: white;
	border-top-right-radius: 0.5rem;
`;

export const RestaurantStatus = styled.p`
	padding: 10px;
	position: absolute;
	font-size: 12px;
	font-family: Archivo;
	margin-top: -41px;
	margin-left: 270px;
	text-shadow: 2px 2px #000000;
	text-transform: uppercase;
	font-size: 14px;
	color: white;
	border-top-left-radius: 0.5rem;
	width: 75px;
	text-align: center;
`;

export const PhoneLink = styled.a`
	color: #ee4b46;
	text-decoration: none;
	z-index: 99999;
`;

export const HoursContainer = styled.section`
	font-family: Archivo;
	margin-top: 0.3rem;
`;

export const Day = styled.span`
	font-weight: bold;
	padding: 0.2rem;
`;

export const Hours = styled.p``;

export const OtherInfo = styled.section`
	position: relative;
`;
