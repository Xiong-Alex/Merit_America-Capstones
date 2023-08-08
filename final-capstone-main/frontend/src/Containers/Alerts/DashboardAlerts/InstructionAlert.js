import Alert from '@mui/material/Alert';
import React from 'react';
import { AlertContainer } from './Styles';

const InstructionAlert = () => {
	return (
		<AlertContainer>
			<Alert severity="info" variant="filled">
				Start inviting your guest to eat! It takes less than 5 minutes{' '}
			</Alert>
		</AlertContainer>
	);
};

export default InstructionAlert;
