import styled from "styled-components";
import { UsernameInput } from "../Login/Styles";

export const RestarauntForm = styled.section`
	background: #ffffff;
	margin-bottom: 1rem;
	padding: 1rem;
	border-radius: 10px;
	border: 0.1rem solid #dbdfe9;
	@media (min-width: 480px) {
		margin-right: 1.5rem;
	}
`;

export const RestarauntList = styled.section`
	background: #ffffff;
	padding: 1rem;
	border-radius: 10px;
	border: 0.1rem solid #dbdfe9;
	display: flex;
	flex-direction: column;
	justify-content: space-between;
	align-items: center;
	@media (min-width: 480px) {
		flex-direction: row;
		flex-wrap: wrap;
		justify-content: center;
		gap: 2rem;
	}
`;

export const Form = styled.form`
	display: flex;
	flex-direction: column;
`;

export const Header = styled.div`
	padding: 0rem 1rem 1rem 1rem;
	@media (min-width: 480px) {
	}
`;

export const PageContainer = styled.div`
	display: flex;
	flex-direction: column;
	@media (min-width: 480px) {
		flex-direction: row;
	}
`;

export const Title = styled.h3`
	text-align: center;
	font-family: Archivo;
`;

export const Input = styled(UsernameInput)`
	margin-bottom: 0rem;
	input:focus {
		outline: 2px solid red;
	}
`;
