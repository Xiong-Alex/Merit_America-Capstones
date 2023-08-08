import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { Button } from "@mui/material";
import styled from "styled-components";

export const FeaturesContainer = styled.section`
	display: flex;
	flex-direction: column;
	width: auto;
	margin: 0 auto;
	padding: 1rem;
	//Desktop Below
	@media (min-width: 480px) {
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: center;
		width: auto;
		margin: auto;
	}
`;

export const FeaturesTitle = styled.h3`
	grid-area: title;
	font-size: 20px;
	font-family: Roboto;
	font-weight: bold;
	margin-top: 2rem;
	text-align: center;
	//Desktop Below
	@media (min-width: 480px) {
		text-align: center;
	}
`;

export const Card = styled.div`
	grid-area: card;
	display: flex;
	flex-direction: column;
	justify-content: space-around;
	height: 300px;
	margin: 0 auto;
	font-size: 20px;
	background-color: ${(props) => (props.color ? props.color : "#222222")};
	color: white;
	border-radius: 10px;
	padding: 2rem;
	margin-top: 1.5rem;
	margin-bottom: 2rem;
	//Desktop Below
	@media (min-width: 480px) {
		margin-right: 1.5rem;
		margin-bottom: 2rem;
	}
`;

export const CardContainer = styled.div``;

export const StyledIcon = styled(FontAwesomeIcon)`
	color: ${(props) => (props.color ? props.color : "white")};
	font-size: ${(props) => (props.size ? props.size : "15px")};
`;

export const FeatureButton = styled(Button)`
	width: 185px;
	height: 49px;
`;

export const CardTitle = styled.h3`
	font-size: 24px;
	width: 300px;
	font-family: Roboto;
	font-weight: bold;
`;
