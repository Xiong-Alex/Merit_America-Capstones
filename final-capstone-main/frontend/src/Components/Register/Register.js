import axios from 'axios';
import { Component } from 'react';
import { baseUrl } from '../../Shared/baseUrl';
import {
	ButtonsContainer,
	Container,
	FormContainer,
	ImageContainer,
	ImageLogin,
	LoginButton,
	LoginTitle,
	PasswordInput,
	SignupLink,
	Span,
	UsernameInput,
	InvalidCredentialsBox,
	RegisterInput
} from '../Login/Styles';

const initialState = {
	username: '',
	password: '',
	email: '',
	confirmPassword: '',
	usernameError: '',
	passwordError: '',
	emailError: '',
	confirmPasswordError: '',
};

let usernameError = '';
let passwordError = '';
let emailError = '';
let confirmPasswordError = '';

class Register extends Component {
	constructor(props) {
		super(props);
		this.state = initialState;
	}

	handleInputChange = (event) => {
		event.preventDefault();
		this.setState({
			[event.target.name]: event.target.value,
		});
	};

	handleSubmit = () => {
		const data = {
			username: this.state.username,
			email: this.state.email,
			password: this.state.password,
			confirmPassword: this.state.confirmPassword,
			role: 'USER',
		};

		const isValid = this.validate();
		if (isValid) {
			console.log(this.state);
			// this.setState(initialState);

			axios.post(baseUrl + '/register', data).then((res) => {
				this.props.history.replace('/login');
			}).catch((err) => {
				if(err.response.status === 400) {
					usernameError = "User already Exists"
					this.setState({usernameError})
					console.log(usernameError)

					//there is a bug with confirmPasswordError not dissapearing
					//so for now, it is being manually reset
					confirmPasswordError = '';
					this.setState({confirmPasswordError})

				}
			});
		}

	};

	/**
	 * @function - Create terms for the password validation conditions
	 * @return {string} - The error message for password validation
	 */
	passwordTerms = () => {
		function containsSpecialChars(str) {
			const specialChars = `\`!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?~`;
		  
			const result = specialChars.split('').some(specialChar => {
			  if (str.includes(specialChar)) {
				return true;
			  }
		  
			  return false;
			});
		  
			return result;
		  }

		if (this.state.password.length < 7) {
			passwordError = 'Needs to be 8 or more characters!';
		} else if(!/[A-Z]/.test(this.state.password)) {
			passwordError = 'Needs to include a capital letter!';
		} else if(!/[a-z]/.test(this.state.password)) {
			passwordError = 'Needs to include a lower-case letter!';
		} else if(!/[0-9]/.test(this.state.password)) {
			passwordError = 'Needs to include one number!';
		} else if(!containsSpecialChars(this.state.password)) {
			passwordError = "Special character required"
		}
		
		else {passwordError = ''}
		return passwordError;
	};

	/**
	 * @function - Create terms for the username validation conditions
	 * @return {string} - The error message for username validation
	 */
	usernameTerms = () => {
		if (this.state.username.length < 3) {
			usernameError = 'Username needs to be more than 3 characters';
		} else if (this.state.username.length > 10) {
			usernameError = `Username can't be longer than 10 characters`;
		} else {usernameError = ''}
		return usernameError;
	};

	/**
	 * @function - Create terms for the email validation conditions
	 * @return {string} - The error message for username validation
	 */


	emailTerms = () => {
		if (!this.state.email.includes('@')) {
			emailError = 'This is not a valid email.';
		} 
		// FETCH list of all emails
		// else if(emailList.includes(this.state.email)) {"Account Already Exists"}
		
		else {emailError = ''}
		return emailError;
	};

	/**
	 * @function - Create terms for the confirm password validation conditions
	 * @return {void} - The error message for confirm password validation
	 */
	confirmPasswordTerms = () => {
		if (this.state.password != this.state.confirmPassword) {
			confirmPasswordError = 'Passwords do not match.'
		} else {confirmPasswordError = ''}
		return confirmPasswordError
	};


	/**
	 * @function - handles all the validions in one place
	 * @return {void} - Might need to return something elese later on
	 */
	validate = () => {
		this.passwordTerms();
		this.usernameTerms();
		this.emailTerms();
		this.confirmPasswordTerms();
		if (usernameError !==  '' || passwordError !=  '' || emailError !==  '' || confirmPasswordError !==  '') {
			this.setState({
				usernameError,
				passwordError,
				emailError,
				confirmPasswordError,
			});
			return false;
		} else {return true;}
	};

	render() {
		return (
			<>
				<Container>
					<ImageContainer>
						<picture>
							<ImageLogin
								src="https://images.pexels.com/photos/1640774/pexels-photo-1640774.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=1"
								alt="login"
							/>
						</picture>
					</ImageContainer>
					<FormContainer>
						<LoginTitle>
							sign <Span>up</Span>
						</LoginTitle>
						<label className="sr-only">Username</label>
						<RegisterInput
							type="email"
							id="email"
							name="email"
							placeholder="Email"
							v-model="user.email"
							onChange={this.handleInputChange}
							required="required"
						/>
						<InvalidCredentialsBox>{this.state.emailError}</InvalidCredentialsBox>
						<RegisterInput
							type="text"
							id="username"
							name="username"
							placeholder="Username"
							v-model="user.username"
							onChange={this.handleInputChange}
							required="required"
						/>
						<InvalidCredentialsBox>{this.state.usernameError}</InvalidCredentialsBox>
						<label className="sr-only">Password</label>
						<RegisterInput
							type="password"
							id="password"
							name="password"
							placeholder="Password"
							v-model="user.password"
							onChange={this.handleInputChange}
							required="required"
						/>
						<InvalidCredentialsBox>{this.state.passwordError}</InvalidCredentialsBox>
						<RegisterInput
							type="password"
							id="password-confirm"
							name="confirmPassword"
							placeholder="Confirm Password"
							v-model="user.password"
							onChange={this.handleInputChange}
							required="required"
						/>
						<InvalidCredentialsBox>{this.state.confirmPasswordError}</InvalidCredentialsBox>
						<SignupLink to="/login">Have an account?</SignupLink>
						<ButtonsContainer>
							<LoginButton
								color="primary"
								variant="contained"
								type="submit"
								onClick={this.handleSubmit}
							>
								sign up
							</LoginButton>
						</ButtonsContainer>
					</FormContainer>
				</Container>
			</>
		);
	}
}

export default Register;
