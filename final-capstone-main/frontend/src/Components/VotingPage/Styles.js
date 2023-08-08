import styled from "styled-components";

export const Container = styled.div`
	display: flex;
	flex-direction: column;
	padding: 0rem 1rem 1rem 1rem;
	@media (min-width: 480px) {
		flex-direction: row;
		justify-content: center;
	}
`;

export const InformationContainer = styled.section`
	margin-bottom: 1rem;
	margin-top: 1rem;
	height: 20vh;
	background-color: white;
	border-radius: 10px;
	border: 0.1rem solid #dbdfe9;
	padding: 1rem;
	@media (min-width: 480px) {
		flex-direction: row;
		justify-content: center;
		height: 25vh;
		width: 250px;
	}
`;

export const VotingContainer = styled.section`
	background-color: black;
	background-color: white;
	border-radius: 10px;
	border: 0.1rem solid #dbdfe9;
	margin-top: 1rem;
	display: flex;
	@media (min-width: 480px) {
		flex-direction: row;
		justify-content: center;
		margin-left: 1rem;
		padding: 1rem;
	}
`;

export const SidebarTitle = styled.h3`
	font-size: 20px;
	font-weight: 700;
	@media (min-width: 480px) {
	}
`;

export const ThumbsContainer = styled.div`
	display: flex;
	gap: 1rem;
	@media (min-width: 480px) {
	}
`;
