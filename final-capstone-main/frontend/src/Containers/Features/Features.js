import {
	faArrowRight,
	faCheckToSlot,
	faGlobe,
	faPeopleGroup,
} from '@fortawesome/free-solid-svg-icons';
import React from 'react';
import {
	Card,
	CardContainer,
	CardTitle,
	FeatureButton,
	FeaturesContainer,
	FeaturesTitle,
	StyledIcon,
} from './Styles';

const Features = () => {
	return (
		<>
			<FeaturesTitle> Features </FeaturesTitle>
			<FeaturesContainer>
				<CardContainer>
					<Card>
						<p>
							<StyledIcon icon={faCheckToSlot} /> Voting System
						</p>
						<CardTitle>
							Let your friends choose! Your group has a voice
						</CardTitle>
						<div>
							<FeatureButton href="/login" variant="contained">
								Start Today <StyledIcon icon={faArrowRight} />
							</FeatureButton>
						</div>
					</Card>
				</CardContainer>
				<CardContainer>
					<Card color="#F19E52">
						<p>
							<StyledIcon icon={faPeopleGroup} /> Social
						</p>
						<CardTitle>
							No more eating out solo! Grab some friends and enjoy some food
							together
						</CardTitle>
						<div>
							<FeatureButton href="/login" color="dark" variant="contained">
								Start Today <StyledIcon icon={faArrowRight} />
							</FeatureButton>
						</div>
					</Card>
				</CardContainer>
				<CardContainer>
					<Card color="#EE4B46">
						<p>
							<StyledIcon icon={faGlobe} /> Exploring
						</p>
						<CardTitle> Start out by exploring some restaurants</CardTitle>
						<div>
							<FeatureButton href="/login" color="light" variant="contained">
								Start Today <StyledIcon icon={faArrowRight} color="#22222" />
							</FeatureButton>
						</div>
					</Card>
				</CardContainer>
			</FeaturesContainer>
		</>
	);
};

export default Features;
