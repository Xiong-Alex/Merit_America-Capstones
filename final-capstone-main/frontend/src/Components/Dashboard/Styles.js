import { Breadcrumbs, Card, CardContent } from "@mui/material";
import { Link } from "react-router-dom";
import styled from "styled-components";

export const PageHeader = styled.div`
	margin: 0rem 0rem 1.5rem 0rem;
	background-image: linear-gradient(rgba(27, 38, 44, 0.64), rgba(0, 0, 0, 0.5)),
		url("https://images.pexels.com/photos/952479/pexels-photo-952479.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1");
	background-position: center;
	background-attachment: fixed;
	background-repeat: no-repeat;
	background-size: cover;
`;

export const DashboardTitle = styled.h1`
	padding: 2rem;
	text-align: center;
	color: white;
	font-size: 28px;
	font-family: Roboto;
	font-weight: bold;
`;

export const IntroContainer = styled.section`
	display: flex;
	flex-direction: column;
	align-items: center;
	@media (min-width: 480px) {
		flex-direction: row;
		justify-content: space-between;
	}
`;

export const WelcomeCard = styled(Card)`
	margin-bottom: 1rem;
`;

export const WelcomeCardContent = styled(CardContent)`
	background-color: white;
	width: 300px;
	text-align: center;
`;

export const RestarauntContainer = styled.section`
	background-color: white;
	margin: 0 auto;
	display: flex;
	flex-direction: column;
	border-radius: 10px;
	border: 0.1rem solid #dbdfe9;
	@media (min-width: 480px) {
		width: 100%;
	}
`;

export const InnerContainer = styled.div`
	display: flex;
	flex-wrap: wrap;
	gap: 1rem;
	padding: 1rem;
	margin: 1rem;
	align-items: center;
`;

export const DiscoverList = styled.section`
	display: flex;
	flex-wrap: nowrap;
	gap: 20px;
	overflow-x: auto;
	padding: 1rem;
	gap: 2rem;
	align-items: center;
	justify-content: space-between;
`;

export const Box = styled.div`
	background-color: #f6f6f6;
	width: 100%;
	margin-bottom: 1rem;
	display: flex;
	flex-direction: column;
	@media (min-width: 480px) {
		width: 325px;
		margin-right: 1rem;
		margin-bottom: 1rem;
	}
`;

export const Image = styled.img`
	object-fit: cover;
	border-radius: 5px;
	height: 25vh;
	@media (min-width: 480px) {
	}
`;

export const BoxTitle = styled.h3`
	font-size: 18px;
	text-align: center;
	@media (min-width: 480px) {
	}
`;

export const BoxDescription = styled.p`
	font-size: 18px;
	text-align: center;
	@media (min-width: 480px) {
	}
`;

export const PageContainer = styled.section`
	padding: 0rem 1rem 1rem 1rem;
	@media (min-width: 480px) {
	}
`;

export const WelcomeMessage = styled.p`
	font-family: Archivo;
`;

export const CreateInvite = styled(WelcomeMessage)``;

export const RequestSectionTitle = styled(WelcomeMessage)`
	text-align: center;
	margin-top: 2rem;
	font-family: Roboto;
	font-weight: bold;
	font-size: 18px;
`;

export const StartInviteLink = styled(Link)`
	text-decoration: none;
	color: black;
	&:hover {
		color: #ee4b46;
	}
`;

export const MainBreadcrumb = styled(Breadcrumbs)`
	margin-bottom: 1rem;
	margin-top: 1rem;
	display: flex;
	justify-content: center;
	@media (min-width: 480px) {
		display: block;
	}
`;

export const ClearButton = styled.button`
	background: #ee4b46;
	border: none;
	border-radius: 5px;
	padding: 8px;
	margin-bottom: 0.5rem;
	color: white;
	&:hover {
		background: #db3c37;
	}
`;

export const SubmitLink = styled.a`
	text-decoration: none;
	color: white;
`;
