import Button from '@mui/material/Button';
import React from 'react';
import Bg from '../../Images/restaraunt.jpeg';
import { BgImage, CtaContainer, CtaTitle, Main, SubText } from './Styles';

const MainCta = () => {
	return (
		<>
			<Main>
				<BgImage src={Bg} alt="restaurant bg" />
				<CtaContainer>
					<CtaTitle> Find Your Next Restaraunt Match</CtaTitle>
					<SubText> A quick and fast way to explore the unknown </SubText>
					<Button href="/login" variant="contained">
						Start Today
					</Button>
				</CtaContainer>
			</Main>
		</>
	);
};

export default MainCta;
