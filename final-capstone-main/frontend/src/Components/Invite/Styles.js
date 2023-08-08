import { Button } from "@mui/material";
import styled from "styled-components";

export const LeftImage = styled.img`
	width: 100%;
	object-fit: cover;
	@media (min-width: 480px) {
		width: 50%;
		height: 93.1vh;
	}
`;

export const Container = styled.section`
	@media (min-width: 480px) {
		display: flex;
	}
`;

export const FormSection = styled.section`
	display: flex;
	flex-direction: column;
	align-items: center;
	@media (min-width: 480px) {
		width: 50%;
		height: 93.1vh;
		justify-content: center;
		margin-top: -2rem;
	}
`;

export const Form = styled.form`
	display: flex;
	flex-direction: column;
`;

export const LinkSpan = styled.span`
	font-weight: 700;
`;

export const LinkContainer = styled.div`
	margin-top: 5rem;
`;

export const Title = styled.h2`
	font-size: 20px;
	font-family: Roboto;
	font-weight: 900;
`;

export const Input = styled.input`
	width: 300px;
	padding: 0.8rem;
	margin: 0 auto;
	margin-bottom: 1rem;
	margin-top: 1rem;
	border-radius: 10px;
	border: none;
	background-color: #dddddd;
`;

export const InviteButton = styled(Button)`
	width: 300px;
	height: 40px;
	margin: 0 auto;
`;
