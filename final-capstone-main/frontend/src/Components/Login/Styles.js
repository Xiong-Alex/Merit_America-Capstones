import { Button } from '@mui/material';
import { Link } from 'react-router-dom';
import styled from 'styled-components';

export const Container = styled.div`
	display: flex;
	flex-direction: column;
	@media (min-width: 480px) {
		flex-direction: row;
	}
`;

export const ImageContainer = styled.section`
	@media (min-width: 480px) {
		width: 50%;
	}
`;

export const ImageLogin = styled.img`
	width: 100%;
	display: none;
	object-fit: cover;
	@media (min-width: 480px) {
		display: block;
		height: 93vh;
	}
`;

export const LoginTitle = styled.h3`
	font-size: 32px;
	font-family: Roboto;
	font-weight: bold;
	text-align: center;
	text-transform: uppercase;
	padding: 2rem;
`;

export const FormContainer = styled.section`
	display: flex;
	flex-direction: column;
	background: white;
	padding: 1rem;
	@media (min-width: 480px) {
		margin: 5rem auto;
		height: ${(props) => (props.height === 'med' ? '43vh' : '50vh')};
		border-radius: 10px;
	}
`;

export const RegisterInput = styled.input`
	width: 300px;
	padding: 0.8rem;
	margin: 0 auto;
	margin-bottom: .4rem;
	margin-top: .4rem;
	border-radius: 10px;
	border: none;
	background-color: #efefef;
`;

export const UsernameInput = styled(RegisterInput)`
	// width: 300px;
	// padding: 0.8rem;
	// margin: 0 auto;
	// margin-bottom: 1rem;
	// margin-top: 1rem;
	// border-radius: 10px;
	// border: none;
	// background-color: #efefef;
`;

export const PasswordInput = styled(RegisterInput)`
	// width: 300px;
	// padding: 0.8rem;
	// margin: 0 auto;
`;



export const LoginButton = styled(Button)`
	width: 200px;
	height: 40px;
`;

export const ButtonsContainer = styled.div`
	display: flex;
	justify-content: center;
	margin-top: 1rem;
`;

export const SignupLink = styled(Link)`
	text-align: center;
	margin-top: 1rem;
	font-family: Archivo;
	text-decoration: none;
	color: black;
	&:hover {
		color: #ee4b46;
	}
`;

export const Span = styled.span`
	color: #ee4b46;
`;

export const InvalidCredentialsBox = styled.span`
	color: red;
	text-align: center;

	@media (min-width: 480px) {
		text-align: start;
		position: relative;
		top:-5px;
		left: 15px;
		font-size: 85%;
	}


`;