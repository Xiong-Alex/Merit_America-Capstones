import styled from 'styled-components';

export const BgImage = styled.img`
	width: 100%;
	height: 60vh;
	object-fit: cover;
	filter: brightness(30%);
`;

export const CtaContainer = styled.div`
	color: white;
	position: absolute;
	top: 30%;
	left: 0;
	box-sizing: border-box;
	width: 100%;
	text-align: center;
`;

export const CtaTitle = styled.h3`
	font-size: 36px;
	width: 70%;
	margin: 0 auto;
	font-family: Roboto;
	font-weight: bold;
	@media (min-width: 480px) {
		width: 700px;
		font-size: 64px;
	}
`;

export const Main = styled.main`
	position: relative;
`;

export const SubText = styled(CtaTitle)`
	width: 100%;
	font-size: 18px;
	font-weight: normal;
	margin-top: 0.5rem;
	margin-bottom: 1.8rem;
`;
